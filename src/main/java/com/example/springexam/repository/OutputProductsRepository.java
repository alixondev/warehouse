package com.example.springexam.repository;

import com.example.springexam.entity.OutputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface OutputProductsRepository extends JpaRepository<OutputProduct,Integer> {
    Page findAllByOutputId(int id, Pageable pageable);
}
