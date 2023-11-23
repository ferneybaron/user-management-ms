package com.fbaron.web.advise;

import com.fbaron.core.exception.jwt.JwtSignatureException;
import com.fbaron.core.exception.user.UserAlreadyExistsException;
import com.fbaron.core.exception.user.UserNotFoundException;
import com.fbaron.web.dto.exception.CustomErrorDTO;
import com.fbaron.web.dto.exception.ExceptionDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<ExceptionDetailDTO> httpMediaTypeNotSupportedException(Exception exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        var error = CustomErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .detail(exception.getMessage())
                .build();
        errors.add(error);
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ExceptionDetailDTO> httpRequestMethodNotSupportedException(Exception exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        var error = CustomErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                .detail(exception.getMessage())
                .build();
        errors.add(error);
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDetailDTO> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(e -> errors
                .add(CustomErrorDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .detail(e.getDefaultMessage())
                        .build()));
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtSignatureException.class})
    public ResponseEntity<ExceptionDetailDTO> jwtSignatureException(JwtSignatureException exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        var error = CustomErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.FORBIDDEN.value())
                .detail(exception.getMessage())
                .build();
        errors.add(error);
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<ExceptionDetailDTO> userAlreadyExistsException(Exception exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        var error = CustomErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.CONFLICT.value())
                .detail(exception.getMessage())
                .build();
        errors.add(error);
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ExceptionDetailDTO> userNotFoundException(Exception exception) {
        log.error(exception.getMessage());
        List<CustomErrorDTO> errors = new ArrayList<>();
        var error = CustomErrorDTO.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .detail(exception.getMessage())
                .build();
        errors.add(error);
        return new ResponseEntity<>(ExceptionDetailDTO.builder()
                .error(errors)
                .build(), HttpStatus.NOT_FOUND);
    }

}
