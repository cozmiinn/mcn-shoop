package com.mcn.shoop.services;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.BaseProduct;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.mappers.BaseProductStructMapper;
import com.mcn.shoop.mappers.ProductVariantStructMapper;
import com.mcn.shoop.repositories.BaseProductRepository;
import com.mcn.shoop.validators.BaseProductValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseProductService {

    private final BaseProductRepository baseProductRepository;
    private final ProductVariantService productVariantService;
    private final ProductVariantStructMapper productVariantStructMapper;
    private final BaseProductStructMapper baseProductStructMapper;

    @Autowired
    public BaseProductService(BaseProductRepository baseProductRepository, ProductVariantService productVariantService, ProductVariantStructMapper productVariantStructMapper, BaseProductStructMapper baseProductStructMapper) {
        this.baseProductRepository = baseProductRepository;
        this.productVariantService = productVariantService;
        this.productVariantStructMapper = productVariantStructMapper;
        this.baseProductStructMapper = baseProductStructMapper;
    }

    public List<BaseProductDTO> getBaseProducts(){
        List<BaseProduct> getBaseProduct = baseProductRepository.findAll();
        List<BaseProductDTO> baseProductDTOS = getBaseProduct
                .stream()
                .map(baseProductStructMapper::baseProductToBaseProductDto)
                .collect(Collectors.toList());
        return baseProductDTOS;
    }

    public BaseProductDTO getBaseProduct(Long id){
        BaseProduct getBaseProducct = baseProductRepository.findById(id).orElse(null);
        return baseProductStructMapper.baseProductToBaseProductDto(getBaseProducct);
    }

    public BaseProductDTO createBaseProduct(BaseProductDTO baseProductDTO){
        BaseProduct baseProduct = baseProductStructMapper.baseProductDtoToBaseProduct(baseProductDTO);
        BaseProduct create = baseProductRepository.save(baseProduct);
        return baseProductStructMapper.baseProductToBaseProductDto(create);
    }

    public BaseProductDTO updateBaseProduct(Long id, BaseProductDTO baseProductDTO){
        BaseProductValidator.validateBP(baseProductDTO);

        BaseProduct currentBaseProduct = baseProductRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Base product with id " + id + " not found!"));
        currentBaseProduct.setType(baseProductDTO.getType());

        currentBaseProduct = baseProductRepository.save(currentBaseProduct);

        return baseProductStructMapper.baseProductToBaseProductDto(currentBaseProduct);
    }

    public void deleteBaseProduct(Long id){
        baseProductRepository.deleteById(id);
    }

    public void addVariantToProduct(Long id, ProductVariantDTO productVariantDTO){
        BaseProduct baseProduct = baseProductRepository.findById(id).orElse(null);
        ProductVariant productVariant = productVariantStructMapper.productDtoToProductVariant(productVariantDTO);
        if(baseProduct != null) {
            productVariant.setBaseProduct(baseProduct);
            productVariantService.createProductVariant(productVariantDTO);
        }else{
            new ResponseEntity<>("Base product not found!", HttpStatus.NOT_FOUND);
        }
        productVariantStructMapper.productVariantToProductVariantDto(productVariant);
    }
}
