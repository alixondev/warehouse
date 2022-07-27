package com.example.springexam.service;

import com.example.springexam.payload.*;

import java.util.List;

public interface CurrencyService {

    ApiResult<List<CurrencyInfoDTO>> getAll();

    ApiResult<CurrencyInfoDTO> add(CurrencyAddDTO currencyAddDTO);

    ApiResult<CurrencyInfoDTO> update(CurrencyUpdateDTO currencyUpdateDTO, Integer id);

    String delete(Integer id);
}
