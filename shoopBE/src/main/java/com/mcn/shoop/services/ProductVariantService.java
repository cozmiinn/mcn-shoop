package com.mcn.shoop.services;

import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.validators.ProductVariantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    public List<ProductVariant> getProductVariant(){
        return productVariantRepository.findAll();
    }

    public ProductVariant createProductVariant(ProductVariant productVariant){
        return productVariantRepository.save(productVariant);
    }

    public ProductVariant updateProductVariant(Long id, ProductVariant productVariant){
        ProductVariantValidator.validatePV(productVariant);
        ProductVariant currentProductVariant = productVariantRepository.findById(id).orElseThrow(RuntimeException::new);
        currentProductVariant.setName(productVariant.getName());
        currentProductVariant.setDescription(productVariant.getDescription());
        currentProductVariant.setPrice(productVariant.getPrice());
        currentProductVariant.setAvailableQuantity(productVariant.getAvailableQuantity());
        currentProductVariant.setAddedDate(productVariant.getAddedDate());

        return productVariantRepository.save(currentProductVariant);
    }

    public void deleteProductVariant(Long id){
        productVariantRepository.deleteById(id);
    }
}
