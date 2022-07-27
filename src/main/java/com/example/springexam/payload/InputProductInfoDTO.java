package com.example.springexam.payload;

import com.example.springexam.entity.InputWarehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductInfoDTO {

    private String name;

    private String measurement;

    private String warehouse;

    private double price;

    public InputProductInfoDTO(Integer id, String name, double price, String name1, InputWarehouse warehouse) {

    }
}
