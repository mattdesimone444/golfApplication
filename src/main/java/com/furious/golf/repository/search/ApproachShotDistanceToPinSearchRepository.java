package com.furious.golf.repository.search;

import com.furious.golf.domain.ApproachShotDistanceToPin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ApproachShotDistanceToPin} entity.
 */
public interface ApproachShotDistanceToPinSearchRepository extends ElasticsearchRepository<ApproachShotDistanceToPin, Long> {
}
