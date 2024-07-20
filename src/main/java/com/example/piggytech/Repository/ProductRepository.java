package com.example.piggytech.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContainingIgnoreCase(String name);
    Product findByProductName(String productName);

}