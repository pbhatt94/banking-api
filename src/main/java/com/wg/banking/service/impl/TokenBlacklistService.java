package com.wg.banking.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

	private final Set<String> blacklistedTokens = new HashSet<>();

	// Add token to the blacklist
	public void blacklistToken(String token) {
		blacklistedTokens.add(token);
	}

	// Check if a token is blacklisted
	public boolean isTokenBlacklisted(String token) {
		return blacklistedTokens.contains(token);
	}
}