package com.furious.golf.repository.search;

import com.furious.golf.domain.SGArroundTheGreen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGArroundTheGreen} entity.
 */
public interface SGArroundTheGreenSearchRepository extends ElasticsearchRepository<SGArroundTheGreen, Long> {
}
