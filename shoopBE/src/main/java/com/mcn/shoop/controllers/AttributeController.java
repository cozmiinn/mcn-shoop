package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products/variant/attribute")
public class AttributeController {
    private final AttributeService attributeService;

    @Autowired

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    public List<AttributeDTO> list(){
        return attributeService.getAttributes();
    }

    @GetMapping("/find/{id}")
    public AttributeDTO getAttribute(@PathVariable Long id){
        return attributeService.getAttribute(id);
    }

    @PostMapping("/add")
    public ResponseEntity<AttributeDTO> createAttribute(@RequestBody AttributeDTO attributeDTO){
        AttributeDTO savedAttribute = attributeService.createAttribute(attributeDTO);
        return ResponseEntity.ok(savedAttribute);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AttributeDTO> updateAttribute(@PathVariable Long id, @RequestBody AttributeDTO attributeDTO){
        AttributeDTO updateAttribute = attributeService.updateAttribute(id, attributeDTO);
        return ResponseEntity.ok(updateAttribute);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable("id") Long id){
        attributeService.deleteAttribute(id);
        return new ResponseEntity<>("Attribute is deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/values")
    public ResponseEntity<AttributeValuesDTO> addValuesToAttribute(@PathVariable("id") Long id, @RequestBody AttributeValuesDTO attributeValuesDTO){
        AttributeValuesDTO attributeValues = attributeService.addValuesToAttribute(id, attributeValuesDTO);
        return new ResponseEntity<>(attributeValues, HttpStatus.OK);
    }
}