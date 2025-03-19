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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_seq")
    @SequenceGenerator(name = "payment_seq", sequenceName = "payment_sequence", allocationSize = 1)
    private Long transactionId;

    private LocalDateTime transactionDate;
    private String transactionType; // Deposit, Withdrawal, EMI Payment, Adhoc Payment
    private String transactionStatus;
    private double amount;


    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    @JsonIgnore
    private SavingsAccount savingsAccount;

    @ManyToOne
    @JoinColumn(name = "loan_account_id")
    @JsonIgnore
    private LoanAccount loanAccount;
}
