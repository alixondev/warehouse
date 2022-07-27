package com.example.springexam.service;

import com.example.springexam.entity.Product;
import com.example.springexam.exception.RestException;
import com.example.springexam.payload.*;
import com.example.springexam.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public ApiResult<List<ProductInfoDTO>> getAll() {

        List<Product> productList = productRepository.findAll();
        List<ProductInfoDTO> productInfoDTOList = productList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        return ApiResult.successResponse(productInfoDTOList);
    }

    @Override
    public ApiResult<ProductInfoDTO> add(ProductAddDTO productAddDTO) {
        checkExistingName(productAddDTO.getName());
        Product product = new Product(
                productAddDTO.getProductId(),
                productAddDTO.getName(),
                productAddDTO.getMeasurement(),
                productAddDTO.getCategory()

        );
        productRepository.save(product);

        return returnApiResult(product,true,"success");
    }

    @Override
    public ApiResult<ProductInfoDTO> update(ProductUpdateDTO productUpdateDTO, Integer id) {
            checkExistingName(productUpdateDTO.getName(),id);
            Product product = getByIdOrElseThrow(id);
            product.setId(product.getId());
            product.setName(product.getName());
            product.setMeasurement(productUpdateDTO.getMeasurement());
            product.setCategory(productUpdateDTO.getCategory());

            productRepository.save(product);



        return returnApiResult(product,true,"success");
    }

    @Override
    public String delete(Integer id) {

        Product product = getByIdOrElseThrow(id);
        productRepository.delete(product);

        return "success";
    }

    private ProductInfoDTO entityToInfoDTO(Product product) {


        return new ProductInfoDTO(
            product.getName(),
                product.getCategory(),
                product.getCategory(),
                product.getAttachment(),
                product.getMeasurement()
        );

            }

    private static String generateCode(String name){
        double random=Math.random()*1000;
        return (random-random%1)+"#"+name;
    }

    public Product getByIdOrElseThrow(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () -> RestException.notFound("Product"));
    }

    private void checkExistingName(String name, Integer id) {
        boolean exists = productRepository.existsByNameAndIdNot(name,id);
        if (exists) throw RestException.alreadyExist("Product");
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw RestException.notFound("Product");
    }

    private void checkExistingName(String name) {
        boolean exist = productRepository.existsByName(name);
        if (exist) throw RestException.alreadyExist("Product");
    }

    private ApiResult<ProductInfoDTO> returnApiResult(Product product, boolean success, String msg) {
        ProductInfoDTO productInfoDTO = entityToInfoDTO(product);
        return new ApiResult<>(productInfoDTO,true,msg);
    }
}
