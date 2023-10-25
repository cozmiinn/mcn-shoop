package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.CartEntryDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.CartEntry;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.mappers.AttributeStructMapper;
import com.mcn.shoop.mappers.CartEntryStructMapper;
import com.mcn.shoop.mappers.ProductVariantStructMapper;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.repositories.CartEntryRepository;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.validators.ProductVariantValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeService attributeService;
    private final CartEntryRepository cartEntryRepository;
    private final CartEntryService cartEntryService;
    private final ProductVariantStructMapper productVariantStructMapper;
    private final CartEntryStructMapper cartEntryStructMapper;
    private final AttributeStructMapper attributeStructMapper;


    @Autowired
    public ProductVariantService(ProductVariantRepository productVariantRepository, AttributeService attributeService, AttributeRepository attributeRepository, CartEntryRepository cartEntryRepository, CartEntryService cartEntryService, ProductVariantStructMapper productVariantStructMapper, CartEntryStructMapper cartEntryStructMapper, AttributeStructMapper attributeStructMapper) {
        this.productVariantRepository = productVariantRepository;
        this.attributeService = attributeService;
        this.attributeRepository = attributeRepository;
        this.cartEntryRepository = cartEntryRepository;
        this.cartEntryService = cartEntryService;
        this.productVariantStructMapper = productVariantStructMapper;
        this.cartEntryStructMapper = cartEntryStructMapper;
        this.attributeStructMapper = attributeStructMapper;
    }

    public List<ProductVariantDTO> getProductVariants(){
        List<ProductVariant> getProductVariants = productVariantRepository.findAll();
        List<ProductVariantDTO> productVariantDTOS;
        productVariantDTOS = getProductVariants
                .stream()
                .map(productVariantStructMapper::productVariantToProductVariantDto)
                .collect(Collectors.toList());
        return productVariantDTOS;
    }

    public ProductVariantDTO getProductVariant(Long id) {
        ProductVariant getProductVariant = productVariantRepository.findById(id).orElse(null);
        return productVariantStructMapper.productVariantToProductVariantDto(getProductVariant);
    }

    public ProductVariantDTO createProductVariant(ProductVariantDTO productVariantDTO){
        ProductVariant productVariant1 = productVariantStructMapper.productDtoToProductVariant(productVariantDTO);
        ProductVariant create = productVariantRepository.save(productVariant1);
        return productVariantStructMapper.productVariantToProductVariantDto(create);
    }

    public ProductVariantDTO updateProductVariant(Long id, ProductVariantDTO productVariantDTO){
        ProductVariantValidator.validatePV(productVariantDTO);
        ProductVariant currentProductVariant = productVariantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product variant with id " + id + " not found!"));
        currentProductVariant.setName(productVariantDTO.getName());
        currentProductVariant.setDescription(productVariantDTO.getDescription());
        currentProductVariant.setPrice(productVariantDTO.getPrice());
        currentProductVariant.setAvailableQuantity(productVariantDTO.getAvailableQuantity());
        currentProductVariant.setAddedDate(productVariantDTO.getAddedDate());

        currentProductVariant = productVariantRepository.save(currentProductVariant);

        return productVariantStructMapper.productVariantToProductVariantDto(currentProductVariant);
    }

    public void deleteProductVariant(Long id){
        productVariantRepository.deleteById(id);
    }


    public AttributeDTO addAttributesToProduct(@PathVariable("id") Long productVariantId, @PathVariable("id") Long attributeId) {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);
        if (productVariant == null) {
            new ResponseEntity<>("Product variant not found!", HttpStatus.NOT_FOUND);
        }
        Attribute attributes = attributeRepository.findById(attributeId).orElse(null);
        if (attributes == null) {
            new ResponseEntity<>("Attribute is null!", HttpStatus.BAD_REQUEST);
        }

        if (productVariant != null && productVariant.getAttribute().contains(attributes)) {
            new ResponseEntity<>("Attribute already exists!", HttpStatus.BAD_REQUEST);
        }
        List<ProductVariant> productVariants = new ArrayList<>();
        productVariants.add(productVariant);
        attributes.setVariants(productVariants);
        attributes = attributeRepository.save(attributes);

        return attributeStructMapper.attributeToAttributeDto(attributes);
    }
}
