package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/*
* Tenure will be in months
* emiAmount will be monthly pay amount
* loanAmount will be sanctioned amount
* outstandingBalance will be how much remaining we have to pay
*
*/

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanAccount extends BaseAccount{


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

    @JsonIgnore
    private double balance;

}
