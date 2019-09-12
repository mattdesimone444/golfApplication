package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SGPuttingSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SGPuttingSearchRepositoryMockConfiguration {

    @MockBean
    private SGPuttingSearchRepository mockSGPuttingSearchRepository;

}
