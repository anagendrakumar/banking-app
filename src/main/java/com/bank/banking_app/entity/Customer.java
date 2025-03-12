package com.bank.banking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private SavingsAccount savingsAccount;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private LoanAccount loanAccount;

}
