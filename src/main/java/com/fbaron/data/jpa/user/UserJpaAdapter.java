package com.fbaron.data.jpa.user;

import com.fbaron.core.model.user.User;
import com.fbaron.core.repo.user.UserRepository;
import com.fbaron.data.jpa.user.mapper.UserJpaMapper;
import com.fbaron.data.jpa.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * The UserJpaAdapter class provides the implementation of the {@link UserRepository} interface.
 *
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Slf4j
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    /**
     * Saves a User with the provided User details in the repository.
     *
     * @param user The object containing the User details.
     * @return The created {@link User} object with a unique identifier.
     */
    @Override
    public User saveUser(User user) {
        var userEntity = userJpaMapper.modelToEntity(user);
        userEntity = userJpaRepository.save(userEntity);
        user = userJpaMapper.entityToModel(userEntity);
        return user;
    }

    /**
     * Retrieve a User with a given User email from the repository.
     *
     * @param email the Email of the User to search for.
     * @return The object {@link Optional <User>} with the User details.
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userJpaMapper::entityToModel);
    }

}
