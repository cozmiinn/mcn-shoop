package com.mcn.shoop.services;

import com.mcn.shoop.entities.BaseProduct;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.BaseProductRepository;
import com.mcn.shoop.validators.BaseProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseProductService {

    private final BaseProductRepository baseProductRepository;

    private final ProductVariantService productVariantService;

    @Autowired
    public BaseProductService(BaseProductRepository baseProductRepository, ProductVariantService productVariantService) {
        this.baseProductRepository = baseProductRepository;
        this.productVariantService = productVariantService;
    }

    public List<BaseProduct> getBaseProducts(){
        return baseProductRepository.findAll();
    }

    public BaseProduct getBaseProduct(Long id){
        return baseProductRepository.findById(id).orElse(null);
    }

    public BaseProduct createBaseProduct(BaseProduct baseProduct){
        return baseProductRepository.save(baseProduct);
    }

    public BaseProduct updateBaseProduct(Long id, BaseProduct baseProduct){
        BaseProductValidator.validateBP(baseProduct);
        BaseProduct currentBaseProduct = baseProductRepository.findById(id).orElseThrow(RuntimeException::new);
        currentBaseProduct.setType(baseProduct.getType());

        return baseProductRepository.save(currentBaseProduct);
    }

    public void deleteBaseProduct(Long id){
        baseProductRepository.deleteById(id);
    }

    public void addVariantToProduct(Long id, ProductVariant productVariant){
        BaseProduct baseProduct = baseProductRepository.findById(id).orElse(null);
        if(baseProduct != null) {
            productVariant.setBaseProduct(baseProduct);
            productVariantService.createProductVariant(productVariant);
        }else{
            throw new IllegalArgumentException("Base product not found!");
        }
    }
}
