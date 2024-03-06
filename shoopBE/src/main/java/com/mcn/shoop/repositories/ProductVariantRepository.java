package com.mcn.shoop.repositories;

import com.mcn.shoop.dtos.ProductVariantDTO;
import com.mcn.shoop.entities.ProductVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    Page<ProductVariant> findAll (Pageable pageable);

    @Query("SELECT p FROM ProductVariant p WHERE p.name LIKE %:keyword%")
    List<ProductVariant> searchProductVariantByName(String keyword);


}
