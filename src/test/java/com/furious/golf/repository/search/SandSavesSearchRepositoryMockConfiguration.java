package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SandSavesSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SandSavesSearchRepositoryMockConfiguration {

    @MockBean
    private SandSavesSearchRepository mockSandSavesSearchRepository;

}
