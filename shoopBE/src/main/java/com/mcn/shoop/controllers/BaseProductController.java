package com.mcn.shoop.controllers;

import com.mcn.shoop.entities.BaseProduct;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.BaseProductRepository;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.services.BaseProductService;
import com.mcn.shoop.services.ProductVariantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/products")
public class BaseProductController {

    private final BaseProductRepository baseProductRepository;
    private final BaseProductService baseProductService;
    private final ProductVariantService productVariantService;

    public BaseProductController(BaseProductRepository baseProductRepository, BaseProductService baseProductService, ProductVariantRepository productVariantRepository, ProductVariantService productVariantService) {
        this.baseProductRepository = baseProductRepository;
        this.baseProductService = baseProductService;
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public List<BaseProduct> list(){
        return baseProductRepository.findAll();
    }

    @GetMapping("/{id}")
    public BaseProduct getBaseProduct(@PathVariable Long id){
        return baseProductRepository.findById(id).orElseThrow(RuntimeException::new);
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
        BaseProduct baseProduct = baseProductRepository.findById(id).orElse(null);
        if(baseProduct==null){
            return new ResponseEntity<>("Base product not found!", HttpStatus.NOT_FOUND);
        }

        productVariant.setBaseProduct(baseProduct);
        productVariantService.createProductVariant(productVariant);
        return new ResponseEntity<>("Variant added to the product successfully!", HttpStatus.OK);
    }
}
