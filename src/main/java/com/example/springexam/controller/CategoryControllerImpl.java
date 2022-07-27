package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.CategoryDTO;
import com.example.springexam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController{

    private final CategoryService categoryService;

    @Override
    public ApiResult<List<CategoryDTO>> getAll() {
        return  categoryService.getAll();
    }

    @Override
    public ApiResult<CategoryDTO> add(CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }

    @Override
    public ApiResult<CategoryDTO> update(CategoryDTO categoryDTO, Integer id) {
        return categoryService.update(categoryDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return categoryService.delete(id);
    }
}
