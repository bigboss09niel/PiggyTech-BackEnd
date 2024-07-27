package com.example.piggytech.Controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Inventory getProduct(@PathVariable Long id){
        return inventoryRepository.findById(id)
        .orElseThrow(()-> new InventoryNotFoundException(id));
    }

    // CREATE ENDPOINTS
    @PostMapping("/new")
    public ResponseEntity<?> addInventoryEntity(@RequestBody InventoryDTO entity){
        // Find the product by its name
        Product product = productRepository.findByProductName(entity.getProductName());
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        // Update the product's stock
        product.setStock(product.getStock() + entity.getQuantity());
        productRepository.save(product);

        // Create the new inventory entry
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
    @PutMapping("/edit/{id}")
    public Inventory updateInventory(@PathVariable Long id,
    @RequestBody InventoryDTO entity){
        Inventory newInventory = new Inventory(
            entity.getReceivedDate(),
            entity.getExpirationDate(),
            entity.getQuantity()
        );
        Product product = productRepository.findByProductName(entity.getProductName());
        return inventoryRepository.findById(id)
        .map(inventory ->{
            inventory.setReceivedDate(newInventory.getReceivedDate());
            inventory.setExpirationDate(newInventory.getExpirationDate());
            inventory.setQuantity(newInventory.getQuantity());
            inventory.setProduct(Collections.singleton(product));
            return inventoryRepository.save(inventory);
        }).orElseGet(()->{
            return inventoryRepository.save(newInventory);
        });
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteInventory(@PathVariable Long id){
        inventoryRepository.deleteById(id);
        return "A inventory is deleted!";
    }

}