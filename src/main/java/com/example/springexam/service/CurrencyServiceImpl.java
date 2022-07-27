package com.example.springexam.service;

import com.example.springexam.entity.CurrencyType;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Currency;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository currencyRepository;
    @Override
    public ApiResult<List<CurrencyInfoDTO>> getAll() {
//        List<Currency> currencyTypeList = currencyRepository.findAll();
//        List<CurrencyInfoDTO> currencyInfoDTO = currencyTypeList
//                .stream()
//                .map(this::entityToInfoDTO)
//                .collect(Collectors.toList());
//        return ApiResult.successResponse(currencyInfoDTO);
        return null;
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {

            checkExistingName(currencyAddDTO.getName());
            CurrencyType currencyType = new CurrencyType(
                    currencyAddDTO.getId(),
                    currencyAddDTO.getName(),
                    currencyAddDTO.isActive()
            );
        return returnApiResult(currencyType,true,"success");
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        checkExistingName(currencyUpdateDTO.getName(),id);
        CurrencyType currencyType = getByIdOrElseThrow(id);
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    private ApiResult<CurrencyInfoDTO> returnApiResult(CurrencyType currencyType, boolean success, String msg) {
        CurrencyInfoDTO currencyInfoDTO =  entityToInfoDTO(currencyType);
        return new ApiResult<>(currencyInfoDTO,true,msg);
    }

    public CurrencyType
    getByIdOrElseThrow(Integer id) {
        return currencyRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Currency"));
    }


    private void checkExistingName(String name, Integer id) {
        boolean exists = currencyRepository.existsByName(name);
        if (exists) throw RestException.alreadyExist("Currency");
        Optional<CurrencyType> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isEmpty()) throw RestException.notFound("Currency");
    }

    private void checkExistingName(String name) {
        boolean exist = currencyRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Measurement");
    }

    private CurrencyInfoDTO entityToInfoDTO(CurrencyType currencyType) {
        return new CurrencyInfoDTO(
                Math.toIntExact(currencyType.getId()),
                currencyType.getName(),
                currencyType.isActive()
        );
    }
}
