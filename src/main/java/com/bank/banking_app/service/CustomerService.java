package com.bank.banking_app.service;

import com.bank.banking_app.entity.Address;
import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.exceptions.CustomerAlreadyExists;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.AddressRepository;
import com.bank.banking_app.repository.CustomerRepository;
import com.bank.banking_app.validations.Validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    Validations validations;

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExists {
        Optional<Customer> customerAlreadyExists=customerRepository.findByAadhaar(customer.getAadhaar());
        if(customer.isMinor())
            throw new RuntimeException("Please fill in the Joint Account");

       //if customer already presents means its throws an error
        if(customerAlreadyExists.isPresent())
            throw new CustomerAlreadyExists("Customer already exists on Aadhaar "+customer.getAadhaar()+
                    ", please check and enter correctly");

        boolean isEmailCheck=Validations.isEmailValidation(customer.getEmail());
         if(!isEmailCheck)
             throw new RuntimeException("Please enter correct Email");

        boolean isAadhaarCheck= Validations.isAadhaarValidation(customer.getAadhaar());
          if(!isAadhaarCheck)
              throw new RuntimeException("Please enter correct Aadhaar details");

          boolean isPhoneCheck=Validations.isMobileValidation(customer.getPhone());
           if(!isPhoneCheck)
              throw new RuntimeException("Please enter correct phone number");

        customer.getAddress().forEach(address->address.setCustomer(customer));
            return customerRepository.save(customer);

    }

    public Customer getCustomer(Long customerId) throws NotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new NotFoundException("Customer details are not found on this id: "+customerId));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer)
    {
        Customer customer=customerRepository.findById(customerId).get();

        if(updatedCustomer.getEmail()==null)
            updatedCustomer.setEmail(customer.getEmail());
        if(updatedCustomer.getPhone()==null)
            updatedCustomer.setPhone(customer.getPhone());
        if(updatedCustomer.getName()==null)
            updatedCustomer.setName(customer.getName());
        if(updatedCustomer.getDateOfBirth()==null)
            updatedCustomer.setDateOfBirth(customer.getDateOfBirth());
        if(updatedCustomer.getAadhaar()==null)
            updatedCustomer.setAadhaar(customer.getAadhaar());
        if(updatedCustomer.getAddress()==null)
            updatedCustomer.setAddress(customer.getAddress());
        if(updatedCustomer.getLoanAccounts()==null)
            updatedCustomer.setLoanAccounts(customer.getLoanAccounts());
        if(updatedCustomer.getSavingsAccount()==null)
            updatedCustomer.setSavingsAccount(customer.getSavingsAccount());
        updatedCustomer.setCustomerId(customerId);
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomerById(Long customerId) throws NotFoundException {
        customerRepository.findById(customerId).orElseThrow(
                ()-> new NotFoundException("Customer is not found on this id: "+customerId)
        );
        customerRepository.deleteById(customerId);
    }

    //customer address will get from this service
    public List<Address> getCustomerAddress(Long customerId) throws NotFoundException {
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->
                new NotFoundException("Customer details are not found on this id: "+customerId));
        if(customer.getAddress()==null)
            throw new NotFoundException("Customer address is not found");

        return customer.getAddress();
    }

}
