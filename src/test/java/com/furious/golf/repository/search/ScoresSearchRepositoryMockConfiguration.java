package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ScoresSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ScoresSearchRepositoryMockConfiguration {

    @MockBean
    private ScoresSearchRepository mockScoresSearchRepository;

}
