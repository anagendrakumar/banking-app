package com.bank.banking_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonIgnore
    private Customer customer;
}
