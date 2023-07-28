package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.repositories.AttributeValuesRepository;
import com.mcn.shoop.services.AttributeValuesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/")
public class AttributeValuesController {
    private final AttributeValuesRepository attributeValuesRepository;
    private final AttributeValuesService attributeValuesService;

    public AttributeValuesController(AttributeValuesRepository attributeValuesRepository, AttributeValuesService attributeValuesService) {
        this.attributeValuesRepository = attributeValuesRepository;
        this.attributeValuesService = attributeValuesService;
    }

    @GetMapping
    public List<AttributeValues> list(){
        return attributeValuesRepository.findAll();
    }

    @GetMapping("/{id}")
    public AttributeValues getValues(@PathVariable Long id){
        return attributeValuesRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<AttributeValues> createValues(@RequestBody AttributeValues attributeValues){
        AttributeValues savedValues = attributeValuesService.createAttributeValues(attributeValues);
        return ResponseEntity.ok(savedValues);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttributeValues> updateValues(@PathVariable Long id, @RequestBody AttributeValues attributeValues) {
        AttributeValues updateValues = attributeValuesService.updateAttributeValues(id, attributeValues);
        return ResponseEntity.ok(updateValues);
    }

    @DeleteMapping("/{id}")
    public void deleteValues(@PathVariable("id") Long id){
        attributeValuesService.deleteAttributeValues(id);
    }
}
