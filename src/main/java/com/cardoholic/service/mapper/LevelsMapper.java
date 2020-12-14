package com.cardoholic.service.mapper;


import com.cardoholic.domain.*;
import com.cardoholic.service.dto.LevelsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Levels} and its DTO {@link LevelsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LevelsMapper extends EntityMapper<LevelsDTO, Levels> {



    default Levels fromId(Long id) {
        if (id == null) {
            return null;
        }
        Levels levels = new Levels();
        levels.setId(id);
        return levels;
    }
}
