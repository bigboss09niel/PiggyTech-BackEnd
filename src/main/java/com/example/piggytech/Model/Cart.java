package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

    private @Id
    @GeneratedValue Long id;
    private Long quantity;
    
    //constructors
    public Cart(Long quantity) {
        this.quantity = quantity;
    }

    //setters
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    //getters
    public Long getId() {
        return id;
    }
    public Long getQuantity() {
        return quantity;
    }
}
