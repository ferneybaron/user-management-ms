package com.fbaron.web.rest;

import com.fbaron.core.exception.user.UserAlreadyExistsException;
import com.fbaron.core.model.user.User;
import com.fbaron.core.service.user.UserService;
import com.fbaron.web.dto.user.SignUpDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpRestAdapterTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDtoMapper userDtoMapper;

    @InjectMocks
    private SignUpRestAdapter underTest;

    @Test
    void signUp_shouldReturnCreatedResponse() {
        SignUpDTO signUpDTO = new SignUpDTO();
        UserDTO expectedUserDTO = new UserDTO();
        User user = User.builder()
                .id(UUID.randomUUID())
                .email("julio@testssw.cl")
                .build();

        when(userDtoMapper.dtoToModel(signUpDTO)).thenReturn(user);
        when(userService.signUp(any(User.class))).thenReturn(user);
        when(userDtoMapper.modelToDTO(any(User.class))).thenReturn(expectedUserDTO);

        ResponseEntity<UserDTO> responseEntity = underTest.signUp(signUpDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedUserDTO, responseEntity.getBody());

        verify(userDtoMapper, times(1)).dtoToModel(signUpDTO);
        verify(userService, times(1)).signUp(any(User.class));
        verify(userDtoMapper, times(1)).modelToDTO(any(User.class));
    }

    @Test
    void signUp_shouldReturnBadRequestResponseOnUserAlreadyExistsException() {
        SignUpDTO signUpDTO = new SignUpDTO();
        User user = User.builder()
                .id(UUID.randomUUID())
                .email("julio@testssw.cl")
                .build();
        when(userDtoMapper.dtoToModel(signUpDTO)).thenReturn(user);
        when(userService.signUp(any(User.class))).thenThrow(UserAlreadyExistsException.class);

        assertThrows(UserAlreadyExistsException.class, () -> underTest.signUp(signUpDTO));

        verify(userDtoMapper, times(1)).dtoToModel(signUpDTO);
        verify(userService, times(1)).signUp(any(User.class));
        verify(userDtoMapper, never()).modelToDTO(any(User.class));
    }

}