package com.example.springexam.repository;

import com.example.springexam.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByName(String name);

}
