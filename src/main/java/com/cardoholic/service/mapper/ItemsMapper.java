package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.ItemsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Items} and its DTO {@link ItemsDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemPricesMapper.class, BasketMapper.class})
public interface ItemsMapper extends EntityMapper<ItemsDTO, Items> {

    @Mapping(source = "itemPrices.id", target = "itemPricesId")
    @Mapping(source = "basket.id", target = "basketId")
    ItemsDTO toDto(Items items);

    @Mapping(source = "itemPricesId", target = "itemPrices")
    @Mapping(target = "itemCodes", ignore = true)
    @Mapping(target = "removeItemCodes", ignore = true)
    @Mapping(source = "basketId", target = "basket")
    Items toEntity(ItemsDTO itemsDTO);

    default Items fromId(Long id) {
        if (id == null) {
            return null;
        }
        Items items = new Items();
        items.setId(id);
        return items;
    }
}
