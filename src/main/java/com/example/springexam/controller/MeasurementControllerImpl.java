package com.example.springexam.controller;

import com.example.springexam.payload.ApiResult;
import com.example.springexam.payload.MeasurementAddDTO;
import com.example.springexam.payload.MeasurementInfoDTO;
import com.example.springexam.payload.MeasurementUpdateDTO;
import com.example.springexam.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class MeasurementControllerImpl implements MeasurementController {

    private final MeasurementService measurementService;

    @Override
    public ApiResult<List<MeasurementInfoDTO>> getAll() {
        return measurementService.getAll();
    }

    @Override
    public ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO) {
        return measurementService.add(measurementAddDTO);
    }

    @Override
    public ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id) {
        return measurementService.update(measurementUpdateDTO, id);
    }

    @Override
    public String delete(Integer id) {
        return measurementService.delete(id);
    }
}
