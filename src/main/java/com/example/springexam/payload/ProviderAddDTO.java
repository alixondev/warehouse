package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderAddDTO {

        private Integer id;

        private String name;

        private String phoneNumber;

}
