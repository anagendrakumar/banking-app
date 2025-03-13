package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String aadhaar;
    private boolean isMinor;



    /*
    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private SavingsAccount savingsAccount;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    private LoanAccount loanAccount;*/

}
