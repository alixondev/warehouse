package com.example.springexam.service;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.ProviderAddDTO;
import com.example.springexam.payload.ProviderInfoDTO;
import com.example.springexam.payload.ProviderUpdateDTO;

import java.util.List;

public interface ProviderService {



    ApiResult<List<ProviderInfoDTO>> getAll();

    ApiResult<ProviderInfoDTO> add(ProviderAddDTO providerAddDTO);

    ApiResult<ProviderInfoDTO> update(ProviderUpdateDTO providerUpdateDTO,Integer id);

    String delete (Integer id);


}
