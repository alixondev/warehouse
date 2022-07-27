package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.CategoryDTO;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CategoryController.CATEGORY_CONTROLLER_PATH)
public interface CategoryController {

    String CATEGORY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/category/";


    @GetMapping("/get")
    ApiResult<List<CategoryDTO>> getAll();

    @PostMapping("/add")
    ApiResult<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO);

    @PutMapping("/{id}")
    ApiResult<CategoryDTO> update(@RequestBody CategoryDTO categoryDTO,@PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);

}
