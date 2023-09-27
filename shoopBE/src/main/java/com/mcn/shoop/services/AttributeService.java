package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.mappers.AttributeStructMapper;
import com.mcn.shoop.mappers.AttributeValuesStructMapper;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.validators.AttributeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeValuesService attributeValuesService;
    private final AttributeStructMapper attributeStructMapper;
    private final AttributeValuesStructMapper attributeValuesStructMapper;

    public AttributeService(AttributeRepository attributeRepository, AttributeValuesService attributeValuesService, AttributeStructMapper attributeStructMapper, AttributeValuesStructMapper attributeValuesStructMapper) {
        this.attributeRepository = attributeRepository;
        this.attributeValuesService = attributeValuesService;
        this.attributeStructMapper = attributeStructMapper;
        this.attributeValuesStructMapper = attributeValuesStructMapper;
    }

    public List<AttributeDTO> getAttributes() {
        List<Attribute> getAttributes = attributeRepository.findAll();
        List<AttributeDTO> attributeDTOS = getAttributes
                .stream()
                .map(attributeStructMapper::attributeToAttributeDto)
                .collect(Collectors.toList());
        return attributeDTOS;
    }

    public AttributeDTO getAttribute(Long id) {
        Attribute getAttribute = attributeRepository.findById(id).orElse(null);
        return attributeStructMapper.attributeToAttributeDto(getAttribute);
    }

    public AttributeDTO createAttribute(AttributeDTO attributeDTO) {
        Attribute attribute = attributeStructMapper.attributeDtoToAttribute(attributeDTO);
        Attribute create = attributeRepository.save(attribute);
        return attributeStructMapper.attributeToAttributeDto(create);
    }

    public AttributeDTO updateAttribute(Long id, AttributeDTO attributeDTO) {
        AttributeValidator.validateAttribute(attributeDTO);

        Attribute currentAttribute = attributeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Attribute with id " + id + " not found!"));
        currentAttribute.setAttribute(attributeDTO.getAttribute());

        currentAttribute = attributeRepository.save(currentAttribute);

        return attributeStructMapper.attributeToAttributeDto(currentAttribute);
    }

    public void deleteAttribute(Long id) {
        attributeRepository.deleteById(id);
    }

    public void addValuesToAttribute(Long id, AttributeValuesDTO attributeValuesDTO) {
        Attribute attribute = attributeRepository.findById(id).orElse(null);
        AttributeValues attributeValues = attributeValuesStructMapper.attributeValuesDtoToAttributeValues(attributeValuesDTO);
        if (attribute != null) {
            attributeValues.setAttribute(attribute);
            attributeValuesService.createAttributeValues(attributeValuesDTO);
        } else {
            new ResponseEntity<>("Attribute not found!", HttpStatus.NOT_FOUND);
        }
        attributeValuesStructMapper.attributeValuesToAttributeValuesDto(attributeValues);
    }
}