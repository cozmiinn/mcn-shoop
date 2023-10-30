package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.dtos.SubcategoryDTO;
import com.mcn.shoop.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://localhost:3000")
@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;
    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService){
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    public List<SubcategoryDTO> list(){
        return subcategoryService.getSubcategories();
    }

    @GetMapping("/find/{id}")
    public SubcategoryDTO getSubcategory(@PathVariable Long id){
        return subcategoryService.getSubcategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<SubcategoryDTO> createSubcategory(@RequestBody SubcategoryDTO subcategoryDTO){
        SubcategoryDTO savedSubcategory = subcategoryService.createSubcategory(subcategoryDTO);
        return ResponseEntity.ok(savedSubcategory);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubcategoryDTO> updateSubcategory(@PathVariable("id") Long id, @RequestBody SubcategoryDTO subcategoryDTO){
        SubcategoryDTO updateSubcategory = subcategoryService.updateSubcategory(id, subcategoryDTO);
        return ResponseEntity.ok(updateSubcategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSubcategory(@PathVariable("id") Long id){
        subcategoryService.deleteSubcategory(id);
        return new ResponseEntity<>("Subcategory is deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/{id}/base/{baseProductId}")
    public ResponseEntity<BaseProductDTO> addBaseToSubcategory(@PathVariable("id") Long id, @PathVariable("baseProductId") Long baseProductId){
        BaseProductDTO baseProducts = subcategoryService.addBaseToSubcategory(id, baseProductId);
        return new ResponseEntity<>(baseProducts, HttpStatus.OK);
    }
}
