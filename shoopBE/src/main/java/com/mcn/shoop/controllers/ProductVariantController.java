package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.ProductVariant;
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

    @PostMapping("/{id}/{attributeId}/attributes")
    public ResponseEntity<Object> addAttributesToProduct(@PathVariable("id") Long id, @PathVariable("attributeId") Long attributeId) {
        return productVariantService.addAttributesToProduct(id, attributeId);
    }


    @PostMapping("/{id}/entry")
    public ResponseEntity<Object> addEntryToCart (@PathVariable("id") Long id, @RequestBody CartEntry cartEntry){
        productVariantService.addProductToEntry(id, cartEntry);
        return new ResponseEntity<>("Entries added to cart succesfully!", HttpStatus.OK);
    }

}

