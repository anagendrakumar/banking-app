package com.bank.banking_app.controller;

import com.bank.banking_app.entity.LoanAccount;
import com.bank.banking_app.exceptions.InsufficientBalance;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.response.LoanAccountResponse;
import com.bank.banking_app.service.EMICalculatorService;
import com.bank.banking_app.service.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/loan-account/")
public class LoanAccountController {

    @Autowired
    private LoanAccountService loanAccountService;

    @Autowired
    private EMICalculatorService emiCalculatorService;

    @PostMapping("/new-loan/{customerId}")
    public ResponseEntity<LoanAccountResponse> newLoan(@PathVariable Long customerId, @RequestHeader double loanAmount,@RequestHeader double interestRate,@RequestHeader int tenure) throws NotFoundException {
        return ResponseEntity.ok(loanAccountService.openLoanAccount(customerId,loanAmount,interestRate,tenure));
    }

    @GetMapping("/get-loan/{loanId}")
    public ResponseEntity<LoanAccount> getLoanDetails(@PathVariable Long loanId) throws NotFoundException {
        return ResponseEntity.ok().body(loanAccountService.getLoanAccountById(loanId));
    }

    @PostMapping("/{loanId}/pay-emi")
    public ResponseEntity<LoanAccount> payEmi(@PathVariable Long loanId, @RequestHeader double amount) {
        return ResponseEntity.ok().body(loanAccountService.payEmi(loanId, amount));

    }

    @PostMapping("/{loanId}/adhoc-payment")
    public ResponseEntity<String> adhocPayment(@PathVariable Long loanId, @RequestHeader double amount) throws NotFoundException {
        try{
            loanAccountService.makeAdhocPayment(loanId, amount);
            return ResponseEntity.ok().body("Payment Successful");
        }catch (InsufficientBalance ex){
            return ResponseEntity.ok("Payment Failed");
        }

    }
    //EMI Calculator
    @GetMapping("/emi-calculate")
    public ResponseEntity<String> emiCalculator(@RequestHeader double loanAmount,@RequestHeader double interestRate,@RequestHeader int tenure)  {
        return  ResponseEntity.ok(emiCalculatorService.calculateEMI(loanAmount,interestRate,tenure));
    }

    @DeleteMapping("/delete/{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long loanId) throws NotFoundException {
        return ResponseEntity.ok(loanAccountService.deleteById(loanId));
    }
}
