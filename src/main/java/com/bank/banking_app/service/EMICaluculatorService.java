package com.bank.banking_app.service;

import org.springframework.stereotype.Service;

@Service
public class EMICaluculatorService {

    public String calculateEMI(double loanAmount, double interestRate, int tenure)  {
        double monthlyInterestRate = interestRate / 12 / 100;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenure)) /
                (Math.pow(1 + monthlyInterestRate, tenure) - 1);
        double finalEmi= (double) Math.round(emi*100)/100;
        return "Loan Amount:"+loanAmount+" Interest Rate:"+interestRate+
                " Tenure (in months):"+tenure+ " Your Monthly EMI will be "+finalEmi +
                " per month";
    }
}
