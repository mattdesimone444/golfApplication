package com.furious.golf.repository;

import com.furious.golf.domain.TournamentPuttingAnalysis;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TournamentPuttingAnalysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TournamentPuttingAnalysisRepository extends JpaRepository<TournamentPuttingAnalysis, Long> {

}
