package com.example.springexam.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WareHouseUpdateDTO {

    private Integer id;

    private String name;

    private boolean active;
}
