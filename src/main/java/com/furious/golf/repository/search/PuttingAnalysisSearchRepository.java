package com.furious.golf.repository.search;

import com.furious.golf.domain.PuttingAnalysis;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PuttingAnalysis} entity.
 */
public interface PuttingAnalysisSearchRepository extends ElasticsearchRepository<PuttingAnalysis, Long> {
}
