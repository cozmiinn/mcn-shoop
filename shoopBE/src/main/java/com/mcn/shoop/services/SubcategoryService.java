package com.mcn.shoop.services;

import com.mcn.shoop.dtos.BaseProductDTO;
import com.mcn.shoop.dtos.SubcategoryDTO;
import com.mcn.shoop.entities.SubCategory;
import com.mcn.shoop.mappers.BaseProductStructMapper;
import com.mcn.shoop.mappers.SubcategoryStructMapper;
import com.mcn.shoop.repositories.BaseProductRepository;
import com.mcn.shoop.repositories.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryStructMapper subcategoryStructMapper;
    private final BaseProductStructMapper baseProductStructMapper;
    private final BaseProductRepository baseProductRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, SubcategoryStructMapper subcategoryStructMapper, BaseProductStructMapper baseProductStructMapper, BaseProductRepository baseProductRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.subcategoryStructMapper = subcategoryStructMapper;
        this.baseProductStructMapper = baseProductStructMapper;
        this.baseProductRepository = baseProductRepository;
    }

    public List<SubcategoryDTO> getSubcategories(){
        List<SubCategory> getSubcategories = subcategoryRepository.findAll();
        List<SubcategoryDTO> subcategoryDTOS;
        subcategoryDTOS = getSubcategories
                .stream()
                .map(subcategoryStructMapper::subcategoryToSubcategoryDTO)
                .collect(Collectors.toList());
        return subcategoryDTOS;
    }

    public SubcategoryDTO getSubcategory(Long id){
        SubCategory getCategory2 = subcategoryRepository.findById(id).orElseThrow(null);
        return subcategoryStructMapper.subcategoryToSubcategoryDTO(getCategory2);
    }

    public SubcategoryDTO createSubcategory(SubcategoryDTO subcategoryDTO){
        SubCategory Subcategory = subcategoryStructMapper.subcategoryDTOToSubcategory(subcategoryDTO);
        SubCategory create = subcategoryRepository.save(Subcategory);
        return subcategoryStructMapper.subcategoryToSubcategoryDTO(create);
    }

    public SubcategoryDTO updateSubcategory(Long id, SubcategoryDTO subcategoryDTO){
        SubCategory currentCategory2 = subcategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("SubCategory with id " + id + " not found!"));

        currentCategory2.setSubName(subcategoryDTO.getSubName());
        currentCategory2 = subcategoryRepository.save(currentCategory2);

        return subcategoryStructMapper.subcategoryToSubcategoryDTO(currentCategory2);
    }

    public void deleteSubcategory(Long id){
        subcategoryRepository.deleteById(id);
    }

    public BaseProductDTO addBaseToSubcategory(@PathVariable("id") Long subcategoryId, @PathVariable("id") Long baseProductId){
        SubCategory Subcategory = subcategoryRepository.findById(subcategoryId).orElse(null);
        if(Subcategory == null){
            new ResponseEntity<>("SubCategory not found!", HttpStatus.NOT_FOUND);
        }

        BaseProduct baseProduct = baseProductRepository.findById(baseProductId).orElse(null);
        if(baseProduct == null){
            new ResponseEntity<>("Base product not found!", HttpStatus.NOT_FOUND);
        }

        if(Subcategory != null && Subcategory.getBaseProducts().contains(baseProduct)){
            new ResponseEntity<>("SubCategory already exists!", HttpStatus.BAD_REQUEST);
        }

        baseProduct.setSubcategory(Subcategory);
        baseProduct = baseProductRepository.save(baseProduct);

        return baseProductStructMapper.baseProductToBaseProductDto(baseProduct);
    }
}
