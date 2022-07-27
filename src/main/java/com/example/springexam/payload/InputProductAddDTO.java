package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProductAddDTO {

    private String name;

    private String measurement;

    private String warehouse;

    private double price;


}
