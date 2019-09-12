package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SGApproachSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SGApproachSearchRepositoryMockConfiguration {

    @MockBean
    private SGApproachSearchRepository mockSGApproachSearchRepository;

}
