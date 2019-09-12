package com.furious.golf.repository.search;

import com.furious.golf.domain.SGTeeToGreen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGTeeToGreen} entity.
 */
public interface SGTeeToGreenSearchRepository extends ElasticsearchRepository<SGTeeToGreen, Long> {
}
