package com.bank.banking_app.exceptions;

public class InsufficientBalance extends RuntimeException{
    private String message;
    public InsufficientBalance(final String message){
        this.message=message;
    }
}
