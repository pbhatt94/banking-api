package com.wg.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wg.banking.model.BlockedUser;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, String> {

}
