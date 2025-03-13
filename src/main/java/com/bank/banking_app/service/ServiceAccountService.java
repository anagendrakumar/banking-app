package com.bank.banking_app.service;

import com.bank.banking_app.entity.SavingsAccount;
import com.bank.banking_app.repository.SavingsAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountService {

   /* @Autowired
    SavingsAccountRepository savingsAccountRepository;

    public SavingsAccount createSavingAccount(SavingsAccount account){
        return savingsAccountRepository.save(account);
    }

    public void withdraw(Long id, double amount){
        SavingsAccount account= savingsAccountRepository.findById(id).orElseThrow();
        if(account.getBalance()-amount>=account.getMinimumBalance()){
            account.setBalance(account.getBalance()-amount);
            savingsAccountRepository.save(account);
        }else{
            throw  new RuntimeException("Insufficient Balance");
        }
    }*/
}
