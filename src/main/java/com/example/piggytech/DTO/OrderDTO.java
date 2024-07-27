package com.example.piggytech.DTO;

import java.util.Date;

public class OrderDTO {

    private double totalAmount;
    private Date orderDate;
    private String email; // Change from Long userAuthId to String email

    // Constructors
    public OrderDTO() {}
    public OrderDTO(double totalAmount, Date orderDate, String email) {
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.email = email; // Set the email
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
