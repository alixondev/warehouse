package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderInfoDTO {

    private String name;

    private String phoneNumber;

    public ProviderInfoDTO(Integer id, String name, String phoneNumber) {

    }
}
