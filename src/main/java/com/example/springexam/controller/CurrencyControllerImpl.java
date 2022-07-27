package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.CurrencyAddDTO;
import com.example.springexam.payload.CurrencyInfoDTO;
import com.example.springexam.payload.CurrencyUpdateDTO;
import com.example.springexam.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CurrencyControllerImpl implements CurrencyController{

    private final CurrencyService currencyService;

    @Override
    public ApiResult<List<CurrencyInfoDTO>> getAll() {
        return currencyService.getAll();
    }

    @Override
    public ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO) {
        return currencyService.add(currencyAddDTO);
    }

    @Override
    public ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id) {
        return currencyService.update(currencyUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return currencyService.delete(id);
    }
}
