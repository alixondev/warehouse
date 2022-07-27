package com.example.springexam.repository;

import com.example.springexam.entity.InputWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputWarehouseRepository extends JpaRepository<InputWarehouse,Integer> {
    boolean existsByName(String name);
}
