package com.mcn.shoop.services;

import com.mcn.shoop.entities.Attribute;
import com.mcn.shoop.repositories.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    public AttributeService(AttributeRepository attributeRepository){
        this.attributeRepository = attributeRepository;
    }

    public Attribute getAttribute(Long id){
        return attributeRepository.findById(id).orElse(null);
    }

    public Attribute createAttribute(Attribute attribute){
        return attributeRepository.save(attribute);
    }

    public Attribute updateAttribute(Long id, Attribute attribute){
        Attribute currentAttribute = attributeRepository.findById(id).orElseThrow(RuntimeException::new);
        currentAttribute.setAttribute(attribute.getAttribute());

        return attributeRepository.save(currentAttribute);
    }

    public void deleteAttribute(Long id){
        attributeRepository.deleteById(id);
    }
}