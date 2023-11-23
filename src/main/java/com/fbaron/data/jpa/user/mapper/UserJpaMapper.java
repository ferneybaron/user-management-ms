package com.fbaron.data.jpa.user.mapper;

import com.fbaron.core.model.user.User;
import com.fbaron.data.jpa.user.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Ferney Estupinan Baron
 * @version 0.0.1
 * @since 11/22/2023
 */
@Mapper(componentModel = "spring")
public interface UserJpaMapper {

    @Mapping(source = "active", target = "isActive")
    User entityToModel(UserEntity entity);

    @Mapping(source = "active", target = "isActive")
    UserEntity modelToEntity(User model);

}
