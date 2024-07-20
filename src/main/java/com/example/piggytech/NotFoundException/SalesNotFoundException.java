package com.example.piggytech.NotFoundException;

public class SalesNotFoundException extends RuntimeException {

    public SalesNotFoundException(Long id){
        super("Could not found sales with " + id);
    }

}