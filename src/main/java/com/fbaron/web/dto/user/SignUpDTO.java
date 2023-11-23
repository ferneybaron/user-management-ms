package com.fbaron.web.dto.user;

import com.fbaron.core.util.PasswordFormat;
import com.fbaron.web.dto.phone.PhoneDTO;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

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
public class SignUpDTO {

    private String name;

    @NotNull(message = "name must no be null")
    @Email(message = "email must be valid", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
    private String email;

    @NotNull(message = "password must no be null")
    @PasswordFormat
    private String password;

    private List<PhoneDTO> phones;

}
