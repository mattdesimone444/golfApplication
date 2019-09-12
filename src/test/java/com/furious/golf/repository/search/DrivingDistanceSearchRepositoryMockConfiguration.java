package com.furious.golf.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link DrivingDistanceSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DrivingDistanceSearchRepositoryMockConfiguration {

    @MockBean
    private DrivingDistanceSearchRepository mockDrivingDistanceSearchRepository;

}
