package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}