package com.fbaron.core.domain.jwt;

import com.fbaron.core.service.jwt.JwtService;
import com.fbaron.data.config.jwt.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DomainJwtService implements JwtService {

    public static final String ERROR_VALIDATING_TOKEN = "Error validating token: {}";

    private final JwtConfig jwtConfig;

    /**
     * Creates a new Jwt with the provided Jwt details.
     *
     * @param username The username of the user to create the Jwt.
     * @return The created String with the Jwt details.
     */
    @Override
    public String generateToken(String username) {
        Instant now = Instant.now();
        Instant expiryDate =Instant.now().plusSeconds(jwtConfig.getExpiration());
        var secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(jwtConfig.getSecret().getBytes()));
        return Jwts.builder()
                .issuer(jwtConfig.getIssuer())
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiryDate))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extracts the username from the provided Jwt.
     *
     * @param token The Jwt to extract the username from.
     * @return The extracted username.
     */
    @Override
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Validates the provided token.
     *
     * @param token The token to validate.
     * @return boolean value indicating if the token is valid or not.
     */
    @Override
    public boolean validateToken(String token) {
        try {
            getClaims(token);
        } catch (Exception e) {
            log.error(ERROR_VALIDATING_TOKEN, e.getMessage());
            return false;
        }
        return true;
    }

    private Claims getClaims(String token) {
        var secretKey = Keys.hmacShaKeyFor(Base64.getEncoder().encode(jwtConfig.getSecret().getBytes()));
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
}
