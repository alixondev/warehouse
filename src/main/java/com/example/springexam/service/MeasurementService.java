package com.example.springexam.service;

import com.example.springexam.payload.*;

import java.util.List;

public interface MeasurementService {
    ApiResult<List<MeasurementInfoDTO>> getAll();

    ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO);

    ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id);

    String delete(Integer id);
}
