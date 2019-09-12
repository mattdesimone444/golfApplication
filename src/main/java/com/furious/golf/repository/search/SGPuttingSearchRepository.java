package com.furious.golf.repository.search;

import com.furious.golf.domain.SGPutting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGPutting} entity.
 */
public interface SGPuttingSearchRepository extends ElasticsearchRepository<SGPutting, Long> {
}
