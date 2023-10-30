package com.mcn.shoop.mappers;

import com.mcn.shoop.dtos.CategoryDTO;
import com.mcn.shoop.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryStructMapper {
    CategoryDTO categoryToCategoryDto(Category category);
    Category categoryDtoToCategory(CategoryDTO categoryDTO);
}
