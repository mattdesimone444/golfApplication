package com.furious.golf.repository;

import com.furious.golf.domain.ApproachShotDistanceToPin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApproachShotDistanceToPin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApproachShotDistanceToPinRepository extends JpaRepository<ApproachShotDistanceToPin, Long> {

}
