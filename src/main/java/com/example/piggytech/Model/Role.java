package com.example.piggytech.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    Role(){}

    // Constructors
    public  Role(String name){
        this.name = name;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    //getters
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}