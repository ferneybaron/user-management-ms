package com.fbaron.web.dto.user;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoginDTO {

    @NotNull(message = "name must no be null")
    private String token;

}
