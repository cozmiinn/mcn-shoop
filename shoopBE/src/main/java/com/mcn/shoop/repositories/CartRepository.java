package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
