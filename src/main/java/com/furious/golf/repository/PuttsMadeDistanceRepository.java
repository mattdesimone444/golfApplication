package com.furious.golf.repository;

import com.furious.golf.domain.PuttsMadeDistance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PuttsMadeDistance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuttsMadeDistanceRepository extends JpaRepository<PuttsMadeDistance, Long> {

}
