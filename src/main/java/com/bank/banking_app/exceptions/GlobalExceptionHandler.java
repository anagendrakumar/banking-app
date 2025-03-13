package com.bank.banking_app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> NotFoundExceptionHandler(NotFoundException exception){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.toString()
                ,HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> InternalServerExceptionHandler(HttpServerErrorException.InternalServerError exception){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.toString()
                ,HttpStatus.INTERNAL_SERVER_ERROR.value(), "Check properly and enter values or check in Application");
        return ResponseEntity.ok().body(errorResponse);
    }

    public  ResponseEntity<ErrorResponse> CustomerAlreadyExistsExceptionHandler(CustomerAlreadyExists alreadyExists){
        String status= String.valueOf(HttpStatus.valueOf("ALREADY_EXISTS"));
        int statusCode=403;
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(),status,statusCode,"Customer Already Exists");
     return ResponseEntity.ok().body(errorResponse);
    }
}