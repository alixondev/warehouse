package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "warehouse")
@SQLDelete(sql = "update warehouse set deleted = true where id=?")
@Where(clause = "deleted=false")
public class Warehouse extends AbsLongEntity {

    private String name;

    private boolean active;
}
