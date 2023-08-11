package com.mcn.shoop.services;

import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.repositories.AttributeValuesRepository;
import com.mcn.shoop.validators.AttributeValuesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeValuesService {
    @Autowired
    private AttributeValuesRepository attributeValuesRepository;

    public AttributeValuesService(AttributeValuesRepository attributeValuesRepository) {
        this.attributeValuesRepository = attributeValuesRepository;
    }

    public List<AttributeValues> getAttributeValuess(){
        return attributeValuesRepository.findAll();
    }
    public AttributeValues getAttributeValues(Long id) {
        return attributeValuesRepository.findById(id).orElse(null);
    }

    public AttributeValues createAttributeValues(AttributeValues attributeValues) {
        return attributeValuesRepository.save(attributeValues);
    }

    public AttributeValues updateAttributeValues(Long id, AttributeValues attributeValues) {
        AttributeValuesValidator.validateAV(attributeValues);
        AttributeValues currentAttributeValues = attributeValuesRepository.findById(id).orElseThrow(RuntimeException::new);
        currentAttributeValues.setValue(attributeValues.getValue());

        return attributeValuesRepository.save(attributeValues);
    }

    public void deleteAttributeValues(Long id) {
        attributeValuesRepository.deleteById(id);
    }
}