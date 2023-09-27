package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.ProductVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVariantStructMapper {
    ProductVariantDTO productVariantToProductVariantDto(ProductVariant productVariant);
    ProductVariant productDtoToProductVariant(ProductVariantDTO productVariantDTO);
}
