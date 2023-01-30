package com.tokioshool.filmotokio.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsername(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public Date getExpirationDate(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userName);
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDate(token);
		return expiration.before(new Date());
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsername(token);
		return username.equals(userDetails.getUsername()) && (!isTokenExpired(token));
		
	}
}
