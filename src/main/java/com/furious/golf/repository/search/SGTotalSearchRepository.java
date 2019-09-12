package com.furious.golf.repository.search;

import com.furious.golf.domain.SGTotal;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGTotal} entity.
 */
public interface SGTotalSearchRepository extends ElasticsearchRepository<SGTotal, Long> {
}
