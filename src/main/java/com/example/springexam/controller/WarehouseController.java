package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(WarehouseController.WAREHOUSE_CONTROLLER_PATH)
public interface WarehouseController {

    String WAREHOUSE_CONTROLLER_PATH = AppConstant.BASE_PATH +"/user";

    @GetMapping("/get")
    ApiResult<List<WarehouseDTO>> getAll();

    @PostMapping("/add")
    ApiResult<WareHouseInfoDTO> add(@RequestBody WareHouseAddDTO wareHouseAddDTO);

    @PutMapping("/{id}")
    ApiResult<WareHouseInfoDTO> update(@RequestBody WareHouseUpdateDTO wareHouseUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);
}


