package com.fbaron.core.service.user;

import com.fbaron.core.model.user.User;

import java.util.Optional;

/**
 * The UserService Interface provides the methods to manage the Users in the system.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public interface UserService {

    /**
     * Registers a new user in the system.
     *
     * @param user The {@link User} object to be registered. This object contains information about the user
     *             that needs to be created.
     * @return The Registered {@link User}. The returned object may contain additional information assigned
     * to it by the system during the registration process, such as an assigned identifier.
     */
    User signUp(User user);

    /**
     * Logs in a user to the system.
     * @param token The token to be used for logging in.
     * @return The logged in {@link User}
     */
    User login(String token);

    /**
     * Retrieves a user by its unique identifier (email) within the application.
     *
     * @param email The email representing the unique identifier of the user to be retrieved.
     * @return An Optional containing the {@link User} object associated with the specified unique identifier, if found.
     * If no user matches the provided identifier, an empty Optional is returned.
     */
    Optional<User> getUserByEmail(String email);

}
