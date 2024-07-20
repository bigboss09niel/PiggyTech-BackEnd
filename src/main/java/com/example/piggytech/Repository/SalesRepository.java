package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Long> {

}