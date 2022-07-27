package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Provider extends AbsLongEntity {

    private String name;

    @Column(nullable = false,unique = true)
    private String phoneNumber;


}
