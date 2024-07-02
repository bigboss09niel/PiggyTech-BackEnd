package com.example.piggytech.Model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {

    private @Id
    @GeneratedValue Long id;
    private String categoryName;
    

    Category(){}

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    // Setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    

     // Getters
     public Long getId() {
        return id;
     }
     public String getCategoryName() {
        return categoryName;
    }
   

}
   

   
