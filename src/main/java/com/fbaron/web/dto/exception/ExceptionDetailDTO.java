package com.fbaron.web.dto.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Data
@Builder
public class ExceptionDetailDTO {

    private List<CustomErrorDTO> error;

}
