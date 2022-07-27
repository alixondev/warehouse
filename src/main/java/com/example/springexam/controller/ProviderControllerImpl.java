package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ProviderAddDTO;
import com.example.springexam.payload.ProviderInfoDTO;
import com.example.springexam.payload.ProviderUpdateDTO;
import com.example.springexam.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class ProviderControllerImpl implements ProviderController{

    private final ProviderService providerService;

    @Override
    public ApiResult<List<ProviderInfoDTO>> getAll() {
        return providerService.getAll();
    }

    @Override
    public ApiResult<ProviderInfoDTO> add(ProviderAddDTO providerAddDTO) {
        return providerService.add(providerAddDTO);
    }

    @Override
    public ApiResult<ProviderInfoDTO> update(ProviderUpdateDTO providerUpdateDTO, Integer id) {
        return providerService.update(providerUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return providerService.delete(id);
    }
}
