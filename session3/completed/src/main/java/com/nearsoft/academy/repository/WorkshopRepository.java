package com.nearsoft.academy.repository;

import com.nearsoft.academy.domain.Workshop;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Workshop entity.
 */
public interface WorkshopRepository extends JpaRepository<Workshop,Long> {
    List<Workshop> findByTitle(String title);
}
