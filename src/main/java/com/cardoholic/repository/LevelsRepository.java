package com.cardoholic.repository;

import com.cardoholic.domain.Levels;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Levels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelsRepository extends JpaRepository<Levels, Long> {
}
