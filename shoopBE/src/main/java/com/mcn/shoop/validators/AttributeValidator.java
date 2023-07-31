package com.mcn.shoop.validators;

import com.mcn.shoop.entities.Attribute;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AttributeValidator {
    public static void validateAttribute(Attribute attribute) {
        if (StringUtils.isEmpty(attribute.getAttribute())) {
            throw new IllegalArgumentException("Attribute is required!");
        }
    }

}
