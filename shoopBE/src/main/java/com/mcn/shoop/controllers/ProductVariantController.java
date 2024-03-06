package com.mcn.shoop.controllers;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.services.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/products/variant")
public class ProductVariantController {

    private final ProductVariantService productVariantService;
    @Autowired
    public ProductVariantController(ProductVariantService productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductVariantDTO>> findAll(@PageableDefault(size = 10)Pageable pageable,
                                                           @RequestParam(name="keyword", required = false) String keyword,
                                                           @RequestParam(name="sort", defaultValue = "name") String sort,
                                                           @RequestParam(name="order", defaultValue = "asc") String order){
        Page<ProductVariantDTO> productVariantDTOPage;
        if(keyword != null && !keyword.isEmpty()){
            List<ProductVariantDTO> productVariantDTOS = productVariantService.searchProductVariantByName(keyword);
            productVariantDTOPage = new PageImpl<>(productVariantDTOS, pageable, productVariantDTOS.size());
        }else {
            //if no search criteria, then retrieve based on sorting options
            Sort.Direction direction = "asc".equalsIgnoreCase(order)?Sort.Direction.ASC : Sort.Direction.DESC;
            Sort sorting = Sort.by(direction, sort);
           productVariantDTOPage =  productVariantService.getProductVariants(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sorting));
        }
        return new ResponseEntity<>(productVariantDTOPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductVariantDTO>> searchProducts(@RequestParam("keyword") String keyword){
        List<ProductVariantDTO> productVariantDTOS = productVariantService.searchProductVariantByName(keyword);
        return new ResponseEntity<>(productVariantDTOS, HttpStatus.OK);
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
    public ResponseEntity<AttributeDTO> addAttributesToProduct(@PathVariable("id") Long id, @PathVariable("attributeId") Long attributeId) {
        AttributeDTO attributes = productVariantService.addAttributesToProduct(id, attributeId);
        return new ResponseEntity<>(attributes, HttpStatus.OK);
    }
}

