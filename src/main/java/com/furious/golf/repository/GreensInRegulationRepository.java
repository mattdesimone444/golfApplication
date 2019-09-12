package com.furious.golf.repository;

import com.furious.golf.domain.GreensInRegulation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GreensInRegulation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GreensInRegulationRepository extends JpaRepository<GreensInRegulation, Long> {

}
