package com.mcn.shoop.validators;

import com.mcn.shoop.entities.BaseProduct;
import io.micrometer.common.util.StringUtils;

public class BaseProductValidator {
    public static void validateBP(BaseProduct baseProduct){
        if(StringUtils.isEmpty(baseProduct.getType())){
            throw new IllegalArgumentException("Base product is required!");
        }
    }
}
