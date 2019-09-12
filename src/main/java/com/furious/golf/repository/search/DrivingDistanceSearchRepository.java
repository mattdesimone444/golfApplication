package com.furious.golf.repository.search;

import com.furious.golf.domain.DrivingDistance;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DrivingDistance} entity.
 */
public interface DrivingDistanceSearchRepository extends ElasticsearchRepository<DrivingDistance, Long> {
}
