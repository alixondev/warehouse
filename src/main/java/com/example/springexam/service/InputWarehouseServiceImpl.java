package com.example.springexam.service;

import com.example.springexam.entity.InputWarehouse;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.InputWarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InputWarehouseServiceImpl implements InputWarehouseService{

    private final InputWarehouseRepository inputWarehouseRepository;
//    @Override
//    public ApiResult<List<InputWarehouseDTO>> getAll() {
//        return null;
//    }

    @Override
    public ApiResult<InputWarehouseDTO> add(InputWareHouseAddDTO wareHouseAddDTODTO) {

//            checkExistingName(wareHouseAddDTODTO.getName());
//        InputWarehouse inputWarehouse = new InputWarehouse(
//                wareHouseAddDTODTO.getTimestamp(),
//                wareHouseAddDTODTO.getWarehouse(),
//                wareHouseAddDTODTO.getProvider(),
//                wareHouseAddDTODTO.getProduct(),
//                wareHouseAddDTODTO.getCurrency(),
//                wareHouseAddDTODTO.getFactureNum(),
//                wareHouseAddDTODTO.getUniqueId()
//
//        );

        return null;
    }

//    @Override
//    public ApiResult<InputWarehouseDTO> update(InputWareHouseUpdateDTO wareHouseUpdateDTO, Integer id) {
//        return null;
//    }
//
//    @Override
//    public String delete(Integer id) {
//        return null;
//    }

    private void checkExistingName(String name) {
        boolean exist = inputWarehouseRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("InputWarehouse");
    }




//    private ApiResult<InputWarehouseDTO> returnApiResult(InputWarehouse inputWarehouse, boolean success, String msg) {
//        InputWarehouseDTO inputWarehouseDTO = mapInfoDTO(warehouse);
//        return new ApiResult<>(wareHouseInfoDTO,success,msg);
//    }
//
//    private InputWarehouseDTO mapInfoDTO(InputWarehouse inputWarehouse) {
//
//        return new WareHouseInfoDTO(
//                warehouse.getName(),
//                warehouse.isActive()
//        );
//
//    }
}
