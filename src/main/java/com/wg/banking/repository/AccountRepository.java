package com.wg.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wg.banking.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}