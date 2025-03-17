package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/*
 * Default minimum balance will be 100
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long accountId;

    @JsonProperty
    private double balance;
    @JsonProperty
    private double minimumBalance = 500.0;

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "savingsAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transactions> transactions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
