package com.example.piggytech.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.piggytech.NotFoundException.UserDetailNotFoundException;

@RestControllerAdvice
public class UserDetailExceptionHandler {

    @ExceptionHandler(UserDetailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userDetailNotFoundHandler(UserDetailNotFoundException e){
        return e.getMessage();
    }

}
