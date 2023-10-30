package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
