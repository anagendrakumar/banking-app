package com.bank.banking_app.controller;

import com.bank.banking_app.entity.LoanAccount;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.service.EMICaluculatorService;
import com.bank.banking_app.service.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/loanaccount/")
public class LoanAccountController {

    @Autowired
    private LoanAccountService loanAccountService;

    @Autowired
    private EMICaluculatorService emiCaluculatorService;

    @PostMapping("/newloan/{customerId}")
    public ResponseEntity<LoanAccount> newLoan(@PathVariable Long customerId, @RequestBody LoanAccount loanAccount) throws NotFoundException {
        return ResponseEntity.ok(loanAccountService.openLoanAccount(customerId,loanAccount));
    }

    @GetMapping("/getloan/{loanId}")
    public ResponseEntity<LoanAccount> getLoanDetails(@PathVariable Long loanId) throws NotFoundException {
        return ResponseEntity.ok().body(loanAccountService.getLoanAccountById(loanId));
    }

    @PostMapping("/pay/emi/{loanId}/{amount}")
    public ResponseEntity<LoanAccount> payEmi(@PathVariable Long loanId, @PathVariable double amount) {
        return ResponseEntity.ok().body(loanAccountService.payEmi(loanId, amount));

    }

    @PostMapping("/pay/adhoc/{loanId}/{amount}")
    public ResponseEntity<LoanAccount> adhocPayment(@PathVariable Long loanId, @PathVariable double amount) throws NotFoundException {
        return ResponseEntity.ok().body(loanAccountService.makeAdhocPayment(loanId, amount));
    }
    //EMI Calculator
    @GetMapping("/calculate/emi/{loanAmount}/{interestRate}/{tenure}")
    public ResponseEntity<String> emiCalculator(@PathVariable double loanAmount,@PathVariable double interestRate,@PathVariable int tenure)  {
        return  ResponseEntity.ok(emiCaluculatorService.calculateEMI(loanAmount,interestRate,tenure));
    }

    @DeleteMapping("/delete/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId){
        return ResponseEntity.ok(loanAccountService.deleteById(loanId));
    }
}
