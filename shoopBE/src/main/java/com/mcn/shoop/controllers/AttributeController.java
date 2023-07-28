package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.services.AttributeService;
import com.mcn.shoop.services.AttributeValuesService;
import com.mcn.shoop.services.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products/variant/attribute")
public class AttributeController {
    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;

    private final ProductVariantService productVariantService;

    private final AttributeValuesService attributeValuesService;

    @Autowired
    public AttributeController(AttributeRepository attributeRepository, AttributeService attributeService, ProductVariantService productVariantService, AttributeValuesService attributeValuesService) {
        this.attributeRepository = attributeRepository;
        this.attributeService = attributeService;
        this.productVariantService = productVariantService;
        this.attributeValuesService = attributeValuesService;
    }

    @GetMapping
    public List<Attribute> list(){
        return attributeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Attribute getAttribute(@PathVariable Long id){
        return attributeRepository.findById(id).orElseThrow(RuntimeException::new);
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

    @PostMapping("/{id}/attributes")
    public ResponseEntity<Object> addAttributesToProduct(@PathVariable("id") Long id, @RequestBody ProductVariant productVariant){
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        if(attribute == null){
            return new ResponseEntity<>("Attribute variant not found", HttpStatus.NOT_FOUND);
        }
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(attribute);
        productVariant.setAttributes(attributes);
        productVariantService.createProductVariant(productVariant);
        return new ResponseEntity<>("Attributes added to the product successfully",HttpStatus.OK);
    }

    @PostMapping("/{id}/values")
    public ResponseEntity<Object> addValuesToAttribute(@PathVariable("id") Long id, @RequestBody AttributeValues attributeValues){
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        if(attribute == null){
            return new ResponseEntity<>("Attribute not found", HttpStatus.NOT_FOUND);
        }
        attributeValues.setAttribute(attribute);
        attributeValuesService.createAttributeValues(attributeValues);
        return new ResponseEntity<>("Values added to the attribute successfully", HttpStatus.OK);
    }

}
