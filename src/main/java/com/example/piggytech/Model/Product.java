package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    private @Id
    @GeneratedValue Long id;
    private String productName;
    private Double price;
    private Long stock;
    private Long sold;
    private String photo;

    Product(){}

    // Constructors
    public Product(String productName, Double price ,Long stock, Long sold, String photo) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.sold = sold;
        this.photo = photo;
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
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
    public void setPhoto(String photo) {
        this.photo = photo;
    }

     // Getters
    public Long getId() {
        return id;
    }
    public String getProductName() {
        return productName;
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
    public String getPhoto() {
        return photo;
    }

}