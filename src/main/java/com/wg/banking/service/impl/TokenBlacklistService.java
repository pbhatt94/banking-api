package com.wg.banking.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.wg.banking.model.BlockedUser;
import com.wg.banking.repository.BlockedUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenBlacklistService {

	private final Set<String> blacklistedTokens = new HashSet<>();
	private final BlockedUserRepository repo;

	public void blacklistToken(String token) {
		blacklistedTokens.add(token);
	}

	public boolean isTokenBlacklisted(String token) {
		return blacklistedTokens.contains(token);
	}
	
	public void blacklistUser(String username) {
		BlockedUser user = new BlockedUser();
		user.setUsername(username);
		repo.save(user);
	}

	public boolean isUserBlacklisted(String username) {
		Optional<BlockedUser> user = repo.findById(username);
		if(user.isEmpty()) return false;
		return true;
	}
}