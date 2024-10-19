package com.wg.banking.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import jakarta.annotation.Nullable;

import com.wg.banking.model.User;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	public Optional<User> findByUsername(String username);

	public Page<User> findAll(@Nullable Specification<User> user, @NonNull Pageable pageable);
}