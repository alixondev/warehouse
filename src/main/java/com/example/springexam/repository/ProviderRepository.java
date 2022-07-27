package com.example.springexam.repository;

import com.example.springexam.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProviderRepository extends JpaRepository<Provider,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNameAndIdNot(String name, Integer id);

    boolean existsByName(String name);
}
