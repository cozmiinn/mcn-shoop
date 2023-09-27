package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.entities.AttributeValues;
import io.micrometer.common.util.StringUtils;

public class AttributeValuesValidator {
    public static void validateAV(AttributeValuesDTO attributeValuesDTO){
        if(StringUtils.isEmpty(attributeValuesDTO.getValue())){
            throw new IllegalArgumentException("Value is required!");
        }
    }
}
