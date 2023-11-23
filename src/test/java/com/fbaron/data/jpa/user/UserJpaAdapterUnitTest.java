package com.fbaron.data.jpa.user;

import com.fbaron.core.model.user.User;
import com.fbaron.data.jpa.user.entity.UserEntity;
import com.fbaron.data.jpa.user.mapper.UserJpaMapper;
import com.fbaron.data.jpa.user.repository.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterUnitTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private UserJpaMapper userJpaMapper;

    @InjectMocks
    private UserJpaAdapter underTest;

    @Test
    void saveUser_shouldSaveUserInRepository() {
        User expectedUser = User.builder().name("Julio").build();
        when(userJpaMapper.modelToEntity(expectedUser)).thenReturn(new UserEntity());
        when(userJpaRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        when(userJpaMapper.entityToModel(any(UserEntity.class))).thenReturn(expectedUser);

        User savedUser = underTest.saveUser(expectedUser);

        assertEquals(expectedUser, savedUser);
        verify(userJpaRepository).save(any(UserEntity.class));
        verify(userJpaMapper).modelToEntity(expectedUser);
        verify(userJpaMapper).entityToModel(any(UserEntity.class));

    }

    @Test
    void findUserByEmail_shouldReturnUserOptional() {
        String userEmail = "julio@testssw.cl";
        User expectedUser = User.builder().name("Julio").email(userEmail).build();
        when(userJpaRepository.findByEmail(userEmail)).thenReturn(Optional.of(new UserEntity()));
        when(userJpaMapper.entityToModel(any(UserEntity.class))).thenReturn(expectedUser);

        Optional<User> foundUser = underTest.findUserByEmail(userEmail);

        assertEquals(expectedUser, foundUser.orElse(null));
        verify(userJpaRepository).findByEmail(userEmail);
        verify(userJpaMapper).entityToModel(any(UserEntity.class));
    }

}