package com.bank.banking_app.service;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.exceptions.CustomerAlreadyExists;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.AddressRepository;
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

    @Autowired
    private AddressRepository addressRepository;

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExists {

         customerRepository
                .findByAadhaar(customer.getAadhaar()).orElseThrow(()->
        new CustomerAlreadyExists("customer already exists in the bank, please check your details"));

             customer.getAddress().stream().forEach(address->address.setCustomer(customer));
            return customerRepository.save(customer);

    }

    public Customer getCustomer(Long id) throws NotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No fount customer details"));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        updatedCustomer.setId(customerId);
        return customerRepository.save(updatedCustomer);
    }

}
