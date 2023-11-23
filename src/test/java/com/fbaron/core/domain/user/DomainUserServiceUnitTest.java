package com.fbaron.core.domain.user;

import com.fbaron.core.exception.jwt.JwtSignatureException;
import com.fbaron.core.exception.user.UserAlreadyExistsException;
import com.fbaron.core.exception.user.UserNotFoundException;
import com.fbaron.core.model.user.User;
import com.fbaron.core.repo.user.UserRepository;
import com.fbaron.core.service.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DomainUserServiceUnitTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private DomainUserService underTest;

    @Test
    void signUp_shouldRegisterNewUser() {
        var password = "a2asfGfdfdf4";
        User expectedUser = User.builder()
                .id(UUID.randomUUID())
                .email("julio@testssw.cl")
                .password(password)
                .build();

        when(userRepository.findUserByEmail(expectedUser.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(jwtService.generateToken(expectedUser.getEmail())).thenReturn("generatedToken");
        when(userRepository.saveUser(any(User.class))).thenReturn(expectedUser);

        User registeredUser = underTest.signUp(expectedUser);

        assertNotNull(registeredUser.getId());
        assertEquals(expectedUser.getEmail(), registeredUser.getEmail());
        assertEquals("encodedPassword", registeredUser.getPassword());
        assertTrue(registeredUser.isActive());
        assertNotNull(registeredUser.getToken());
        verify(userRepository).findUserByEmail(expectedUser.getEmail());
        verify(passwordEncoder).encode(password);
        verify(jwtService).generateToken(expectedUser.getEmail());
        verify(userRepository).saveUser(any(User.class));
    }

    @Test
    void signUp_shouldThrowUserAlreadyExistsException() {
        User expectedUser = User.builder().email("julio@testssw.cl").build();
        when(userRepository.findUserByEmail(expectedUser.getEmail())).thenReturn(Optional.of(expectedUser));

        assertThrows(UserAlreadyExistsException.class, () -> underTest.signUp(expectedUser));

        verify(userRepository).findUserByEmail(expectedUser.getEmail());
        verifyNoMoreInteractions(passwordEncoder, jwtService, userRepository);
    }

    @Test
    void login_shouldLoginUser() {
        String validToken = "validToken";
        String userEmail = "julio@testssw.cl";
        User expectedUser = User.builder()
                .email(userEmail)
                .build();

        when(jwtService.validateToken(validToken)).thenReturn(true);
        when(jwtService.extractUsername(validToken)).thenReturn(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.of(expectedUser));
        when(jwtService.generateToken(userEmail)).thenReturn("newToken");

        User loggedInUser = underTest.login(validToken);

        assertNotNull(loggedInUser.getLastLogin());
        assertEquals("newToken", loggedInUser.getToken());
        verify(jwtService).validateToken(validToken);
        verify(jwtService).extractUsername(validToken);
        verify(userRepository).findUserByEmail(userEmail);
        verify(jwtService).generateToken(userEmail);
    }

    @Test
    void login_shouldThrowJwtSignatureException() {
        String invalidToken = "invalidToken";

        when(jwtService.validateToken(invalidToken)).thenReturn(false);

        assertThrows(JwtSignatureException.class, () -> underTest.login(invalidToken));
        verify(jwtService).validateToken(invalidToken);
        verifyNoMoreInteractions(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void login_shouldThrowUserNotFoundException() {
        String validToken = "validToken";
        String userEmail = "nonexistent@example.com";

        when(jwtService.validateToken(validToken)).thenReturn(true);
        when(jwtService.extractUsername(validToken)).thenReturn(userEmail);
        when(userRepository.findUserByEmail(userEmail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> underTest.login(validToken));
        verify(jwtService).validateToken(validToken);
        verify(jwtService).extractUsername(validToken);
        verify(userRepository).findUserByEmail(userEmail);
        verifyNoMoreInteractions(jwtService, userRepository);
    }

}