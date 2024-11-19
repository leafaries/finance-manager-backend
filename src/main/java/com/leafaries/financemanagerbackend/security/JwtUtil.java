package com.leafaries.financemanagerbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for handling JSON Web Tokens (JWT).
 * Provides methods for generating, validating, and extracting information from JWT tokens.
 */
@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    /**
     * Generates a JWT token for the given username.
     *
     * @param username the username for which the token is generated
     * @return the generated JWT token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Creates a JWT token with the specified claims and subject.
     *
     * @param claims a map of claims to be included in the token
     * @param subject the subject (typically the username) of the token
     * @return the created JWT token
     */
    private String createToken(Map<String, Object> claims, String subject) {
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY);
        return builder.compact();
    }

    /**
     * Validates the given JWT token against the specified username.
     *
     * @param token the JWT token to validate
     * @param username the username to validate against
     * @return {@code true} if the token is valid and not expired; {@code false} otherwise
     */
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /**
     * Extracts the username (subject) from the given JWT token.
     *
     * @param token the JWT token from which to extract the username
     * @return the extracted username
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token the JWT token from which to extract claims
     * @return the extracted claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token the JWT token to check
     * @return {@code true} if the token is expired; {@code false} otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
