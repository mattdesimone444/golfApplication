package com.furious.golf.repository.search;

import com.furious.golf.domain.FairwaysHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FairwaysHit} entity.
 */
public interface FairwaysHitSearchRepository extends ElasticsearchRepository<FairwaysHit, Long> {
}
