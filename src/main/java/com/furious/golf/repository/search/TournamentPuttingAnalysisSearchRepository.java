package com.furious.golf.repository.search;

import com.furious.golf.domain.TournamentPuttingAnalysis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TournamentPuttingAnalysis} entity.
 */
public interface TournamentPuttingAnalysisSearchRepository extends ElasticsearchRepository<TournamentPuttingAnalysis, Long> {
}
