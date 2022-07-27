package com.example.springexam.payload;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;

    private int categoryId;

    private int  photoId;

    private int measurementId;
}
