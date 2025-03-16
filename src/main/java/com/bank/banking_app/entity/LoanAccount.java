package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* Tenure will be in months
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private double loanAmount;
    private int tenure; // In months
    private double interestRate;
    private double emiAmount;
    private double outstandingBalance;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "loanAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transactions> transactions;

}
