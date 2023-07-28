package com.mcn.shoop.services;

import com.mcn.shoop.entities.BaseProduct;
import com.mcn.shoop.repositories.BaseProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseProductService {

    @Autowired
    private BaseProductRepository baseProductRepository;

    public BaseProductService(BaseProductRepository baseProductRepository) {
        this.baseProductRepository = baseProductRepository;
    }

    public BaseProduct getBaseProduct(Long id){
        return baseProductRepository.findById(id).orElse(null);
    }

    public BaseProduct createBaseProduct(BaseProduct baseProduct){
        return baseProductRepository.save(baseProduct);
    }

    public BaseProduct updateBaseProduct(Long id, BaseProduct baseProduct){
        BaseProduct currentBaseProduct = baseProductRepository.findById(id).orElseThrow(RuntimeException::new);
        currentBaseProduct.setType(baseProduct.getType());

        return baseProductRepository.save(currentBaseProduct);
    }

    public void deleteBaseProduct(Long id){
        baseProductRepository.deleteById(id);
    }
}
