package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
