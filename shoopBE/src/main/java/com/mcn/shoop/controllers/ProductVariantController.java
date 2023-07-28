package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.services.AttributeService;
import com.mcn.shoop.services.ProductVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/products/variant")
public class ProductVariantController {

    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantService productVariantService;
    private final AttributeService attributeService;


    public ProductVariantController( ProductVariantRepository productVariantRepository, ProductVariantService productVariantService, AttributeService attributeService ) {
        this.productVariantRepository = productVariantRepository;
        this.productVariantService = productVariantService;
        this.attributeService = attributeService;
    }

    @GetMapping
    public List<ProductVariant> list(){
        return productVariantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductVariant getProductVariant(@PathVariable Long id){
        return productVariantRepository.findById(id).orElseThrow(RuntimeException::new);
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

    @PostMapping("/{id}/attributes")
    public ResponseEntity<Object> addAttributesToProduct(@PathVariable("id") Long id, @RequestBody Attribute attributes){
        ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
        if(productVariant == null){
            return new ResponseEntity<>("Product variant not found", HttpStatus.NOT_FOUND);
        }
        List<ProductVariant> productVariants = new ArrayList<>();
        productVariants.add(productVariant);
        attributes.setVariants(productVariants);
        attributeService.createAttribute(attributes);
        return new ResponseEntity<>("Attributes added to the product successfully", HttpStatus.OK);
    }

}
