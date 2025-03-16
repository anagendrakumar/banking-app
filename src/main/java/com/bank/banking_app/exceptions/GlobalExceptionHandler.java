package com.bank.banking_app.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> NotFoundExceptionHandler(NotFoundException exception){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.toString()
                ,HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> InternalServerExceptionHandler(HttpServerErrorException.InternalServerError exception){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.toString()
                ,HttpStatus.INTERNAL_SERVER_ERROR.value(), "Check properly and enter values or check in Application");
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = CustomerAlreadyExists.class)
    public  ResponseEntity<ErrorResponse> CustomerAlreadyExistsExceptionHandler(CustomerAlreadyExists alreadyExists){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), HttpStatus.valueOf("ALREADY_EXISTS").toString(), HttpStatus.valueOf(403).value(), alreadyExists.getMessage());
     return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = InsufficientBalance.class)
    public ResponseEntity<ErrorResponse> InsufficientBalanceExceptionHandler(InsufficientBalance insufficientBalance){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),HttpStatus.valueOf("INSUFFICIENT_FUNDS").toString(),HttpStatus.valueOf(406).value(),
                insufficientBalance.getMessage());
        return ResponseEntity.ok(errorResponse);
    }
}