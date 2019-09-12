package com.furious.golf.repository;

import com.furious.golf.domain.PuttingAnalysis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PuttingAnalysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PuttingAnalysisRepository extends JpaRepository<PuttingAnalysis, Long> {

}
