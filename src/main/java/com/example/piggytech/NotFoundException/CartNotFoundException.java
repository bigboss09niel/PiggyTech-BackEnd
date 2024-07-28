package com.example.piggytech.NotFoundException;

public class CartNotFoundException extends RuntimeException {

    
    public CartNotFoundException(Long id){
        super("Could not found cart with " + id);
    }

}
