package com.mcn.shoop.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AttributeDTO {
    private Long id;
    private String attribute;
    private AttributeValuesDTO values;
}
