package com.fbaron.data.jpa.user.repository;

import com.fbaron.data.jpa.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

}
