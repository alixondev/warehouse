package com.example.springexam.payload;

import com.example.springexam.entity.Category;
import com.example.springexam.entity.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {

    private String name;

    private Category category;

    private Integer productId;

    private Measurement measurement;
}
