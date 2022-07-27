package com.example.springexam.service;

import com.example.springexam.entity.InputProduct;
import com.example.springexam.entity.Measurement;
import com.example.springexam.entity.User;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.InputProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputProductServiceImpl implements InputProductService{
   private final InputProductsRepository productsRepository;
    @Override
    public ApiResult<List<InputProductInfoDTO>> getAll() {

        List<InputProduct> inputProductList = productsRepository.findAll();
        List<InputProductInfoDTO> inputProductInfoDTOList = inputProductList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(inputProductInfoDTOList);
    }



    @Override
    public ApiResult<InputProductInfoDTO> add(InputProductAddDTO inputProductAddDTO) {
        checkExistingName(inputProductAddDTO.getName());
        InputProduct inputProduct = new InputProduct(
                inputProductAddDTO.getName(),
                inputProductAddDTO.getMeasurement(),
                inputProductAddDTO.getWarehouse(),
                inputProductAddDTO.getPrice()
        );
        productsRepository.save(inputProduct);
        return returnApiResult(inputProduct,true,"success");
    }

    @Override
    public ApiResult<InputProductInfoDTO> update(InputProductUpdateDTO inputProductUpdateDTO, Integer id) {

        checkExistingName(inputProductUpdateDTO.getName(),id);
        InputProduct inputProduct = getByIdOrElseThrow(id);

        inputProduct.setProduct(inputProductUpdateDTO.getName());
        inputProduct.setMeasurement(inputProductUpdateDTO.getMeasurement());
        inputProduct.setPrice(inputProductUpdateDTO.getPrice());
        inputProduct.setWarehouse(inputProductUpdateDTO.getWarehouse());

        productsRepository.save(inputProduct);

        return returnApiResult(inputProduct,true,"success");
    }

    @Override
    public String delete(Integer id) {
        InputProduct inputProduct = getByIdOrElseThrow(id);
        productsRepository.delete(inputProduct);
        return "success";
    }


    private InputProductInfoDTO entityToInfoDTO(InputProduct inputProduct) {
        return new InputProductInfoDTO(
                inputProduct.getId(),
                inputProduct.getProduct().getName(),
                inputProduct.getPrice(),
                inputProduct.getMeasurement().getName(),
                inputProduct.getWarehouse()
        );
    }


    public InputProduct getByIdOrElseThrow(Integer id) {
        return productsRepository.findById(id).orElseThrow(
                () -> RestException.notFound("User"));
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = productsRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("InputProduct");
        Optional<InputProduct> optionalInputProduct = productsRepository.findById(id);
        if (optionalInputProduct.isEmpty()) throw RestException.notFound("InputProduct");
    }

    private void checkExistingName(String name) {
        boolean exist = productsRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("InputProduct");
    }
    private ApiResult<InputProductInfoDTO> returnApiResult(InputProduct inputProduct, boolean success, String msg) {
        InputProductInfoDTO inputProductInfoDTO = entityToInfoDTO(inputProduct);
        return new ApiResult<>(inputProductInfoDTO,true,msg);
    }
}
