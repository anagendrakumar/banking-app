package com.bank.banking_app.repository;

import com.bank.banking_app.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanAccountRepository extends JpaRepository<Long, LoanAccount> {
}
