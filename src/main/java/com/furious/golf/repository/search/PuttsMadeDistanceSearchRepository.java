package com.furious.golf.repository.search;

import com.furious.golf.domain.PuttsMadeDistance;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PuttsMadeDistance} entity.
 */
public interface PuttsMadeDistanceSearchRepository extends ElasticsearchRepository<PuttsMadeDistance, Long> {
}
