package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Inventory {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private String receivedDate;
    private ]String expirationDate;
    private String quantity;

   Inventory(){}

    public Inventory(String name, String receivedDate,String expirationDate ,String quantity) {
        this.name = name;
        this.receivedDate = receivedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
       
       
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
   

   
