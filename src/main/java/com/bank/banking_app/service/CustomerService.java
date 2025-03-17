package com.bank.banking_app.service;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.exceptions.CustomerAlreadyExists;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.AddressRepository;
import com.bank.banking_app.repository.CustomerRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Customer addCustomer(Customer customer) throws CustomerAlreadyExists {
        Optional<Customer> customerAlreadyExists=customerRepository.findByAadhaar(customer.getAadhaar());
       //if customer already presents means its throws an error
        if(customerAlreadyExists.isPresent())
            throw new CustomerAlreadyExists("Customer already exists on Aadhaar "+customer.getAadhaar()+
                    ", please check and enter correctly");

        boolean isAadhaarCheck= isAadhaarValidation(customer.getAadhaar());
          if(!isAadhaarCheck)
              throw new RuntimeException("Please enter correct Aadhaar details");

        customer.getAddress().stream().forEach(address->address.setCustomer(customer));
            return customerRepository.save(customer);

    }

    public Customer getCustomer(Long id) throws NotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("No found customer details"));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        updatedCustomer.setId(customerId);
        return customerRepository.save(updatedCustomer);
    }

    public void deleteCustomerById(Long customerId) throws NotFoundException {
        customerRepository.findById(customerId).orElseThrow(
                ()-> new NotFoundException("Customer is not found")
        );
        customerRepository.deleteById(customerId);
    }

    //Validations

    private boolean isAadhaarValidation(String aadhaar) {
        String regex= "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";
        Pattern p= Pattern.compile(regex);
        if(aadhaar==null)
            return false;
        Matcher m=p.matcher(aadhaar);
        return m.matches();
    }
}
