package com.cardoholic.service;

import com.cardoholic.service.dto.BasketDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cardoholic.domain.Basket}.
 */
public interface BasketService {

    /**
     * Save a basket.
     *
     * @param basketDTO the entity to save.
     * @return the persisted entity.
     */
    BasketDTO save(BasketDTO basketDTO);

    /**
     * Get all the baskets.
     *
     * @return the list of entities.
     */
    List<BasketDTO> findAll();


    /**
     * Get the "id" basket.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BasketDTO> findOne(Long id);

    /**
     * Delete the "id" basket.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
