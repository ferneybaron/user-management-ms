package com.fbaron.web.mapper;

import com.fbaron.core.model.user.User;
import com.fbaron.web.dto.user.SignUpDTO;
import com.fbaron.web.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    User dtoToModel(SignUpDTO dto);

    UserDTO modelToDTO(User model);

}
