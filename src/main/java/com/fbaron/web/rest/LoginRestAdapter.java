package com.fbaron.web.rest;

import com.fbaron.core.service.user.UserService;
import com.fbaron.web.dto.exception.ExceptionDetailDTO;
import com.fbaron.web.dto.user.LoginDTO;
import com.fbaron.web.dto.user.UserDTO;
import com.fbaron.web.mapper.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.fbaron.core.constant.StatusConstant.*;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginRestAdapter {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Operation(summary = "login",
            description = "Logs in a user to the system by giving the RequestBody with its token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SUCCESS,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
            @ApiResponse(responseCode = "404", description = NOT_FOUND,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
            @ApiResponse(responseCode = "403", description = FORBIDDEN,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
            @ApiResponse(responseCode = "405", description = METHOD_NOT_ALLOWED,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
            @ApiResponse(responseCode = "415", description = UNSUPPORTED_MEDIA_TYPE,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExceptionDetailDTO.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> login(@RequestBody @Valid LoginDTO dto) {
        log.debug("Received a request for POST /api/v1/login EndPoint with RequestBody: {}", dto);
        var user = userService.login(dto.getToken());
        var userDTO = userDtoMapper.modelToDTO(user);
        log.debug("Returning response for POST /api/v1/login EndPoint: {}", userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

}
