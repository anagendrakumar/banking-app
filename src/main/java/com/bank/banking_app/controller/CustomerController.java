package com.bank.banking_app.controller;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
     private CustomerService customerService;

    @PostMapping("/add")
     public ResponseEntity<Customer> addCustomerDetails(@RequestBody Customer customer){
        return  ResponseEntity.ok().body(customerService.addCustomer(customer));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getAllCustomerDetails(){
        return  ResponseEntity.ok(customerService.getAllCustomers());
    }
}
