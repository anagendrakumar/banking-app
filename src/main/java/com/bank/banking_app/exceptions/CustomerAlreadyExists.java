package com.bank.banking_app.exceptions;

public class CustomerAlreadyExists extends Exception {

    public CustomerAlreadyExists(String message) {
       super(message);
    }
}
