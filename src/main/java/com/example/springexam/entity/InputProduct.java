package com.example.springexam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "input_product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Measurement measurement;

    @ManyToOne(fetch = FetchType.LAZY)
    private InputWarehouse warehouse;

    private double price;


    public InputProduct(String name, String measurement, String warehouse, double price) {
        
    }

    public void setProduct(String name) {
        
    }

    public void setMeasurement(String measurement) {
        
    }

    public void setWarehouse(String warehouse) {
    }
}
