package com.example.piggytech.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.piggytech.Model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query("SELECT i FROM Inventory i JOIN i.product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Inventory> findByProductNameContainingIgnoreCase(@Param("productName") String productName);

}