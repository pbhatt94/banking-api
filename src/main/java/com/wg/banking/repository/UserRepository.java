package com.wg.banking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wg.banking.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUsername(String username);
}