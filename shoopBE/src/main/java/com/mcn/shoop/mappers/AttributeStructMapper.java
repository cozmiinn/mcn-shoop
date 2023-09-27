package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.entities.Attribute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeStructMapper {
    AttributeDTO attributeToAttributeDto (Attribute attribute);
    Attribute attributeDtoToAttribute(AttributeDTO attributeDTO);
}
