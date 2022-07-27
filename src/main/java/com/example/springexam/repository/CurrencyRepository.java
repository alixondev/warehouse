package com.example.springexam.repository;


import com.example.springexam.entity.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;

public interface CurrencyRepository extends JpaRepository<CurrencyType,Integer> {




    boolean existsByName(String name);
}
