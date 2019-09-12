package com.furious.golf.repository.search;

import com.furious.golf.domain.Scores;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Scores} entity.
 */
public interface ScoresSearchRepository extends ElasticsearchRepository<Scores, Long> {
}
