package com.mcn.shoop.services;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.validators.AttributeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    private final AttributeValuesService attributeValuesService;

    public AttributeService(AttributeRepository attributeRepository, AttributeValuesService attributeValuesService) {
        this.attributeRepository = attributeRepository;
        this.attributeValuesService = attributeValuesService;
    }

    public List<Attribute> getAttributes() {
        return attributeRepository.findAll();
    }

    public Attribute getAttribute(Long id) {
        return attributeRepository.findById(id).orElse(null);
    }

    public Attribute createAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    public Attribute updateAttribute(Long id, Attribute attribute) {
        AttributeValidator.validateAttribute(attribute);
        Attribute currentAttribute = attributeRepository.findById(id).orElseThrow(RuntimeException::new);
        currentAttribute.setAttribute(attribute.getAttribute());
        return attributeRepository.save(currentAttribute);
    }

    public void deleteAttribute(Long id) {
        attributeRepository.deleteById(id);
    }

    public void addValuesToAttribute(Long id, AttributeValues attributeValues) {
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        if (attribute != null) {
            attributeValues.setAttribute(attribute);
            attributeValuesService.createAttributeValues(attributeValues);
        } else {
            new ResponseEntity<>("Attribute not found!", HttpStatus.NOT_FOUND);
        }
    }
}