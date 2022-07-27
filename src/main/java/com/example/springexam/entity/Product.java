package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="product")
@SQLDelete(sql = "update product set deleted = true where id=?")
@Where(clause = "deleted=false")
public class Product extends AbsLongEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

    private Long productUniqueId;


    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Measurement measurement;

    public Product(Integer productId, String name, Measurement measurement, Category category) {

    }
}
