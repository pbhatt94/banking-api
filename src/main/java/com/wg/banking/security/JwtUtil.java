package com.wg.banking.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wg.banking.config.JwtConfig;
import com.wg.banking.service.TokenBlacklistService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY;

	private final TokenBlacklistService blacklistService;
	

	@Autowired
	public JwtUtil(JwtConfig jwtConfig, TokenBlacklistService blacklistService) {
		this.SECRET_KEY = jwtConfig.getSecret();
		this.blacklistService = blacklistService;
	}

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		return claims.getSubject();
	}

	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().claims(claims).subject(subject).header().empty().add("typ", "JWT").and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(getSigningKey()).compact();
	}

	public Boolean validateToken(String token) {
		if (blacklistService.isTokenBlacklisted(token)) {
			return false; 
		}
		String username = extractUsername(token);
		if(blacklistService.isUserBlacklisted(username)) {
			return false;
		}
		return !isTokenExpired(token); 
	}

	public void blacklistToken(String token) {
		Claims claims = extractAllClaims(token);
		long expirationTime = claims.getExpiration().getTime();
		blacklistService.blacklistToken(token);
	}

}