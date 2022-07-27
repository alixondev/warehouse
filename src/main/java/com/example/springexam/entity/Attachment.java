package com.example.springexam.entity;


import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment")
@FieldNameConstants
public class Attachment extends AbsLongEntity {

    private String originalName;

    private long size;

    private String contentType;

    private String name;

    public Attachment(String originalName, long size, String contentType) {
        this.originalName = originalName;
        this.size = size;
        this.contentType = contentType;
    }
}
