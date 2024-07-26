package com.example.piggytech.Model;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales")
public class Sales {

    private @Id
    @GeneratedValue Long id;
    @CreationTimestamp
    private Date date;
    private Double totalPrice;

    // Defining the many-to-many relationship with Product
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_sales",
        joinColumns = { @JoinColumn(name = "sales_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") }
    )
    private Set<Product> products;

    Sales(){}

    // Constructors
    public Sales(Date date, Double totalPrice) {
        this.date = date;
        this.totalPrice = totalPrice;
    }

    // Setters
    public void setDate(Date date) {
        this.date =date;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice= totalPrice;
    }
    public void setProducts(Set<Product> products) {
        this.products = products;
    }

     // Getters
    public Long getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public Set<Product> getProducts() {
        return products;
    }

}