package com.fbaron.web.rest;

import com.fbaron.core.service.user.UserService;
import com.fbaron.web.dto.user.SignUpDTO;
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
@RequestMapping("/api/v1/sign-up")
@RequiredArgsConstructor
public class SignUpRestAdapter {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @Operation(summary = "signUp",
            description = "Registers a new user in the system by giving the RequestBody with its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = CREATED,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = BAD_REQUEST,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpDTO dto) {
        log.debug("Received a request for POST /api/v1/sign-up EndPoint with RequestBody: {}", dto);
        var user = userDtoMapper.dtoToModel(dto);
        user = userService.signUp(user);
        var userDTO = userDtoMapper.modelToDTO(user);
        log.debug("Returning response for POST /api/v1/users EndPoint: {}", userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

}
