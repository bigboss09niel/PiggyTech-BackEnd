package com.example.piggytech.DTO;

import java.util.Date;

public class InventoryDTO {

    private String productName;
    private Date receivedDate;
    private Date expirationDate;
    private int quantity;

    InventoryDTO(){}

    // Constructors
    public InventoryDTO(String productName, Date receivedDate, Date expirationDate, int quantity) {
        this.productName = productName;
        this.receivedDate = receivedDate;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getters
    public String getProductName() {
        return productName;
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