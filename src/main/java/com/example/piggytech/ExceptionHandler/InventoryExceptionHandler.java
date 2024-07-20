package com.example.piggytech.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.piggytech.NotFoundException.InventoryNotFoundException;

@RestControllerAdvice
public class InventoryExceptionHandler {

    @ExceptionHandler(InventoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String inventoryNotFoundHandler(InventoryNotFoundException e){
        return e.getMessage();
    }

}