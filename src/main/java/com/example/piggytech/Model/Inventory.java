package com.example.piggytech.Model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    private @Id
    @GeneratedValue Long id;
    private Date receivedDate;
    private Date expirationDate;
    private int quantity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_inventory",
        joinColumns = { @JoinColumn(name = "inventory_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") }
    )
    private Product product;

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
    public void setProduct(Product product) {
        this.product = product;
    }

    // Getters
    public Long getyId() {
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
    public Product getProduct() {
        return product;
    }
}
