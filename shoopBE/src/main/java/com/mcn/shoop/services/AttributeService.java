package com.mcn.shoop.services;

import com.mcn.shoop.dtos.AttributeDTO;
import com.mcn.shoop.dtos.AttributeValuesDTO;
import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.entities.AttributeValues;
import com.mcn.shoop.mappers.AttributeStructMapper;
import com.mcn.shoop.mappers.AttributeValuesStructMapper;
import com.mcn.shoop.repositories.AttributeRepository;
import com.mcn.shoop.repositories.AttributeValuesRepository;
import com.mcn.shoop.validators.AttributeValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;
    private final AttributeStructMapper attributeStructMapper;
    private final AttributeValuesStructMapper attributeValuesStructMapper;
    private final AttributeValuesRepository attributeValuesRepository;

    public AttributeService(AttributeRepository attributeRepository, AttributeStructMapper attributeStructMapper, AttributeValuesStructMapper attributeValuesStructMapper, AttributeValuesRepository attributeValuesRepository) {
        this.attributeRepository = attributeRepository;
        this.attributeStructMapper = attributeStructMapper;
        this.attributeValuesStructMapper = attributeValuesStructMapper;
        this.attributeValuesRepository = attributeValuesRepository;
    }

    public List<AttributeDTO> getAttributes() {
        List<Attribute> getAttributes = attributeRepository.findAll();
        List<AttributeDTO> attributeDTOS;
        attributeDTOS = getAttributes
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

    public AttributeValuesDTO addValuesToAttribute(@PathVariable("id") Long attributeId, @PathVariable("id") Long valuesId) {
        Attribute attribute = attributeRepository.findById(attributeId).orElse(null);
        if (attribute == null) {
            new ResponseEntity<>("Attribute not found!", HttpStatus.NOT_FOUND);
        }

        AttributeValues attributeValues = attributeValuesRepository.findById(valuesId).orElse(null);
        if(attributeValues == null){
            new ResponseEntity<>("Values not found!", HttpStatus.NOT_FOUND);
        }

        attributeValues.setAttribute(attribute);
        attributeValues = attributeValuesRepository.save(attributeValues);

        return attributeValuesStructMapper.attributeValuesToAttributeValuesDto(attributeValues);
    }

}