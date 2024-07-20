package com.example.piggytech.NotFoundException;

public class UserDetailNotFoundException extends RuntimeException{

    public UserDetailNotFoundException(Long id){
        super("Could not found user detail with " + id);
    }

}