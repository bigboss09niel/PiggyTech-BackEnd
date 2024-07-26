package com.example.piggytech.Model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_tbl")
public class Order {

    private @Id
    @GeneratedValue Long id;
    @CreationTimestamp
    private Date orderDate;
    private Double totalPrice;

    Order(){}

    // Constructors
    public Order(Date orderDate, Double totalPrice) {
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    // Setters
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice= totalPrice;
    }

     // Getters
    public Long getId() {
        return id;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }

}