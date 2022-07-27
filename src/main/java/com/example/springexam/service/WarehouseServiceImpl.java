package com.example.springexam.service;

import com.example.springexam.entity.Warehouse;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;


    @Override
    public ApiResult<List<WarehouseDTO>> getAll() {
//        List<Warehouse> warehouseList = warehouseRepository.findAll();
//        List<WarehouseDTO> warehouseDTOList = warehouseList
//                        .stream()
//                        .map(this::entityToInfoDTO)
//                        .collect(Collectors.toList());
//        return ApiResult.successResponse(warehouseDTOList);
        return null;
    }

    @Override
    public ApiResult<WareHouseInfoDTO> add(WareHouseAddDTO wareHouseAddDTO) {
        checkExistingName(wareHouseAddDTO.getName());
        Warehouse warehouse = new Warehouse(

                wareHouseAddDTO.getName(),
                wareHouseAddDTO.isActive()
        );
        warehouseRepository.save(warehouse);

        return returnApiResult(warehouse,true,"success");
    }


    @Override
    public ApiResult<WareHouseInfoDTO> update(WareHouseUpdateDTO wareHouseUpdateDTO, Integer id) {
        checkExistingName(wareHouseUpdateDTO.getName(),id);
       Warehouse warehouse = getByIdOrElseThrow(id);
        warehouse.setId(wareHouseUpdateDTO.getId());
        warehouse.setName(wareHouseUpdateDTO.getName());
        warehouse.setActive(warehouse.isActive());

        warehouseRepository.save(warehouse);
            return returnApiResult(warehouse,true,"success");

    }

    @Override
    public String delete(Integer id) {

        Warehouse warehouse = getByIdOrElseThrow(id);
            warehouseRepository.delete(warehouse);

        return "Successfully deleted";
    }




    private WareHouseInfoDTO mapInfoDTO(Warehouse warehouse) {

        return new WareHouseInfoDTO(
                warehouse.getName(),
                warehouse.isActive()
        );

    }
    private void checkExistingName(String name, Integer id) {
        boolean exists = warehouseRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Warehouse");
        var optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) throw RestException.notFound("Warehouse");
    }

    private void checkExistingName(String name) {
        boolean exist = warehouseRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Warehouse");
    }


    public Warehouse getByIdOrElseThrow(Integer id) {
        return warehouseRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Warehouse"));
    }

    private ApiResult<WareHouseInfoDTO> returnApiResult(Warehouse warehouse, boolean success, String msg) {
        WareHouseInfoDTO wareHouseInfoDTO = mapInfoDTO(warehouse);
        return new ApiResult<>(wareHouseInfoDTO,success,msg);
    }


}
