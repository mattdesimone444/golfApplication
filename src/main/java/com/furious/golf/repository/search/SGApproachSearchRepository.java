package com.furious.golf.repository.search;

import com.furious.golf.domain.SGApproach;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGApproach} entity.
 */
public interface SGApproachSearchRepository extends ElasticsearchRepository<SGApproach, Long> {
}
