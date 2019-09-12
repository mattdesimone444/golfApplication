package com.furious.golf.repository;

import com.furious.golf.domain.FairwaysHit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FairwaysHit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FairwaysHitRepository extends JpaRepository<FairwaysHit, Long> {

}
