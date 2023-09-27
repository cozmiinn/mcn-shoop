package com.mcn.shoop.validators;

import com.mcn.shoop.dtos.AttributeDTO;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AttributeValidator {
    public static void validateAttribute(AttributeDTO attributeDTO) {
        if (StringUtils.isEmpty(attributeDTO.getAttribute())) {
            throw new IllegalArgumentException("Attribute is required!");
        }
    }

}
