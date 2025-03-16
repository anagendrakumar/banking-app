package com.bank.banking_app.service;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.entity.SavingsAccount;
import com.bank.banking_app.entity.Transactions;
import com.bank.banking_app.exceptions.InsufficientBalance;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.CustomerRepository;
import com.bank.banking_app.repository.SavingsAccountRepository;
import com.bank.banking_app.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SavingsAccountService {

    @Autowired
    SavingsAccountRepository savingsAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

   /*
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
   public SavingsAccount openAccount(Long customerId, SavingsAccount account) {
       Customer customer=customerRepository.findById(customerId).get();
       account.setCreatedAt(LocalDateTime.now());
       account.setCustomer(customer);
       return savingsAccountRepository.save(account);
   }

    public SavingsAccount getAccountById(Long accountId) throws NotFoundException {
        return savingsAccountRepository
                .findById(accountId).orElseThrow(()->new NotFoundException("Your account not Exists"));
    }

    public SavingsAccount deposit(Long accountId, double amount) throws NotFoundException {
        SavingsAccount account = savingsAccountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        Transactions transactions=new Transactions();
         transactions.setAmount(amount);
         transactions.setTransactionDate(LocalDateTime.now());
         transactions.setSavingsAccount(account);
         transactions.setTransactionType("Deposit");
         transactionsRepository.save(transactions);
        return savingsAccountRepository.save(account);
    }

    public SavingsAccount withdraw(Long accountId, double amount) throws NotFoundException,InsufficientBalance {
        SavingsAccount account = savingsAccountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        if ((account.getBalance() - amount) < account.getMinimumBalance()) {
            throw new InsufficientBalance("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        Transactions transactions=new Transactions();
        transactions.setAmount(amount);
        transactions.setTransactionDate(LocalDateTime.now());
        transactions.setSavingsAccount(account);
        transactions.setTransactionType("Withdraw");
        transactionsRepository.save(transactions);
        return savingsAccountRepository.save(account);
    }
}
