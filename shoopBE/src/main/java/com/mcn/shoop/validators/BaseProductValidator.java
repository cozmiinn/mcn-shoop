package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.entities.BaseProduct;
import io.micrometer.common.util.StringUtils;

public class BaseProductValidator {
    public static void validateBP(BaseProductDTO baseProductDTO){
        if(StringUtils.isEmpty(baseProductDTO.getType())){
            throw new IllegalArgumentException("Base product is required!");
        }
    }
}
