package com.example.springexam.service;

import com.example.springexam.payload.*;

import java.util.List;

public interface ProductService {
    ApiResult<List<ProductInfoDTO>> getAll();

    ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO);

    ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id);

    String delete(Integer id);
}
