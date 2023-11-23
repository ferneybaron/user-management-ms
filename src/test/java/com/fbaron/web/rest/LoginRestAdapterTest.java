package com.fbaron.web.rest;

import com.fbaron.core.exception.jwt.JwtSignatureException;
import com.fbaron.core.exception.user.UserNotFoundException;
import com.fbaron.core.model.user.User;
import com.fbaron.core.service.user.UserService;
import com.fbaron.web.dto.user.LoginDTO;
import com.fbaron.web.dto.user.UserDTO;
import com.fbaron.web.mapper.UserDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginRestAdapterTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDtoMapper userDtoMapper;

    @InjectMocks
    private LoginRestAdapter underTest;

    @Test
    void login_shouldReturnOkResponse() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken("token");
        var uuid = UUID.randomUUID();
        var email = "julio@testssw.cl";

        UserDTO expectedUserDTO = new UserDTO();
        expectedUserDTO.setId(uuid);
        expectedUserDTO.setEmail(email);

        User user = User.builder()
                .id(uuid)
                .email(email)
                .build();

        when(userService.login(anyString())).thenReturn(user);
        when(userDtoMapper.modelToDTO(any())).thenReturn(expectedUserDTO);

        ResponseEntity<UserDTO> responseEntity = underTest.login(loginDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUserDTO, responseEntity.getBody());

        verify(userService, times(1)).login(anyString());
        verify(userDtoMapper, times(1)).modelToDTO(any());
    }

    @Test
    void login_shouldReturnBadRequestResponseOnJwtSignatureException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken("token");

        when(userService.login(anyString())).thenThrow(JwtSignatureException.class);

        assertThrows(JwtSignatureException.class, () -> underTest.login(loginDTO));

        verify(userService, times(1)).login(anyString());
        verify(userDtoMapper, never()).modelToDTO(any());
    }

    @Test
    void login_shouldReturnNotFoundResponseOnUserNotFoundException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken("token");

        when(userService.login(anyString())).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> underTest.login(loginDTO));

        verify(userService, times(1)).login(anyString());
        verify(userDtoMapper, never()).modelToDTO(any());
    }

}