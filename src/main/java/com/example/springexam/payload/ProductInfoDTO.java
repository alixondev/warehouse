package com.example.springexam.payload;

import com.example.springexam.entity.Attachment;
import com.example.springexam.entity.Category;
import com.example.springexam.entity.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDTO {

    private String name;
    private Category category;
    private String photo;
    private Attachment productId;

    private Measurement measurement;

    public ProductInfoDTO(String name, Category category, Category photo, Attachment productId, Measurement measurement) {
        this.name = name;
        this.category = category;
        this.photo = String.valueOf(photo);
        this.productId = productId;
        this.measurement = measurement;
    }
}
