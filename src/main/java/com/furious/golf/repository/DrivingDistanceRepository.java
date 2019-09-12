package com.furious.golf.repository;

import com.furious.golf.domain.DrivingDistance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DrivingDistance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DrivingDistanceRepository extends JpaRepository<DrivingDistance, Long> {

}
