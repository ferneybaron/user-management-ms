package com.fbaron.core.model.phone;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Data
@Builder
public class Phone {

    private long id;
    private long number;
    private int cityCode;
    private String countryCode;

}
