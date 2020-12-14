package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.BasketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Basket} and its DTO {@link BasketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BasketMapper extends EntityMapper<BasketDTO, Basket> {


    @Mapping(target = "items", ignore = true)
    @Mapping(target = "removeItems", ignore = true)
    Basket toEntity(BasketDTO basketDTO);

    default Basket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Basket basket = new Basket();
        basket.setId(id);
        return basket;
    }
}
