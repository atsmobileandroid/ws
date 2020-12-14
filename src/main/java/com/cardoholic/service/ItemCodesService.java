package com.cardoholic.service;

import com.cardoholic.service.dto.ItemCodesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cardoholic.domain.ItemCodes}.
 */
public interface ItemCodesService {

    /**
     * Save a itemCodes.
     *
     * @param itemCodesDTO the entity to save.
     * @return the persisted entity.
     */
    ItemCodesDTO save(ItemCodesDTO itemCodesDTO);

    /**
     * Get all the itemCodes.
     *
     * @return the list of entities.
     */
    List<ItemCodesDTO> findAll();


    /**
     * Get the "id" itemCodes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemCodesDTO> findOne(Long id);

    /**
     * Delete the "id" itemCodes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
