package com.fbaron.web.advise;

import com.fbaron.core.exception.jwt.JwtSignatureException;
import com.fbaron.core.exception.user.UserAlreadyExistsException;
import com.fbaron.core.exception.user.UserNotFoundException;
import com.fbaron.web.dto.exception.ExceptionDetailDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerAdviceTest {

    @InjectMocks
    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    @Test
    void httpMediaTypeNotSupportedException_shouldReturnResponseEntity() {
        HttpMediaTypeNotSupportedException exception = mock(HttpMediaTypeNotSupportedException.class);

        ResponseEntity<ExceptionDetailDTO> responseEntity = exceptionHandlerAdvice.httpMediaTypeNotSupportedException(exception);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
    }

    @Test
    void httpRequestMethodNotSupportedException_shouldReturnResponseEntity() {
        HttpRequestMethodNotSupportedException exception = mock(HttpRequestMethodNotSupportedException.class);

        ResponseEntity<ExceptionDetailDTO> responseEntity = exceptionHandlerAdvice.httpRequestMethodNotSupportedException(exception);

        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, responseEntity.getStatusCode());
    }

    @Test
    void jwtSignatureException_shouldReturnResponseEntity() {
        JwtSignatureException exception = mock(JwtSignatureException.class);

        ResponseEntity<ExceptionDetailDTO> responseEntity = exceptionHandlerAdvice.jwtSignatureException(exception);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    void userAlreadyExistsException_shouldReturnResponseEntity() {
        UserAlreadyExistsException exception = mock(UserAlreadyExistsException.class);

        ResponseEntity<ExceptionDetailDTO> responseEntity = exceptionHandlerAdvice.userAlreadyExistsException(exception);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    void userNotFoundException_shouldReturnResponseEntity() {
        UserNotFoundException exception = mock(UserNotFoundException.class);

        ResponseEntity<ExceptionDetailDTO> responseEntity = exceptionHandlerAdvice.userNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}