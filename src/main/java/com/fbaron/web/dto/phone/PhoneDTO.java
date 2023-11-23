package com.fbaron.web.dto.phone;

import lombok.*;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {

    private long number;
    private int cityCode;
    private String countryCode;

}
