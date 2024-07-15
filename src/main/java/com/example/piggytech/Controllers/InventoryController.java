package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Inventory;
import com.example.piggytech.NotFoundException.InventoryNotFoundException;
import com.example.piggytech.Repository.InventoryRepository;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    
    InventoryRepository repo;

    public InventoryController(InventoryRepository repo) {
        this.repo = repo;
    }

    // GET ALL INVENTORY
    @GetMapping("/all")
    public List<Inventory> getInventory(){
        return repo.findAll();
    }

    // GET ONE INVENTORY
    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new InventoryNotFoundException(id));
    }

    // CREATE ENDPOINTS
    @PostMapping("/new")
    public String addInventory(@RequestBody Inventory newInventory){
        repo.save(newInventory);
        return "A new inventory is added. Yey!";
    }

    // UPDATE ENDPOINTS
    @PutMapping("/edit/{id}")
    public Inventory updateInventory(@PathVariable Long id,
    @RequestBody Inventory newInventory){
        return repo.findById(id)
        .map(inventory ->{
            inventory.setReceivedDate(newInventory.getReceivedDate());
            inventory.setExpirationDate(newInventory.getExpirationDate());
            inventory.setQuantity(newInventory.getQuantity());
            return repo.save(inventory);
        }).orElseGet(()->{
            return repo.save(newInventory);
        });
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteInventory(@PathVariable Long id){
        repo.deleteById(id);
        return "A inventory is deleted!";
    }
}
