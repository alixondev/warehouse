package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CurrencyController.CURRENCY_CONTROLLER_PATH)
public interface CurrencyController {

    String CURRENCY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/currency/";

    @GetMapping("/get")
    ApiResult<List<CurrencyInfoDTO>> getAll();

    @PostMapping("/add")
    ApiResult<CurrencyInfoDTO> add(@RequestBody CurrencyAddDTO currencyAddDTO);

    @PutMapping("/{id}")
    ApiResult<CurrencyInfoDTO> update(@RequestBody CurrencyUpdateDTO currencyUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);

}
