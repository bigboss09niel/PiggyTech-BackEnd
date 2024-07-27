package com.example.piggytech.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.piggytech.NotFoundException.OrderItemNotFoundException;

public class OrderItemExceptionHandler {

    @ExceptionHandler(OrderItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderItemNotFoundHandler(OrderItemNotFoundException e){
        return e.getMessage();
    }

}