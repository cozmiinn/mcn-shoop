package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.services.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/products")
public class BaseProductController {


    private final BaseProductService baseProductService;

    @Autowired
    public BaseProductController(BaseProductService baseProductService) {
        this.baseProductService = baseProductService;
    }

    @GetMapping
    public List<BaseProductDTO> list(){
        return baseProductService.getBaseProducts();
    }

    @GetMapping("/find/{id}")
    public BaseProductDTO getBaseProduct(@PathVariable Long id){
        return baseProductService.getBaseProduct(id);
    }

    @PostMapping("/add")
    public ResponseEntity<BaseProductDTO> createBaseProduct(@RequestBody BaseProductDTO baseProductDTO){
        BaseProductDTO saveBaseProduct = baseProductService.createBaseProduct(baseProductDTO);
        return ResponseEntity.ok(saveBaseProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BaseProductDTO> updateBaseProduct(@PathVariable Long id, @RequestBody BaseProductDTO baseProductDTO){
        BaseProductDTO updateBaseProduct = baseProductService.updateBaseProduct(id, baseProductDTO);
        return ResponseEntity.ok(updateBaseProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBaseProduct(@PathVariable("id") Long id){
        baseProductService.deleteBaseProduct(id);
        return new ResponseEntity<>("Base product is deleted successfully", HttpStatus.OK);
    }

    @PostMapping("/{id}/variant/{productVariantId}")
    public ResponseEntity<ProductVariantDTO> addVariantToProduct(@PathVariable("id") Long id, @PathVariable("productVariantId") Long productVariantId){
        ProductVariantDTO productVariant = baseProductService.addVariantToProduct(id, productVariantId);
        return new ResponseEntity<>(productVariant, HttpStatus.OK);
    }
}
