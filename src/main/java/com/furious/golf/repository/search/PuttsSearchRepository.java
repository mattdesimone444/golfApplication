package com.furious.golf.repository.search;

import com.furious.golf.domain.Putts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Putts} entity.
 */
public interface PuttsSearchRepository extends ElasticsearchRepository<Putts, Long> {
}
