package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String transactionType; // Deposit, Withdrawal, EMI Payment, Adhoc Payment
    private double amount;
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    @JsonIgnore
    private SavingsAccount savingsAccount;

    @ManyToOne
    @JoinColumn(name = "loan_account_id")
    @JsonIgnore
    private LoanAccount loanAccount;
}
