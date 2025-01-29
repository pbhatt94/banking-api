package com.wg.banking.service;

public interface TokenBlacklistService {
	public void blacklistToken(String token);

	public boolean isTokenBlacklisted(String token);

	public void blacklistUser(String username);

	public boolean isUserBlacklisted(String username);
}
