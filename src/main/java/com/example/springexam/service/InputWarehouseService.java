package com.example.springexam.service;

import com.example.springexam.payload.*;

import java.util.List;

public interface InputWarehouseService {


//    ApiResult<List<InputWarehouseDTO>> getAll();

    ApiResult<InputWarehouseDTO> add(InputWareHouseAddDTO wareHouseAddDTODTO);

//    ApiResult<InputWarehouseDTO> update(InputWareHouseUpdateDTO wareHouseUpdateDTO, Integer id);
//
//    String delete(Integer id);
}
