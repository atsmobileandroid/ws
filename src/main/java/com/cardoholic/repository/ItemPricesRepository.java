package com.cardoholic.repository;

import com.cardoholic.domain.ItemPrices;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemPrices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPricesRepository extends JpaRepository<ItemPrices, Long> {
}
