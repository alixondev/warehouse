package com.example.springexam.entity;

import com.example.springexam.entity.Abs.AbsLongEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Objects;


@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="category")
@SQLDelete(sql = "update category set deleted = true where id=?")
@Where(clause = "deleted=false")
public class Category extends AbsLongEntity {

  private String name;

  private boolean active;

  public Category(String name){
    this.name=name;
  }


  public Category(Integer id, String name, boolean active) {

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Category category = (Category) o;
    return getId() != null && Objects.equals(getId(), category.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
