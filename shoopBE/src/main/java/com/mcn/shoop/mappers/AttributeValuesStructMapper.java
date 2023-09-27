package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.entities.AttributeValues;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeValuesStructMapper {
    AttributeValuesDTO attributeValuesToAttributeValuesDto(AttributeValues attributeValues);
    AttributeValues attributeValuesDtoToAttributeValues(AttributeValuesDTO attributeValuesDTO);
}
