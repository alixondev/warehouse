package com.example.springexam.entity;


import com.example.springexam.entity.Abs.AbsEntity;
import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "attachment_content")
@FieldNameConstants
public class AttachmentContent extends AbsLongEntity {

    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Attachment attachment;

}
