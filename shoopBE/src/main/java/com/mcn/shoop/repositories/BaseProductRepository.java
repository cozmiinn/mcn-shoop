package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseProductRepository extends JpaRepository<BaseProduct, Long> { }
