package com.example.springexam.repository;

import com.example.springexam.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Page<Category> findAllByNameLike(String s, Pageable pageable);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
