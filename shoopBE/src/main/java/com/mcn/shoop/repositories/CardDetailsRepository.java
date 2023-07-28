package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {
}