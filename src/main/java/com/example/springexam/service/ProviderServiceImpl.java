package com.example.springexam.service;


import com.example.springexam.entity.Provider;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;

import com.example.springexam.repository.ProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService{

    private final ProviderRepository providerRepository;

    @Override
    public ApiResult<List<ProviderInfoDTO>> getAll() {

        List<Provider> providerList = providerRepository.findAll();
        List<ProviderInfoDTO> providerInfoDTOList = providerList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());
        return ApiResult.successResponse(providerInfoDTOList);
    }



    @Override
    public ApiResult<ProviderInfoDTO> add(ProviderAddDTO providerAddDTO) {
        checkExistingName(providerAddDTO.getName());
        Provider provider = new Provider(
                providerAddDTO.getName(),
                providerAddDTO.getPhoneNumber()
        );
        providerRepository.save(provider);
        return returnApiResult(provider,true,"success");
    }

    @Override
    public ApiResult<ProviderInfoDTO> update(ProviderUpdateDTO providerUpdateDTO,Integer id) {
        checkExistingName(providerUpdateDTO.getName(),id);
        Provider provider = getByIdOrElseThrow(id);

        provider.setName(providerUpdateDTO.getName());
        provider.setPhoneNumber(providerUpdateDTO.getPhoneNumber());

        providerRepository.save(provider);
        return returnApiResult(provider,true,"success");
    }



    @Override
    public String delete(Integer id) {
        return null;
    }

    private ProviderInfoDTO entityToInfoDTO(Provider provider) {
        return new ProviderInfoDTO(
                provider.getId(),
                provider.getName(),
                provider.getPhoneNumber()

        );
    }

    public Provider getByIdOrElseThrow(Integer id) {
        return providerRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Provider"));
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = providerRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Provider");
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isEmpty()) throw RestException.notFound("Provider");
    }

    private void checkExistingName(String name) {
        boolean exist = providerRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Product");
    }

    private ApiResult<ProviderInfoDTO> returnApiResult(Provider provider, boolean success, String msg) {
        ProviderInfoDTO providerInfoDTO = entityToInfoDTO(provider);
        return new ApiResult<>(providerInfoDTO,true,msg);
    }
}
