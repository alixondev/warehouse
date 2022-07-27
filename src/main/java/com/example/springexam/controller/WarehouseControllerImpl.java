package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
public class WarehouseControllerImpl implements WarehouseController{

    private final WarehouseService warehouseService;

    @Override
    public ApiResult<List<WarehouseDTO>> getAll() {
        return warehouseService.getAll();
    }

    @Override
    public ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseAddDTO) {
        return warehouseService.add(wareHouseAddDTO);
    }

    @Override
    public ApiResult<WareHouseInfoDTO> update(WareHouseUpdateDTO wareHouseUpdateDTO, Integer id) {
        return warehouseService.update(wareHouseUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return warehouseService.delete(id);
    }
}
