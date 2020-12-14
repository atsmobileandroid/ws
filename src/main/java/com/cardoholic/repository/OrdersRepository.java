package com.cardoholic.repository;

import com.cardoholic.domain.Orders;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Orders entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}