package com.mcn.shoop.validators;

import com.mcn.shoop.entities.Attribute;

public class AttributeValidator {
    public static void validateAttribute(Attribute attribute){
        throw new IllegalArgumentException("Attribute is required!");
    }
}
