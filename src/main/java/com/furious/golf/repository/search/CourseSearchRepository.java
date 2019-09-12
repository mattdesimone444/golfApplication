package com.furious.golf.repository.search;

import com.furious.golf.domain.Course;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Course} entity.
 */
public interface CourseSearchRepository extends ElasticsearchRepository<Course, Long> {
}
