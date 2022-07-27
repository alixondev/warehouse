package com.example.springexam.service;

import com.example.springexam.entity.InputProduct;
import com.example.springexam.payload.*;

import java.util.List;

public interface InputProductService {

    ApiResult<List<InputProductInfoDTO>> getAll();

    ApiResult<InputProductInfoDTO> add(InputProductAddDTO inputProductAddDTO);

    ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, Integer id);



    String delete(Integer id);

}
