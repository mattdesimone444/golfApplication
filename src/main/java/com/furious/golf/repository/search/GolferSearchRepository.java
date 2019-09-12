package com.furious.golf.repository.search;

import com.furious.golf.domain.Golfer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Golfer} entity.
 */
public interface GolferSearchRepository extends ElasticsearchRepository<Golfer, Long> {
}
