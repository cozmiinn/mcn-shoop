package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.entities.BaseProduct;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseProductStructMapper {
    BaseProductDTO baseProductToBaseProductDto(BaseProduct baseProduct);
    BaseProduct baseProductDtoToBaseProduct(BaseProductDTO baseProductDTO);
}
