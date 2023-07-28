package com.mcn.shoop.repositories;

import com.mcn.shoop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

