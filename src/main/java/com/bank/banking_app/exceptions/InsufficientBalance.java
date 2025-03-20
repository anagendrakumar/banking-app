package com.bank.banking_app.exceptions;

public class InsufficientBalance extends RuntimeException{
    public InsufficientBalance(String message){
        super(message);
    }
}
