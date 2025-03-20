package com.bank.banking_app.exceptions;


import org.apache.coyote.BadRequestException;
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
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(),  exception.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorResponse> InternalServerExceptionHandler(HttpServerErrorException.InternalServerError exception){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), "Check properly and enter values or check in Application");
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = CustomerAlreadyExists.class)
    public  ResponseEntity<ErrorResponse> CustomerAlreadyExistsExceptionHandler(CustomerAlreadyExists alreadyExists){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), alreadyExists.getMessage());
     return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(value = InsufficientBalance.class)
    public ResponseEntity<ErrorResponse> InsufficientBalanceExceptionHandler(InsufficientBalance insufficientBalance){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),
                insufficientBalance.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public  ResponseEntity<ErrorResponse> BadRequestExceptionHandler(BadRequestException ex){
        ErrorResponse errorResponse= new ErrorResponse(LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.ok().body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> RuntimeExceptionHandler(RuntimeException ex){
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),ex.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

}