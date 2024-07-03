package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Sales;
import com.example.piggytech.NotFoundException.SalesNotFoundException;
import com.example.piggytech.Repository.SalesRepository;

@RestController
public class SalesController {
    
    SalesRepository repo;

    public SalesController(SalesRepository repo) {
        this.repo = repo;
    }

    // GET ALL SALES
    // http://127.0.0.1:8080/sales
    @GetMapping("/sales")
    public List<Sales> getProducts(){
        return repo.findAll();
    }

    // GET ONE INVENTORY
    // http://127.0.0.1:8080/sales/1
    @GetMapping("/sales/{id}")
    public Sales getSalesyById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new SalesNotFoundException(id));
    }

    // CREATE ENDPOINTS
    // http://127.0.0.1:8080/sales/new
    @PostMapping("/sales/new")
    public String addSales(@RequestBody Sales newSales){
        repo.save(newSales);
        return "A new sales is added. Yey!";
    }

    // UPDATE ENDPOINTS
    // http://127.0.0.1:8080/sales/edit/1
    @PutMapping("/sales/edit/{id}")
    public Sales updateSales(@PathVariable Long id,
    @RequestBody Sales newSales){
        return repo.findById(id)
        .map(sales ->{
            sales.setProductId(newSales.getProductId());
            sales.setDate(newSales.getDate());
            sales.setQuantity(newSales.getQuantity());
           return repo.save(sales);
        }).orElseGet(()->{
            return repo.save(newSales);
        });
    }

    // DELETE ENDPOINTS
    // http://127.0.0.1:8080/sales/delete/1
    @DeleteMapping("/sales/delete/{id}")
    public String deleteSales(@PathVariable Long id){
        repo.deleteById(id);
        return "A sales is deleted!";
    }
}
