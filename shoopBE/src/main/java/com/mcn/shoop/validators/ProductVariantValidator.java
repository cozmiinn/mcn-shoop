package com.mcn.shoop.validators;

import com.mcn.shoop.entities.ProductVariant;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantValidator {
    public static void validatePV(ProductVariant productVariant){
        if(StringUtils.isEmpty(productVariant.getName())){
            throw new IllegalArgumentException("Product variant is required!");
        }

        if(StringUtils.isEmpty(productVariant.getDescription())){
            throw new IllegalArgumentException("The description is required!");
        }

        if(productVariant.getPrice() == null){
            throw new IllegalArgumentException("Price is required!");
        }

        if(productVariant.getAvailableQuantity() == -1){
            throw new IllegalArgumentException("Available quantity is required!");
        }else if(productVariant.getAvailableQuantity() <= 0){
            throw new IllegalArgumentException("Product is not available!");
        }

    }
}
