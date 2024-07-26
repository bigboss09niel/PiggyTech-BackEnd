package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderItem")
public class OrderItem {

    private @Id
    @GeneratedValue Long id;
    private Long quantity;
    private Double price;


    OrderItem(){}

    //Constructors
    public OrderItem(Long quantity, Double price) {
        this.quantity = quantity;
        this.price = price;
    }

    //setters
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    //getters
    public Long getId() {
        return id;
    }


    public Long getQuantity() {
        return quantity;
    }


    public Double getPrice() {
        return price;
    }
}
