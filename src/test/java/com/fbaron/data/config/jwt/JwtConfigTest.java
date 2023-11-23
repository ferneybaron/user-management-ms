package com.fbaron.data.config.jwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JwtConfigTest {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    void testJwtConfigProperties() {
        assertNotNull(jwtConfig);
        assertNotNull(jwtConfig.getIssuer());
        assertNotNull(jwtConfig.getSecret());
        assertNotNull(jwtConfig.getExpiration());
    }

}