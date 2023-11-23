package com.fbaron.core.constant;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public final class UserConstant {

    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String NOT_BE_TRUSTED = "JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.";
    public static final String USER_NOT_FOUND_WITH_EMAIL = "User not found with email: %s";

    private UserConstant() {
    }

}
