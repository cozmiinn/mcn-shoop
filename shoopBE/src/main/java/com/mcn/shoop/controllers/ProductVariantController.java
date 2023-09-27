package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.services.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProductVariantDTO> list(){
        return productVariantService.getProductVariants();
    }

    @GetMapping("/find/{id}")
    public ProductVariantDTO getProductVariant(@PathVariable Long id){
        return productVariantService.getProductVariant(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductVariantDTO> createProductVariant(@RequestBody ProductVariantDTO productVariantDTO){
        ProductVariantDTO savedProductVariant = productVariantService.createProductVariant(productVariantDTO);
        return ResponseEntity.ok(savedProductVariant);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductVariantDTO> updateProductVariant(@PathVariable Long id, @RequestBody ProductVariantDTO productVariantDTO){
        ProductVariantDTO updateProductVariant = productVariantService.updateProductVariant(id, productVariantDTO);
        return ResponseEntity.ok(updateProductVariant);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProductVariant(@PathVariable("id") Long id){
        productVariantService.deleteProductVariant(id);
    }

    @PostMapping("/{id}/attribute/{attributeId}")
    public ResponseEntity<Object> addAttributesToProduct(@PathVariable("id") Long id, @PathVariable("attributeId") Long attributeId, AttributeDTO attributeDTO) {
        return productVariantService.addAttributesToProduct(id, attributeId, attributeDTO);
    }

    @PostMapping("/{id}/entry/{entryId}")
    public ResponseEntity<Object> addProductToEntry(@PathVariable("id") Long id, @PathVariable("entryId") Long entryId, CartEntryDTO cartEntryDTO) {
        return productVariantService.addProductToEntry(id, entryId, cartEntryDTO);
    }
}

