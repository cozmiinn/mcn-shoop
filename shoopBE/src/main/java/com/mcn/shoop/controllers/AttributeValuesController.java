package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.services.AttributeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/products/variant/attribute/value")
public class AttributeValuesController {
    private final AttributeValuesService attributeValuesService;

    @Autowired
    public AttributeValuesController(AttributeValuesService attributeValuesService) {
        this.attributeValuesService = attributeValuesService;
    }

    @GetMapping
    public List<AttributeValuesDTO> list(){
        return attributeValuesService.getAttributeValues();
    }

    @GetMapping("/find/{id}")
    public AttributeValuesDTO getValues(@PathVariable Long id){
        return attributeValuesService.getAttributeValues(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AttributeValuesDTO> createValues(@RequestBody AttributeValuesDTO attributeValuesDTO){
        AttributeValuesDTO savedValues = attributeValuesService.createAttributeValues(attributeValuesDTO);
        return ResponseEntity.ok(savedValues);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttributeValuesDTO> updateValues(@PathVariable Long id, @RequestBody AttributeValuesDTO attributeValuesDTO) {
        AttributeValuesDTO updateValues = attributeValuesService.updateAttributeValues(id, attributeValuesDTO);
        return ResponseEntity.ok(updateValues);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteValues(@PathVariable("id") Long id){
        attributeValuesService.deleteAttributeValues(id);
    }
}
