package com.example.piggytech.NotFoundException;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(Long id){
        super("Could not found order item with " + id);
    }

}
