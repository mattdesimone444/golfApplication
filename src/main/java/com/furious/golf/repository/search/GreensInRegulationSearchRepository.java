package com.furious.golf.repository.search;

import com.furious.golf.domain.GreensInRegulation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link GreensInRegulation} entity.
 */
public interface GreensInRegulationSearchRepository extends ElasticsearchRepository<GreensInRegulation, Long> {
}
