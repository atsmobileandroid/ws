package com.cardoholic.service;

import com.cardoholic.service.dto.LevelsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cardoholic.domain.Levels}.
 */
public interface LevelsService {

    /**
     * Save a levels.
     *
     * @param levelsDTO the entity to save.
     * @return the persisted entity.
     */
    LevelsDTO save(LevelsDTO levelsDTO);

    /**
     * Get all the levels.
     *
     * @return the list of entities.
     */
    List<LevelsDTO> findAll();


    /**
     * Get the "id" levels.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LevelsDTO> findOne(Long id);

    /**
     * Delete the "id" levels.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
