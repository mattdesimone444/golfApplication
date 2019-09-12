package com.furious.golf.repository.search;

import com.furious.golf.domain.Tournament;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Tournament} entity.
 */
public interface TournamentSearchRepository extends ElasticsearchRepository<Tournament, Long> {
}
