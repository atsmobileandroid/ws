package com.cardoholic.repository;

import com.cardoholic.domain.ItemCodes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ItemCodes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemCodesRepository extends JpaRepository<ItemCodes, Long> {
}
