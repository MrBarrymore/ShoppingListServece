package com.testtask.shoppinglistservice.repositories;

import com.testtask.shoppinglistservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
