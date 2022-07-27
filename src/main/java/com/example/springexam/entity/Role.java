package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import com.example.springexam.enums.PermissionEnum;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Data
@Entity(name = "role")
public class Role extends AbsLongEntity {

    private String name;

    private String description;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PermissionEnum> permissions;
}
