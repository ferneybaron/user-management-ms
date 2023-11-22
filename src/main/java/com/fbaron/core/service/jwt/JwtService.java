package com.fbaron.core.service.jwt;

/**
 * The JwtService Interface provides the methods to manage the JSON Web Tokens.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public interface JwtService {

    /**
     * Creates a new Jwt with the provided Jwt details.
     *
     * @param username The username of the user to create the Jwt.
     * @return The created String with the Jwt details.
     */
    String generateToken(String username);

    /**
     * Extracts the username from the provided Jwt.
     * @param token The Jwt to extract the username from.
     * @return The extracted username.
     */
    String extractUsername(String token);

    /**
     * Validates the provided token.
     * @param token The token to validate.
     * @return boolean value indicating if the token is valid or not.
     */
    boolean validateToken(String token);

}
