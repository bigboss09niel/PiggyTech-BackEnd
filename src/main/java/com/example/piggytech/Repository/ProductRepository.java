package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.User;

public interface ProductRepository extends JpaRepository<User, Long> {
    
}
