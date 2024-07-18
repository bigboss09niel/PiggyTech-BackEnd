package com.example.piggytech.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

    private @Id
    @GeneratedValue Long id;
    private Long productId;
    private Date date;
    private int quantity;

    Sales(){}

    public Sales(Long productId, Date date, int quantity) {
        this.productId = productId;
        this.date = date;
        this.quantity = quantity;
    }

    // Setters
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public void setDate(Date date) {
        this.date =date;
    }
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

     // Getters
    public Long getId() {
        return id;
    }
    public Long getProductId() {
        return productId;
    }
    public Date getDate() {
        return date;
    }
    public int getQuantity() {
        return quantity;
    }
   
}
   

   
