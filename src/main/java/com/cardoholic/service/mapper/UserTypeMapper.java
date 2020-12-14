package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.UserTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserType} and its DTO {@link UserTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserTypeMapper extends EntityMapper<UserTypeDTO, UserType> {



    default UserType fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserType userType = new UserType();
        userType.setId(id);
        return userType;
    }
}
