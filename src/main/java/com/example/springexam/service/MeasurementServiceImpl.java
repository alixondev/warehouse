package com.example.springexam.service;

import com.example.springexam.entity.Measurement;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.MeasurementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService{

    private final MeasurementRepository measurementRepository;


    @Override
    public ApiResult<List<MeasurementInfoDTO>> getAll() {
        List<Measurement> measurementList = measurementRepository.findAll();
        List<MeasurementInfoDTO> measurementInfoDTOList = measurementList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(measurementInfoDTOList);
    }



    @Override
    public ApiResult<MeasurementInfoDTO> add(MeasurementAddDTO measurementAddDTO) {

            checkExistingName(measurementAddDTO.getName());
            Measurement measurement = new Measurement(
                measurementAddDTO.getId(),
                    measurementAddDTO.getName(),
                    measurementAddDTO.isActive()
            );
            measurementRepository.save(measurement);
        return returnApiResult(measurement,true,"success");
    }

    @Override
    public ApiResult<MeasurementInfoDTO> update(MeasurementUpdateDTO measurementUpdateDTO, Integer id) {

        checkExistingName(measurementUpdateDTO.getName(),id);
        Measurement measurement = getByIdOrElseThrow(id);
        measurement.setId(measurementUpdateDTO.getId());
        measurement.setName(measurementUpdateDTO.getName());
        measurement.setActive(measurementUpdateDTO.isActive());

        measurementRepository.save(measurement);
        return returnApiResult(measurement,true,"success");
    }

    @Override
    public String delete(Integer id) {
        Measurement measurement = getByIdOrElseThrow(id);
        measurementRepository.delete(measurement);
        return "success";
    }

    private ApiResult<MeasurementInfoDTO> returnApiResult(Measurement measurement,boolean success, String msg) {
        MeasurementInfoDTO measurementInfoDTO = entityToInfoDTO(measurement);
        return new ApiResult<>(measurementInfoDTO,true,msg);
    }

    public Measurement getByIdOrElseThrow(Integer id) {
        return measurementRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Measurement"));
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = measurementRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Measurement");
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty()) throw RestException.notFound("Measurement");
    }

    private void checkExistingName(String name) {
        boolean exist = measurementRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Measurement");
    }

    private MeasurementInfoDTO entityToInfoDTO(Measurement measurement) {
        return new MeasurementInfoDTO(
                Math.toIntExact(measurement.getId().longValue()),
                measurement.getName(),
                measurement.isActive()
        );
    }
}
