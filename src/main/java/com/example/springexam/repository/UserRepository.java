package com.example.springexam.repository;

import com.example.springexam.entity.User;
import com.example.springexam.payload.ApiResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);


    boolean existsByFirstNameAndIdNot(String name, Integer id);

    boolean existsByFirstName(String name);
}
