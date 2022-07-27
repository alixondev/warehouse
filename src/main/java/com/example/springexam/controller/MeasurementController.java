package com.example.springexam.controller;

import com.example.springexam.payload.*;
import com.example.springexam.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(MeasurementController.MEASUREMENT_CONTROLLER_PATH)
public interface MeasurementController {

    String MEASUREMENT_CONTROLLER_PATH = AppConstant.BASE_PATH +"/measurement";

    @GetMapping("/get")
    ApiResult<List<MeasurementInfoDTO>> getAll();

    @PostMapping("/add")
    ApiResult<MeasurementInfoDTO> add(@RequestBody MeasurementAddDTO measurementAddDTO);

    @PutMapping("/{id}")
    ApiResult<MeasurementInfoDTO> update(@RequestBody MeasurementUpdateDTO measurementUpdateDTO, @PathVariable Integer id);

    @DeleteMapping("/{id}")
    String delete(@PathVariable Integer id);
}
