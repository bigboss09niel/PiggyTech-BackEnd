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
    private Long stock = 0L;
    private Long sold = 0L;
    private String photo = "https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg";

    Product(){}

    // Constructors
    public Product(String productName, Double price ,Long stock, Long sold, String photo) {
        this.productName = productName;
        this.price = price;
        this.stock = (stock != null) ? stock : 0L; // Default to 0 if null
        this.sold = (sold != null) ? sold : 0L; // Default to 0 if null
        this.photo = (photo != null && !photo.isEmpty()) ? photo : "https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg";
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setStock(Long stock) {
        this.stock = stock != null ? stock : 0L;
    }
    public void setSold(Long sold) {
        this.sold = sold != null ? sold : 0L;
    }
    public void setPhoto(String photo) {
        this.photo = (photo != null && !photo.isEmpty()) ? photo : "https://cdn.vectorstock.com/i/1000v/09/60/piggy-vector-2900960.jpg";
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