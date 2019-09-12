package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link DistanceOfApproachSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DistanceOfApproachSearchRepositoryMockConfiguration {

    @MockBean
    private DistanceOfApproachSearchRepository mockDistanceOfApproachSearchRepository;

}
