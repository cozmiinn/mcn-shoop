package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
