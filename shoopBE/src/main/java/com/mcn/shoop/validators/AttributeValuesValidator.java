package com.mcn.shoop.validators;

import com.mcn.shoop.entities.AttributeValues;
import io.micrometer.common.util.StringUtils;

public class AttributeValuesValidator {
    public static void validateAV(AttributeValues attributeValues){
        if(StringUtils.isEmpty(attributeValues.getValue())){
            throw new IllegalArgumentException("Value is required!");
        }
    }
}
