package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.UsersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Users} and its DTO {@link UsersDTO}.
 */
@Mapper(componentModel = "spring", uses = {BasketMapper.class, CountriesMapper.class, UserTypeMapper.class})
public interface UsersMapper extends EntityMapper<UsersDTO, Users> {

    @Mapping(source = "basket.id", target = "basketId")
    @Mapping(source = "countries.id", target = "countriesId")
    @Mapping(source = "userType.id", target = "userTypeId")
    UsersDTO toDto(Users users);

    @Mapping(source = "basketId", target = "basket")
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "removeOrders", ignore = true)
    @Mapping(source = "countriesId", target = "countries")
    @Mapping(source = "userTypeId", target = "userType")
    Users toEntity(UsersDTO usersDTO);

    default Users fromId(Long id) {
        if (id == null) {
            return null;
        }
        Users users = new Users();
        users.setId(id);
        return users;
    }
}
