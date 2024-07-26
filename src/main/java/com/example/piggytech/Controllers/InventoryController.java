package com.example.piggytech.Controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.DTO.InventoryDTO;
import com.example.piggytech.Model.Inventory;
import com.example.piggytech.Model.Product;
import com.example.piggytech.NotFoundException.InventoryNotFoundException;
import com.example.piggytech.Repository.InventoryRepository;
import com.example.piggytech.Repository.ProductRepository;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ProductRepository productRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // GET ALL INVENTORY
    @GetMapping("/all")
    public ResponseEntity<List<InventoryDTO>> getInventory(@RequestParam(required = false) String search) {
        List<Inventory> inventories;
    
        if (search != null && !search.isEmpty()) {
            inventories = inventoryRepository.findByProductNameContainingIgnoreCase(search);
        } else {
            inventories = inventoryRepository.findAll();
        }

        List<InventoryDTO> inventoryDTOs = inventories.stream()
            .map(inventory -> {
                String productName = inventory.getProduct().iterator().next().getProductName();
                return new InventoryDTO(
                    productName,
                    inventory.getReceivedDate(),
                    inventory.getExpirationDate(),
                    inventory.getQuantity()
                );
            })
            .collect(Collectors.toList());
    
        return new ResponseEntity<>(inventoryDTOs, HttpStatus.OK);
    }
    
    // GET ONE INVENTORY
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventory(@PathVariable Long id) {
        Inventory inventory = inventoryRepository.findById(id)
            .orElseThrow(() -> new InventoryNotFoundException(id));
        
        String productName = inventory.getProduct().iterator().next().getProductName();
        InventoryDTO inventoryDTO = new InventoryDTO(
            productName,
            inventory.getReceivedDate(),
            inventory.getExpirationDate(),
            inventory.getQuantity()
        );
        
        return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
    }

    // CREATE ENDPOINTS
    @Transactional
    @PostMapping("/new")
    public ResponseEntity<String> addInventory(@RequestBody InventoryDTO entity) {
        Product product = productRepository.findByProductName(entity.getProductName());
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        product.setStock(product.getStock() + entity.getQuantity());
        productRepository.save(product);

        Inventory inventory = new Inventory(
            entity.getReceivedDate(),
            entity.getExpirationDate(),
            entity.getQuantity()
        );
        inventory.setProduct(Collections.singleton(product));
        inventoryRepository.save(inventory);

        return new ResponseEntity<>("A new inventory is added. Yey!", HttpStatus.OK);
    }

    // UPDATE ENDPOINTS
    @Transactional
    @PutMapping("/edit/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody InventoryDTO entity) {
        Product product = productRepository.findByProductName(entity.getProductName());
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Inventory updatedInventory = inventoryRepository.findById(id)
            .map(inventory -> {
                inventory.setReceivedDate(entity.getReceivedDate());
                inventory.setExpirationDate(entity.getExpirationDate());
                inventory.setQuantity(entity.getQuantity());
                inventory.setProduct(Collections.singleton(product));
                return inventoryRepository.save(inventory);
            }).orElseGet(() -> {
                Inventory newInventory = new Inventory(
                    entity.getReceivedDate(),
                    entity.getExpirationDate(),
                    entity.getQuantity()
                );
                newInventory.setProduct(Collections.singleton(product));
                return inventoryRepository.save(newInventory);
            });
        
        return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable Long id) {
        if (!inventoryRepository.existsById(id)) {
            return new ResponseEntity<>("Inventory not found", HttpStatus.NOT_FOUND);
        }
        
        inventoryRepository.deleteById(id);
        return new ResponseEntity<>("A inventory is deleted!", HttpStatus.OK);
    }
}
