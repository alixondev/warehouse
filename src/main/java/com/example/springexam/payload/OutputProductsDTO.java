package com.example.springexam.payload;

import lombok.Data;

@Data
public class OutputProductsDTO {
    private Integer productId;
    private Integer measurementId;
    private double amount;
    private double price;
    private Integer outputId;
}
