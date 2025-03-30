package com.bank.banking_app.controller;

import com.bank.banking_app.entity.Address;
import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.exceptions.CustomerAlreadyExists;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
     private CustomerService customerService;

    @PostMapping("/add")
     public ResponseEntity<Customer> addCustomerDetails(@RequestBody Customer customer) throws CustomerAlreadyExists {
        return  ResponseEntity.ok().body(customerService.addCustomer(customer));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getAllCustomerDetails(){
        return  ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<Customer> getByCustomerId(@PathVariable Long customerId) throws NotFoundException {
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomerDetails(@PathVariable Long customerId, @RequestBody Customer updateCustomer){
      return ResponseEntity.ok().body(customerService.updateCustomer(customerId,updateCustomer));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long customerId) throws NotFoundException {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok("Customer Account is Deleted");
    }

    @GetMapping("/{customerId}/address")
    public ResponseEntity<List<Address>> getCustomerDetails(@PathVariable Long customerId) throws NotFoundException {

        return ResponseEntity.ok(customerService.getCustomerAddress(customerId));
    }

    @PutMapping("/address/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId,@RequestBody Address address) throws NotFoundException {
        return ResponseEntity.ok(customerService.updateAddressByCustomerId(addressId,address));
    }
}
