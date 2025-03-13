package com.bank.banking_app.exceptions;

public class CustomerAlreadyExists extends RuntimeException {

    public CustomerAlreadyExists(String message) {
       super(message);
    }
}
