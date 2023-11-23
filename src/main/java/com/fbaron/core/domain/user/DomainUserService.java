package com.fbaron.core.domain.user;

import com.fbaron.core.exception.jwt.JwtSignatureException;
import com.fbaron.core.exception.user.UserAlreadyExistsException;
import com.fbaron.core.exception.user.UserNotFoundException;
import com.fbaron.core.model.user.User;
import com.fbaron.core.repo.user.UserRepository;
import com.fbaron.core.service.jwt.JwtService;
import com.fbaron.core.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.fbaron.core.constant.UserConstant.*;

/**
 * The DomainUserService class provides the implementation of the {@link UserService} interface.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DomainUserService implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user in the system.
     *
     * @param user The {@link User} object to be registered. This object contains information about the user
     *             that needs to be created.
     * @return The Registered {@link User}. The returned object may contain additional information assigned
     * to it by the system during the registration process, such as an assigned identifier.
     */
    @Override
    public User signUp(User user) {
        if (getUserByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        user.setId(UUID.randomUUID());
        var dateTime = LocalDateTime.now();
        user.setCreated(dateTime);
        user.setLastLogin(dateTime);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(jwtService.generateToken(user.getEmail()));
        return userRepository.saveUser(user);
    }

    /**
     * Logs in a user to the system.
     *
     * @param token The token to be used for logging in.
     * @return The logged in {@link User}
     */
    @Override
    public User login(String token) {
        if (!jwtService.validateToken(token)) throw new JwtSignatureException(NOT_BE_TRUSTED);
        var username = jwtService.extractUsername(token);
        var user = getUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_WITH_EMAIL, username)));
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtService.generateToken(username));
        return user;
    }

    /**
     * Retrieves a user by its unique identifier (email) within the application.
     *
     * @param email The email representing the unique identifier of the user to be retrieved.
     * @return An Optional containing the {@link User} object associated with the specified unique identifier, if found.
     * If no user matches the provided identifier, an empty Optional is returned.
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
