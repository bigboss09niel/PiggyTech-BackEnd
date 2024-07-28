package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Cart;

public interface CartRepository extends JpaRepository<Cart, Object> {

}
