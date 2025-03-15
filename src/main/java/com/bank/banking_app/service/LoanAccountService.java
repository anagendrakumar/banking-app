package com.bank.banking_app.service;

import com.bank.banking_app.entity.LoanAccount;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.LoanAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanAccountService {

    @Autowired
    private LoanAccountRepository loanAccountRepository;

    public LoanAccount openLoanAccount(LoanAccount loanAccount) {
        return loanAccountRepository.save(loanAccount);
    }

    public LoanAccount getLoanAccountById(Long loanId) throws NotFoundException {
        return loanAccountRepository.findById(loanId)
                .orElseThrow(()-> new NotFoundException("Your loan details are not found"));
    }

    public LoanAccount payEmi(Long loanId, double amount) {
        LoanAccount loanAccount = loanAccountRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));
        loanAccount.setOutstandingBalance(loanAccount.getOutstandingBalance() - amount);
        return loanAccountRepository.save(loanAccount);
    }

    public LoanAccount makeAdhocPayment(Long loanId, double amount) {
        LoanAccount loanAccount = loanAccountRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));
        loanAccount.setOutstandingBalance(loanAccount.getOutstandingBalance() - amount);
        return loanAccountRepository.save(loanAccount);
    }
}
