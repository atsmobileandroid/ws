package com.cardoholic.service;

import com.cardoholic.service.dto.ItemPricesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cardoholic.domain.ItemPrices}.
 */
public interface ItemPricesService {

    /**
     * Save a itemPrices.
     *
     * @param itemPricesDTO the entity to save.
     * @return the persisted entity.
     */
    ItemPricesDTO save(ItemPricesDTO itemPricesDTO);

    /**
     * Get all the itemPrices.
     *
     * @return the list of entities.
     */
    List<ItemPricesDTO> findAll();


    /**
     * Get the "id" itemPrices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ItemPricesDTO> findOne(Long id);

    /**
     * Delete the "id" itemPrices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
