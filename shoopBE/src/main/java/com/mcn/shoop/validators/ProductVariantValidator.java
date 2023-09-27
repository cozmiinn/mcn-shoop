package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.ProductVariant;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantValidator {
    public static void validatePV(ProductVariantDTO productVariantDTO){
        if(StringUtils.isEmpty(productVariantDTO.getName())){
            throw new IllegalArgumentException("Product variant is required!");
        }

        if(StringUtils.isEmpty(productVariantDTO.getDescription())){
            throw new IllegalArgumentException("The description is required!");
        }

        if(productVariantDTO.getPrice() == null){
            throw new IllegalArgumentException("Price is required!");
        }

        if(productVariantDTO.getAvailableQuantity() == -1){
            throw new IllegalArgumentException("Available quantity is required!");
        }else if(productVariantDTO.getAvailableQuantity() <= 0){
            throw new IllegalArgumentException("Product is not available!");
        }

    }
}
