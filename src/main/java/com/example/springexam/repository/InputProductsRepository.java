package com.example.springexam.repository;

import com.example.springexam.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.awt.print.Pageable;

public interface InputProductsRepository extends JpaRepository<InputProduct, Integer> {

    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByName(String name);
}
