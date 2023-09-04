package com.mcn.shoop.services;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.repositories.CartEntryRepository;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.validators.ProductVariantValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;

    private final CartEntryRepository cartEntryRepository;

    private final CartEntryService cartEntryService;


    @Autowired
    public ProductVariantService(ProductVariantRepository productVariantRepository, AttributeService attributeService, AttributeRepository attributeRepository, CartEntryRepository cartEntryRepository, CartEntryService cartEntryService) {
        this.productVariantRepository = productVariantRepository;
        this.attributeService = attributeService;
        this.attributeRepository = attributeRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.cartEntryService = cartEntryService;
    }

    public List<ProductVariant> getProductVariants(){
        return productVariantRepository.findAll();
    }

    public ProductVariant getProductVariant(Long id) {
       return productVariantRepository.findById(id).orElse(null);
    }

    public ProductVariant createProductVariant(ProductVariant productVariant){
        return productVariantRepository.save(productVariant);
    }

    public ProductVariant updateProductVariant(Long id, ProductVariant productVariant){
        ProductVariantValidator.validatePV(productVariant);
        ProductVariant currentProductVariant = productVariantRepository.findById(id).orElseThrow(RuntimeException::new);
        currentProductVariant.setName(productVariant.getName());
        currentProductVariant.setDescription(productVariant.getDescription());
        currentProductVariant.setPrice(productVariant.getPrice());
        currentProductVariant.setAvailableQuantity(productVariant.getAvailableQuantity());
        currentProductVariant.setAddedDate(productVariant.getAddedDate());

        return productVariantRepository.save(currentProductVariant);
    }

    public void deleteProductVariant(Long id){
        productVariantRepository.deleteById(id);
    }


    public ResponseEntity<Object> addAttributesToProduct(Long id, Long attributeId) {
        ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
        if (productVariant == null) {
            return new ResponseEntity<>("Product variant not found", HttpStatus.NOT_FOUND);
        }

        Attribute attributes = attributeRepository.findById(attributeId).orElse(null);
        if (attributes == null) {
            return new ResponseEntity<>("Attribute is null!", HttpStatus.BAD_REQUEST);
        }

        if (productVariant.getAttribute().contains(attributes)) {
            return new ResponseEntity<>("Attribute already exists", HttpStatus.BAD_REQUEST);
        }

        List<ProductVariant> productVariants = new ArrayList<>();
        productVariants.add(productVariant);

        productVariant.getAttribute().add(attributes);
        attributes.setVariants(productVariants);

        attributeService.createAttribute(attributes);

        return new ResponseEntity<>("Attributes added to the product successfully", HttpStatus.OK);
    }

    public ResponseEntity<Object> addProductToEntry(Long id, Long entryId){
        ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
        if(productVariant == null){
            return new ResponseEntity<>("Entry not found!", HttpStatus.NOT_FOUND);
        }

        CartEntry cartEntry = cartEntryRepository.findById(entryId).orElse(null);
        if(cartEntry == null){
            return new ResponseEntity<>("Product not found!", HttpStatus.NOT_FOUND);
        }

        productVariant.getCartEntries().add(cartEntry);
        cartEntry.setProduct(productVariant);
        cartEntryService.createCartEntry(cartEntry);

        return new ResponseEntity<>("Product added to the entry successfully!", HttpStatus.OK);
    }
}
