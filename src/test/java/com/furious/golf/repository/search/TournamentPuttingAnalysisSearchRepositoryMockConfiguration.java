package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TournamentPuttingAnalysisSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TournamentPuttingAnalysisSearchRepositoryMockConfiguration {

    @MockBean
    private TournamentPuttingAnalysisSearchRepository mockTournamentPuttingAnalysisSearchRepository;

}
