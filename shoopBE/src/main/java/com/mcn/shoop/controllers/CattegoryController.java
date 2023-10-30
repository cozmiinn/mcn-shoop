package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.CategoryDTO;
import com.mcn.shoop.dtos.SubcategoryDTO;
import com.mcn.shoop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/category")
public class CattegoryController {
    private final CategoryService categoryService;
    @Autowired
    public CattegoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> list(){
        return categoryService.getCategories();
    }

    @GetMapping("/find/{id}")
    public CategoryDTO getCategory(@PathVariable Long id){
        return categoryService.getCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updateCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category is deleted successfully!", HttpStatus.OK);
    }

    @PutMapping("/{id}/subcategory/{subcategoryId}")
    public ResponseEntity<SubcategoryDTO> addSubcategoryToCategory(@PathVariable("id") Long id, @PathVariable("id") Long subcategoryId){
        SubcategoryDTO subcategory = categoryService.addSubcategoryToCategory(id, subcategoryId);
        return new ResponseEntity<>(subcategory, HttpStatus.OK);
    }
}
