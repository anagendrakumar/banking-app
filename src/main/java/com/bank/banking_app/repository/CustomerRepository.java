package com.bank.banking_app.repository;

import com.bank.banking_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   Customer findByAadhaar(String aadhaar);
}
