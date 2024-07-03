package com.example.piggytech.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Inventory {

    private @Id
    @GeneratedValue Long id;
    private Date receivedDate;
    private Date expirationDate;
    private int quantity;

    Inventory(){}

    public Inventory(Date receivedDate, Date expirationDate, int quantity) {
        this.receivedDate = receivedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    // Setters
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

     // Getters
    public Long getId() {
        return id;
    }
    public Date getReceivedDate() {
        return receivedDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public int getQuantity() {
        return quantity;
    }
   
}
   

   
