package com.bank.banking_app.service;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.exceptions.CustomerAlreadyExists;
import com.bank.banking_app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer){

        Customer customerAlreadyExists= customerRepository.findByAadhaar(customer.getAadhaar());
        if(customerAlreadyExists!=null)
            throw new CustomerAlreadyExists("customer already exists in the bank, please check your details");
        else
          return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id).get();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

}
