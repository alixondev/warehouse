package com.example.springexam.controller;

import com.example.springexam.payload.*;

import com.example.springexam.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductService {

    private final ProductService productService;


    @Override
    public ApiResult<List<ProductInfoDTO>> getAll() {
        return productService.getAll();
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        return productService.add(productAddDTO);
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id) {
        return productService.update(productUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return productService.delete(id);
    }
}
