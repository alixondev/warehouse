package com.example.springexam.repository;

import com.example.springexam.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {


    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByName(String name);
}
