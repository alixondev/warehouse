package com.example.springexam.payload;

import lombok.Data;

@Data
public class InputProductsDTO {

    private int id;

    private Integer productId;
    private Integer measurementId;
    private double amount;
    private double price;
    private String expireDate;//Format{"dd/MM/yy"}
    private Integer inputId;
}
