package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="measurement")
@SQLDelete(sql = "update measurement set deleted = true where id=?")
@Where(clause = "deleted=false")
public class Measurement extends AbsLongEntity {

    private String name;

    private boolean active;

    public Measurement(Integer id, String name, boolean active) {

    }
}
