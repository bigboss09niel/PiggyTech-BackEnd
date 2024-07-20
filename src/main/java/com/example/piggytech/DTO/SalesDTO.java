package com.example.piggytech.DTO;

import java.util.Date;

public class SalesDTO {

    private String productName;
    private Date date;
    private int quantity;

    SalesDTO(){}

    // Constructors
    public SalesDTO(String productName, Date date, int quantity) {
        this.productName = productName;
        this.date = date;
        this.quantity = quantity;
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters
    public String getProductName() {
        return productName;
    }
    public Date getDate() {
        return date;
    }
    public int getQuantity() {
        return quantity;
    }

}