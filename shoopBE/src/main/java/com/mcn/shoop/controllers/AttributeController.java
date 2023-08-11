package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.AttributeValues;
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
    public List<Attribute> list(){
        return attributeService.getAttributes();
    }

    @GetMapping("/{id}")
    public Attribute getAttribute(@PathVariable Long id){
        return attributeService.getAttribute(id);
    }

    @PostMapping
    public ResponseEntity<Attribute> createAttribute(@RequestBody Attribute attribute){
        Attribute savedAttribute = attributeService.createAttribute(attribute);
        return ResponseEntity.ok(savedAttribute);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attribute> updateAttribute(@PathVariable Long id, @RequestBody Attribute attribute){
        Attribute updateAttribute = attributeService.updateAttribute(id, attribute);
        return ResponseEntity.ok(updateAttribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttribute(@PathVariable("id") Long id){
        attributeService.deleteAttribute(id);
        return new ResponseEntity<>("Attribute is deleted succesfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/values")
    public ResponseEntity<Object> addValuesToAttribute(@PathVariable("id") Long id, @RequestBody AttributeValues attributeValues){
        attributeService.addValuesToAttribute(id, attributeValues);
        return new ResponseEntity<>("Values added to the attribute successfully", HttpStatus.OK);
    }
}