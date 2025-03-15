package com.bank.banking_app.service;

import org.springframework.stereotype.Service;

@Service
public class EMICaluculatorService {

    public double calculateEMI(double loanAmount, double interestRate, int tenure) {
        double monthlyInterestRate = interestRate / 12 / 100;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure)) /
                (Math.pow(1 + monthlyInterestRate, tenure) - 1);
        return emi;
    }
}
