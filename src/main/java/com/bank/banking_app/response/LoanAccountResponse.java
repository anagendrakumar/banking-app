package com.bank.banking_app.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccountResponse {

    private double loanAmount;
    private int tenure; // In months
    private double interestRate;
    private double emiAmount;
    private double outstandingBalance;
}
