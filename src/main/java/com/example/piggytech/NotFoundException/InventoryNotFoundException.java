package com.example.piggytech.NotFoundException;

public class InventoryNotFoundException extends RuntimeException {

    public InventoryNotFoundException(Long id){
        super("Could not found inventory with " + id);
    }

}