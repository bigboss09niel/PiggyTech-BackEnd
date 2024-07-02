package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

    private @Id
    @GeneratedValue Long id;
    private String productName;
    private Long categoryId;
    private Double price;
    private Long stock;
    private Long sold;

    Product(){}

    public Product(String productName, Long categoryId, Double price ,Long stock, Long sold) {
        this.productName = productName;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
       
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }
    public void setSold(Long sold) {
        this.sold = sold;
    }

     // Getters
     public Long getId() {
        return id;
     }
     public String getProductName() {
        return productName;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public Double getPrice() {
        return price;
    }
    public Long getStock() {
        return stock;
    }
    public Long getSold() {
        return sold;
    }

}
   

   
