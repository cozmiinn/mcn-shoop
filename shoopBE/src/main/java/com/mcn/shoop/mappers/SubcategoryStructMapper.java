package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.SubcategoryDTO;
import com.mcn.shoop.entities.SubCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubcategoryStructMapper {
    SubcategoryDTO subcategoryToSubcategoryDTO(SubCategory Subcategory);
    SubCategory subcategoryDTOToSubcategory(SubcategoryDTO subcategoryDTO);
}
