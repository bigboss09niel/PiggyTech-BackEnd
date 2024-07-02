package com.example.piggytech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.piggytech.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
