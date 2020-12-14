package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.ItemCodesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ItemCodes} and its DTO {@link ItemCodesDTO}.
 */
@Mapper(componentModel = "spring", uses = {ItemsMapper.class})
public interface ItemCodesMapper extends EntityMapper<ItemCodesDTO, ItemCodes> {

    @Mapping(source = "items.id", target = "itemsId")
    ItemCodesDTO toDto(ItemCodes itemCodes);

    @Mapping(source = "itemsId", target = "items")
    ItemCodes toEntity(ItemCodesDTO itemCodesDTO);

    default ItemCodes fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemCodes itemCodes = new ItemCodes();
        itemCodes.setId(id);
        return itemCodes;
    }
}
