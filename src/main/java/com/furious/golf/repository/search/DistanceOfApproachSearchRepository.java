package com.furious.golf.repository.search;

import com.furious.golf.domain.DistanceOfApproach;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DistanceOfApproach} entity.
 */
public interface DistanceOfApproachSearchRepository extends ElasticsearchRepository<DistanceOfApproach, Long> {
}
