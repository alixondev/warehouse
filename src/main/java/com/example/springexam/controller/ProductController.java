package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ProductController.PRODUCT_CONTROLLER_PATH)
public interface ProductController {

    String PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH +"/product";

    @GetMapping("/get")
    ApiResult<List<ProductInfoDTO>> getAll();

    @PostMapping("/add")
    ApiResult<ProductInfoDTO> add(@RequestBody ProductAddDTO productAddDTO);

    @PutMapping("/{id}")
    ApiResult<ProductInfoDTO> update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);
}
