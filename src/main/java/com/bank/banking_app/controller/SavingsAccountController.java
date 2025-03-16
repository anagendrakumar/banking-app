package com.bank.banking_app.controller;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.entity.SavingsAccount;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.SavingsAccountRepository;
import com.bank.banking_app.service.SavingsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savingsaccount")
public class SavingsAccountController {

    @Autowired
    SavingsAccountService savingsAccountService;
  @PostMapping("/save/{customerId}")
  public ResponseEntity<SavingsAccount> newAccount(@PathVariable Long customerId, @RequestBody SavingsAccount savingsAccount){
      return ResponseEntity.ok().body(savingsAccountService.openAccount(customerId,savingsAccount));
  }

  @GetMapping("/get/{accountId}")
  public ResponseEntity<SavingsAccount> getAccountDetails(@PathVariable Long accountId) throws NotFoundException {
      return ResponseEntity.ok().body(savingsAccountService.getAccountById(accountId));
  }
    @PostMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity<SavingsAccount> deposit(@PathVariable Long accountId,@PathVariable double amount) throws NotFoundException {
      return ResponseEntity.ok(savingsAccountService.deposit(accountId,amount));
    }

    @PostMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity<SavingsAccount> withdraw(@PathVariable Long accountId,@PathVariable double amount) throws NotFoundException {
        return ResponseEntity.ok(savingsAccountService.withdraw(accountId,amount));
    }
}
