package com.fbaron.web.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Data
@Builder
public class CustomErrorDTO {

    private LocalDateTime timestamp;
    private int code;
    private String detail;

}
