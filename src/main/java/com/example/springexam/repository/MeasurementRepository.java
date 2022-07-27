package com.example.springexam.repository;

import com.example.springexam.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {


    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByName(String name);
}
