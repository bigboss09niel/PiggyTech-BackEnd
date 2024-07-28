package com.example.piggytech.DTO;

import java.util.Date;

public class OrderDTO {

    private double totalAmount;
    private Date orderDate;
    private String username;

    // Constructors
    public OrderDTO() {}
    public OrderDTO(double totalAmount, Date orderDate, String username) {
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.username = username; // Set the email
    }

    // Setters and Getters
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
