package com.example.piggytech.Controllers;

import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.DTO.SalesDTO;
import com.example.piggytech.Model.Product;
import com.example.piggytech.Model.Sales;
import com.example.piggytech.NotFoundException.SalesNotFoundException;
import com.example.piggytech.Repository.ProductRepository;
import com.example.piggytech.Repository.SalesRepository;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {
    
    @Autowired
    SalesRepository salesRepository;

    @Autowired
    ProductRepository productRepository;

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    // GET ALL SALES
    @GetMapping("/all")
    public List<Sales> getSales(){
        return salesRepository.findAll();
    }

    // GET ONE INVENTORY
    @GetMapping("/{id}")
    public Sales getSalesyById(@PathVariable Long id){
        return salesRepository.findById(id)
        .orElseThrow(()-> new SalesNotFoundException(id));
    }

    // CREATE ENDPOINTS
    @PostMapping("/new")
    public ResponseEntity<?> addSalesEntity(@RequestBody SalesDTO entity){
        // Find the product by its name
        Product products = productRepository.findByProductName(entity.getProductName());
        if (products == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        // Update the product's stock and sold count
        if (products.getStock() < entity.getQuantity()) {
            return new ResponseEntity<>("Insufficient stock", HttpStatus.BAD_REQUEST);
        }

        // Update the product's stock
        products.setStock(products.getStock() - entity.getQuantity());
        // Update the product's sold
        products.setSold(products.getSold() + entity.getQuantity());
        productRepository.save(products);

        // Create the new inventory entry
        Sales sales = new Sales(
            entity.getDate(),
            entity.getQuantity()
        );
        sales.setProducts(Collections.singleton(products));
        salesRepository.save(sales);

        return new ResponseEntity<>("A new inventory is added. Yey!", HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public Sales updateSales(@PathVariable Long id,
    @RequestBody SalesDTO entity){
        Sales newSales = new Sales(
            entity.getDate(),
            entity.getQuantity()
        );
        Product products = productRepository.findByProductName(entity.getProductName());
        return salesRepository.findById(id)
        .map(sales ->{
            sales.setDate(newSales.getDate());
            sales.setQuantity(newSales.getQuantity());
            sales.setProducts(Collections.singleton(products));
            return salesRepository.save(sales);
        }).orElseGet(()->{
            return salesRepository.save(newSales);
        });
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteSales(@PathVariable Long id){
        salesRepository.deleteById(id);
        return "A sales is deleted!";
    }

}