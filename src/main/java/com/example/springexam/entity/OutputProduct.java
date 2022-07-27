package com.example.springexam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Product product;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Measurement measurement;

    @Column(nullable = false,unique = true)
    private double amount;

    @Column(nullable = false,unique = true)
    private double price;

}
