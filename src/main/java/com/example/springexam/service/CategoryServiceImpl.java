package com.example.springexam.service;

import com.example.springexam.entity.Category;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.CategoryDTO;
import com.example.springexam.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{


    private final CategoryRepository categoryRepository;


    @Override
    public ApiResult<List<CategoryDTO>> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryInfoDTOList = categoryList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(categoryInfoDTOList);
    }




    @Override
    public ApiResult<CategoryDTO> add(CategoryDTO categoryDTO) {
        checkExistingName(categoryDTO.getName());
        Category category = new Category(
                categoryDTO.getId(),
              categoryDTO.getName(),
                categoryDTO.isActive()
        );
            categoryRepository.save(category);
            return returnApiResult(category,true,"success");
            }

    @Override
    public ApiResult<CategoryDTO> update(CategoryDTO categoryDTO, Integer id) {
        checkExistingName(categoryDTO.getName(),id);
        Category category = getByIdOrElseThrow(id);
        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.isActive());
        categoryRepository.save(category);
        return returnApiResult(category,true,"success");
    }


    @Override
    public String delete(Integer id) {
     Category category = getByIdOrElseThrow(id);
        categoryRepository.delete(category);
        return "success";
    }




    private CategoryDTO mapInfoDTO(Category category) {

        return new CategoryDTO(
                Math.toIntExact(category.getId()),
                category.getName(),
                category.isActive()
        );

    }
    private void checkExistingName(String name, Integer id) {
        boolean exists = categoryRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Category");
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) throw RestException.notFound("Category");
    }

    private void checkExistingName(String name) {
        boolean exist = categoryRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Category");
    }


    public Category getByIdOrElseThrow(Integer priceSaleId) {
        return categoryRepository.findById(priceSaleId).orElseThrow(
                () -> RestException.notFound("Category"));
    }

    private ApiResult<CategoryDTO> returnApiResult(Category category, boolean success, String msg) {
        CategoryDTO categoryDTO = mapInfoDTO(category);
        return new ApiResult<>(categoryDTO,success,msg);
    }

    private CategoryDTO entityToInfoDTO(Category category) {
        return new CategoryDTO(
                Math.toIntExact(category.getId()),
                category.getName(),
                category.isActive()


        );
    }
}
