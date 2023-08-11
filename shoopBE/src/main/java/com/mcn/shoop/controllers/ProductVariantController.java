package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.services.AttributeService;
import com.mcn.shoop.services.ProductVariantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/products/variant")
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    @Autowired
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public List<ProductVariant> list(){
        return productVariantService.getProductVariants();
    }

    @GetMapping("/{id}")
    public ProductVariant getProductVariant(@PathVariable Long id){
        return productVariantService.getProductVariant(id);
    }

    @PostMapping
    public ResponseEntity<ProductVariant> createProductVariant(@RequestBody ProductVariant productVariant){
        ProductVariant savedProductVariant = productVariantService.createProductVariant(productVariant);
        return ResponseEntity.ok(savedProductVariant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariant> updateProductVariant(@PathVariable Long id, @RequestBody ProductVariant productVariant){
        ProductVariant updateProductVariant = productVariantService.updateProductVariant(id, productVariant);
        return ResponseEntity.ok(updateProductVariant);
    }

    @DeleteMapping("/{id}")
    public void deleteProductVariant(@PathVariable("id") Long id){
        productVariantService.deleteProductVariant(id);
    }

//    @PostMapping("/{id}/{attributeId}/attributes")
//    public ResponseEntity<Object> addAttributesToProduct(@PathVariable("id") Long id, @PathVariable("attributeId") Long attributeId) {
//        ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
//        if (productVariant == null) {
//            return new ResponseEntity<>("Product variant not found", HttpStatus.NOT_FOUND);
//        }
//        Attribute attributes = attributeRepository.findById(attributeId).orElse(null);
//        if (attributes == null) {
//            return new ResponseEntity<>("Attribute not found", HttpStatus.NOT_FOUND);
//        }
//        if(productVariant.getAttribute().contains(attributes)){
//            return new ResponseEntity<>("Attribute already exists!", HttpStatus.BAD_REQUEST);
//        }
//        productVariant.getAttribute().add(attributes);
//        attributeService.createAttribute(attributes);
//        return new ResponseEntity<>("Attributes added to the product successfully", HttpStatus.OK);
//    }
}

