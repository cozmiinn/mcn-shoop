package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.mappers.AttributeValuesStructMapper;
import com.mcn.shoop.repositories.AttributeValuesRepository;
import com.mcn.shoop.validators.AttributeValuesValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeValuesService {
    private final AttributeValuesRepository attributeValuesRepository;
    private final AttributeValuesStructMapper attributeValuesStructMapper;

    @Autowired
    public AttributeValuesService(AttributeValuesRepository attributeValuesRepository, AttributeValuesStructMapper attributeValuesStructMapper) {
        this.attributeValuesRepository = attributeValuesRepository;
        this.attributeValuesStructMapper = attributeValuesStructMapper;
    }

    public List<AttributeValuesDTO> getAttributeValues(){
        List<AttributeValues> getAttributeValues = attributeValuesRepository.findAll();
        List<AttributeValuesDTO> attributeValuesDTOS;
        attributeValuesDTOS = getAttributeValues
                .stream()
                .map(attributeValuesStructMapper::attributeValuesToAttributeValuesDto)
                .collect(Collectors.toList());
        return attributeValuesDTOS;
    }

    public AttributeValuesDTO getAttributeValues(Long id) {
        AttributeValues getAttributeValues = attributeValuesRepository.findById(id).orElse(null);
        return attributeValuesStructMapper.attributeValuesToAttributeValuesDto(getAttributeValues);
    }

    public AttributeValuesDTO createAttributeValues(AttributeValuesDTO attributeValuesDTO) {
        AttributeValues attributeValues = attributeValuesStructMapper.attributeValuesDtoToAttributeValues(attributeValuesDTO);
        AttributeValues create = attributeValuesRepository.save(attributeValues);
        return attributeValuesStructMapper.attributeValuesToAttributeValuesDto(create);
    }

    public AttributeValuesDTO updateAttributeValues(Long id, AttributeValuesDTO attributeValuesDTO) {
        AttributeValuesValidator.validateAV(attributeValuesDTO);

        AttributeValues currentAttributeValues = attributeValuesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Values with id " + id + " not found!"));
        currentAttributeValues.setValue(attributeValuesDTO.getValue());

        currentAttributeValues = attributeValuesRepository.save(currentAttributeValues);

        return attributeValuesStructMapper.attributeValuesToAttributeValuesDto(currentAttributeValues);
    }

    public void deleteAttributeValues(Long id) {
        attributeValuesRepository.deleteById(id);
    }
}