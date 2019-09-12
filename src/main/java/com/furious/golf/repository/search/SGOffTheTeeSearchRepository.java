package com.furious.golf.repository.search;

import com.furious.golf.domain.SGOffTheTee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SGOffTheTee} entity.
 */
public interface SGOffTheTeeSearchRepository extends ElasticsearchRepository<SGOffTheTee, Long> {
}
