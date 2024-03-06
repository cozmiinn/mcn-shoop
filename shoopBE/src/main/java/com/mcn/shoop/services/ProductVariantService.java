package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.ProductVariant;
import com.mcn.shoop.mappers.AttributeStructMapper;
import com.mcn.shoop.mappers.ProductVariantStructMapper;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.repositories.ProductVariantRepository;
import com.mcn.shoop.validators.ProductVariantValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ProductVariantStructMapper productVariantStructMapper;
    private final AttributeStructMapper attributeStructMapper;


    @Autowired
    public ProductVariantService(ProductVariantRepository productVariantRepository, AttributeRepository attributeRepository, ProductVariantStructMapper productVariantStructMapper, AttributeStructMapper attributeStructMapper) {
        this.productVariantRepository = productVariantRepository;
        this.attributeRepository = attributeRepository;
        this.productVariantStructMapper = productVariantStructMapper;
        this.attributeStructMapper = attributeStructMapper;
    }

//la fiecare queri din bd, ofera 10rezultate/pagin, nu toate simultan
    public Page<ProductVariantDTO> getProductVariants (Pageable pageable){
        Page<ProductVariant> getProductVariants = productVariantRepository.findAll(pageable);
        Page<ProductVariantDTO> productVariantDTOS;
        productVariantDTOS = getProductVariants
                .map(productVariantStructMapper::productVariantToProductVariantDto);
        return productVariantDTOS;
    }

    //cautare dupa nume in db
    public List<ProductVariantDTO> searchProductVariantByName(String keyword){
        List<ProductVariant> productVariants = productVariantRepository.searchProductVariantByName(keyword);
        List<ProductVariantDTO> productVariantDTOS;
        productVariantDTOS = productVariants.stream()
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
        currentProductVariant.setPictureURL(productVariantDTO.getPictureURL());

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
