package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VouchersRepository extends JpaRepository<Voucher, Long> {
}