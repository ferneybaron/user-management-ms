package com.fbaron.core.repo.user;

import com.fbaron.core.model.user.User;

import java.util.Optional;

/**
 * The UserRepository Interface provides the methods to manage the Users at repository level.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public interface UserRepository {

    /**
     * Saves a User with the provided User details in the repository.
     *
     * @param user The object containing the User details.
     * @return The created {@link User} object with a unique identifier.
     */
    User saveUser(User user);

    /**
     * Retrieve a User with a given User email from the repository.
     *
     * @param email the Email of the User to search for.
     * @return The object {@link User} with the User details.
     */
    Optional<User> findUserByEmail(String email);

}
