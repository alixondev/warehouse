package com.example.springexam.service;


import com.example.springexam.payload.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WarehouseService {

    ApiResult<List<WarehouseDTO>> getAll();

    ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseAddDTODTO);

    ApiResult<WareHouseInfoDTO> update(WareHouseUpdateDTO wareHouseUpdateDTO, Integer id);



    String delete(Integer id);




}
