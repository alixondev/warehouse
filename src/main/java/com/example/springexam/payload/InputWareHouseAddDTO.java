package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputWareHouseAddDTO {

    private Timestamp timestamp;

    private  String warehouse;

    private String provider;

    private String product;

    private String currency;

    private String factureNum;

    private Integer uniqueId;

    private String name;
}
