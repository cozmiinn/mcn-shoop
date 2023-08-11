package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.BaseProduct;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.services.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products")
public class BaseProductController {


    private final BaseProductService baseProductService;

    @Autowired
    public BaseProductController(BaseProductService baseProductService) {
        this.baseProductService = baseProductService;
    }

    @GetMapping
    public List<BaseProduct> list(){
        return baseProductService.getBaseProducts();
    }

    @GetMapping("/{id}")
    public BaseProduct getBaseProduct(@PathVariable Long id){
        return baseProductService.getBaseProduct(id);
    }

    @PostMapping
    public ResponseEntity<BaseProduct> createBaseProduct(@RequestBody BaseProduct baseProduct){
        BaseProduct saveBaseProduct = baseProductService.createBaseProduct(baseProduct);
        return ResponseEntity.ok(saveBaseProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseProduct> updateBaseProduct(@PathVariable Long id, @RequestBody BaseProduct baseProduct){
        BaseProduct updateBaseProduct = baseProductService.updateBaseProduct(id, baseProduct);
        return ResponseEntity.ok(updateBaseProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBaseProduct(@PathVariable("id") Long id){
        baseProductService.deleteBaseProduct(id);
        return new ResponseEntity<>("Base product is deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/variant")
    public ResponseEntity<Object> addVariantToProduct(@PathVariable("id") Long id, @RequestBody ProductVariant productVariant){
        baseProductService.addVariantToProduct(id, productVariant);
        return new ResponseEntity<>("Variant added to the product successfully!", HttpStatus.OK);
    }
}
