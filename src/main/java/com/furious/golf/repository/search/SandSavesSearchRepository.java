package com.furious.golf.repository.search;

import com.furious.golf.domain.SandSaves;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SandSaves} entity.
 */
public interface SandSavesSearchRepository extends ElasticsearchRepository<SandSaves, Long> {
}
