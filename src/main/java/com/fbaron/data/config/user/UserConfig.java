package com.fbaron.data.config.user;

import com.fbaron.core.repo.user.UserRepository;
import com.fbaron.data.jpa.user.UserJpaAdapter;
import com.fbaron.data.jpa.user.mapper.UserJpaMapper;
import com.fbaron.data.jpa.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final UserJpaRepository userJpaRepository;
    private final UserJpaMapper userJpaMapper;

    /**
     * Creates an instance of the desired implementation of UserRepository based on the chosen repository provider.
     *
     * @return The {@link UserRepository} UserRepository bean.
     */
    @Bean
    public UserRepository userRepository() {
        return new UserJpaAdapter(userJpaRepository, userJpaMapper);
    }

}
