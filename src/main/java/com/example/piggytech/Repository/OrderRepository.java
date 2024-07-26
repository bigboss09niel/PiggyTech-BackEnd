package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}