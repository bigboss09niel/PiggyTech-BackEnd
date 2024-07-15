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

import com.example.piggytech.Model.Product;
import com.example.piggytech.NotFoundException.ProductNotFoundException;
import com.example.piggytech.Repository.ProductRepository;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }


    //GET ALL PRODUCT
    @GetMapping("/all")
    public List<Product> getProduct() {
        return repo.findAll();
    }
    
    // GET One product
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return repo.findById(id)
        .orElseThrow(()-> new ProductNotFoundException(id));
    }

    //CREATE ENDPOINTS
    @PostMapping("/new")
    public String addProduct(@RequestBody Product newProduct) {
        repo.save(newProduct);
        return "A new product is added. Yey!";
    }
    //UPDATE ENDPOINTS
    @PutMapping("/edit/{id}")
    public Product updateProduct(@PathVariable Long id, 
    @RequestBody Product newProduct) {
        return repo.findById(id)
        .map(product -> {
            product.setProductName(newProduct.getProductName());
            product.setCategoryId(newProduct.getCategoryId());
            product.setPrice(newProduct.getPrice());
            product.setStock(newProduct.getStock());
            product.setSold(newProduct.getSold());
            return repo.save(product);
        }).orElseGet(()->{
            return repo.save(newProduct);
        });
    }

    //DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        repo.deleteById(id);
        return "A product is deleted";
    }
}
