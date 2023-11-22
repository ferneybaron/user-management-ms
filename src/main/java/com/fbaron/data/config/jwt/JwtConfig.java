package com.fbaron.data.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String issuer;
    private String secret;
    private Long expiration;

}
