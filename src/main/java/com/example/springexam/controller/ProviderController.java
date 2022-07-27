package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ProviderController.PROVIDER_CONTROLLER_PATH)
public interface ProviderController {

    String PROVIDER_CONTROLLER_PATH = AppConstant.BASE_PATH +"/provider";

    @GetMapping("/get")
    ApiResult<List<ProviderInfoDTO>> getAll();

    @PostMapping("/add")
    ApiResult<ProviderInfoDTO> add(@RequestBody ProviderAddDTO providerAddDTO);

    @PutMapping("/{id}")
    ApiResult<ProviderInfoDTO> update(@RequestBody ProviderUpdateDTO providerUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);
}
