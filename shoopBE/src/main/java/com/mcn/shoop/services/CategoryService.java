package com.mcn.shoop.services;

import com.mcn.shoop.dtos.CategoryDTO;
import com.mcn.shoop.dtos.SubcategoryDTO;
import com.mcn.shoop.entities.Category;
import com.mcn.shoop.entities.SubCategory;
import com.mcn.shoop.mappers.CategoryStructMapper;
import com.mcn.shoop.mappers.SubcategoryStructMapper;
import com.mcn.shoop.repositories.CategoryRepository;
import com.mcn.shoop.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryStructMapper categoryStructMapper;
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryStructMapper subcategoryStructMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryStructMapper categoryStructMapper, SubcategoryRepository subcategoryRepository, SubcategoryStructMapper subcategoryStructMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryStructMapper = categoryStructMapper;
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryStructMapper = subcategoryStructMapper;
    }

    public List<CategoryDTO> getCategories(){
        List<Category> getCategories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS;
        categoryDTOS = getCategories
                .stream()
                .map(categoryStructMapper::categoryToCategoryDto)
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    public CategoryDTO getCategory(Long id){
        Category getCategory = categoryRepository.findById(id).orElse(null);
        return categoryStructMapper.categoryToCategoryDto(getCategory);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO){
        Category category = categoryStructMapper.categoryDtoToCategory(categoryDTO);
        Category create = categoryRepository.save(category);
        return categoryStructMapper.categoryToCategoryDto(create);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO){
        Category currentCategory = categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with id " + id + " not found!"));

        currentCategory.setCategoryName(categoryDTO.getCategoryName());
        currentCategory = categoryRepository.save(currentCategory);

        return categoryStructMapper.categoryToCategoryDto(currentCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

    public SubcategoryDTO addSubcategoryToCategory(@PathVariable("id") Long categoryId, @PathVariable("id") Long subcategoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null){
            new ResponseEntity<>("Category not found!", HttpStatus.NOT_FOUND);
        }

        SubCategory Subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);
        if(Subcategory == null){
            new ResponseEntity<>("SubCategory not found!", HttpStatus.NOT_FOUND);
        }

        if(category != null && category.getSubcategories().contains(Subcategory)){
            new ResponseEntity<>("Category already exists!", HttpStatus.BAD_REQUEST);
        }

        Subcategory.setCategory(category);
        Subcategory = subcategoryRepository.save(Subcategory);

        return subcategoryStructMapper.subcategoryToSubcategoryDTO(Subcategory);
    }
}
