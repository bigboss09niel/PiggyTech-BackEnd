package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
