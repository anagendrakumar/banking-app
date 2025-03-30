package com.bank.banking_app.service;

import com.bank.banking_app.entity.Customer;
import com.bank.banking_app.entity.LoanAccount;
import com.bank.banking_app.entity.SavingsAccount;
import com.bank.banking_app.entity.Transactions;
import com.bank.banking_app.exceptions.InsufficientBalance;
import com.bank.banking_app.exceptions.NotFoundException;
import com.bank.banking_app.repository.CustomerRepository;
import com.bank.banking_app.repository.LoanAccountRepository;
import com.bank.banking_app.repository.SavingsAccountRepository;
import com.bank.banking_app.repository.TransactionsRepository;
import com.bank.banking_app.response.LoanAccountResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@Service
public class LoanAccountService {

    @Autowired
    private LoanAccountRepository loanAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LoanAccountResponse openLoanAccount(Long customerId, double loanAmount, double interestRate,int tenure) throws NotFoundException {
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(() ->new NotFoundException("Customer Not Found"));
        LoanAccount loanAccount= new LoanAccount(loanAmount,tenure,interestRate);
        loanAccount.setCustomer(customer);
        double monthlyInterestRate = loanAccount.getInterestRate() / 12 / 100;
        double emi = (loanAccount.getLoanAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanAccount.getTenure())) /
                (Math.pow(1 + monthlyInterestRate, loanAccount.getTenure()) - 1);
        loanAccount.setEmiAmount((double) Math.round(emi*100)/100);
        loanAccount.setOutstandingBalance(loanAccount.getLoanAmount());
        loanAccount.setCreatedAt(LocalDateTime.now());
         LoanAccount saved=loanAccountRepository.save(loanAccount);
        LoanAccountResponse response=new LoanAccountResponse();
         modelMapper.map(saved,response);
        return response;
    }

    public LoanAccount getLoanAccountById(Long loanId) throws NotFoundException {
        return loanAccountRepository.findById(loanId)
                .orElseThrow(()-> new NotFoundException("Your loan details are not found"));
    }

    public LoanAccount payEmi(Long loanId, double amount) {
        LoanAccount loanAccount = loanAccountRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan account not found"));
        loanAccount.setOutstandingBalance(loanAccount.getOutstandingBalance() - amount);
        Transactions transactions=new Transactions(LocalDateTime.now(),
                "EMI Payment","SUCCESS",amount);
        transactions.setLoanAccount(loanAccount);
        transactionsRepository.save(transactions);
        return loanAccountRepository.save(loanAccount);
    }

    public void makeAdhocPayment(Long loanId, double amount) throws NotFoundException {
        LoanAccount loanAccount = loanAccountRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan account not found"));
        SavingsAccount savingsAccount=loanAccount.getCustomer().getSavingsAccount();
        if(savingsAccount.getBalance()<amount) {
            Transactions transactions=new Transactions(LocalDateTime.now(),
                    "ADHOC Payment","FAILED",amount);
            transactions.setLoanAccount(loanAccount);
            transactionsRepository.save(transactions);
            throw new InsufficientBalance("Funds are not sufficient to make payment");
        }
        savingsAccount.setBalance(savingsAccount.getBalance()-amount);
        savingsAccountRepository.save(savingsAccount);
        loanAccount.setOutstandingBalance(loanAccount.getOutstandingBalance() - amount);
        Transactions transactions=new Transactions(LocalDateTime.now(),
                "ADHOC Payment","SUCCESS",amount);
        transactions.setLoanAccount(loanAccount);
        transactionsRepository.save(transactions);
        loanAccountRepository.save(loanAccount);
    }

    public String deleteById(Long loanId) throws NotFoundException {
        LoanAccount loanAccount=loanAccountRepository.findById(loanId).orElseThrow(
                ()-> new NotFoundException("Loan Account is not found on this id: "+loanId)
        );
        if(loanAccount.getOutstandingBalance()==0)
         loanAccountRepository.deleteById(loanId);
        else
            return "Please pay remaining pending loan amount: "+loanAccount.getOutstandingBalance()+", then your loan will be cleared. Thanks!!";
        return "Loan is cleared";
    }
}
