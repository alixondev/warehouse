package com.example.springexam.service;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    ApiResult<List<CategoryDTO>> getAll();

    ApiResult<CategoryDTO> add(CategoryDTO categoryDTO);

    ApiResult<CategoryDTO> update(CategoryDTO categoryDTO,Integer id);



    String delete(Integer id);

}
