package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Object> {

}
