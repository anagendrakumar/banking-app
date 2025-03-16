package com.bank.banking_app.service;

import org.springframework.stereotype.Service;

@Service
public class EMICaluculatorService {

    public String calculateEMI(double loanAmount, double interestRate, int tenure)  {
        double monthlyInterestRate = interestRate / 12 / 100;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure)) /
                (Math.pow(1 + monthlyInterestRate, tenure) - 1);
        double finalEmi= (double) Math.round(emi*100)/100;
        return "Loan Amount:"+loanAmount+ System.lineSeparator()+
                "Interest Rate:"+interestRate+ System.lineSeparator()+
                "Tenure (in months):"+tenure+ System.lineSeparator()+
                "Your Monthly EMI will be "+finalEmi;
    }
}
