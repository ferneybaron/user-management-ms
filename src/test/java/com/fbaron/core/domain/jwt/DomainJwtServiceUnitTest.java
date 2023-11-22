package com.fbaron.core.domain.jwt;

import com.fbaron.data.config.jwt.JwtConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DomainJwtServiceUnitTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private DomainJwtService underTest;

    @BeforeEach
    void setUp() {
        openMocks(this);
        when(jwtConfig.getIssuer()).thenReturn("testIssuer");
        when(jwtConfig.getSecret()).thenReturn("5bd9d398546f420f99c0a5ca8924d731");
        when(jwtConfig.getExpiration()).thenReturn(3600L);
    }

    @Test
    void generateToken() {
        String username = "julio@testssw.cl";
        String token = underTest.generateToken(username);
        assertNotNull(token);
    }

    @Test
    void extractUsername() {
        String username = "julio@testssw.cl";
        String token = underTest.generateToken(username);
        String extractedUsername = underTest.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void validateValidToken() {
        String username = "julio@testssw.cl";
        String token = underTest.generateToken(username);
        boolean isValid = underTest.validateToken(token);
        assertTrue(isValid);
    }

    @Test
    void validateInvalidToken() {
        String invalidToken = "invalidToken";
        boolean isValid = underTest.validateToken(invalidToken);
        assertFalse(isValid);
    }

}