package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SGOffTheTeeSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SGOffTheTeeSearchRepositoryMockConfiguration {

    @MockBean
    private SGOffTheTeeSearchRepository mockSGOffTheTeeSearchRepository;

}
