package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.Course;
import com.furious.golf.repository.CourseRepository;
import com.furious.golf.repository.search.CourseSearchRepository;
import com.furious.golf.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static com.furious.golf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CourseResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class CourseResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAR = 1;
    private static final Integer UPDATED_PAR = 2;
    private static final Integer SMALLER_PAR = 1 - 1;

    private static final Integer DEFAULT_YARDAGE = 1;
    private static final Integer UPDATED_YARDAGE = 2;
    private static final Integer SMALLER_YARDAGE = 1 - 1;

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_COURSE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COURSE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_GREEN_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_GREEN_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOLE_1_PAR = 1;
    private static final Integer UPDATED_HOLE_1_PAR = 2;
    private static final Integer SMALLER_HOLE_1_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_1_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_1_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_1_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_1_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_1_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_1_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_2_PAR = 1;
    private static final Integer UPDATED_HOLE_2_PAR = 2;
    private static final Integer SMALLER_HOLE_2_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_2_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_2_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_2_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_2_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_2_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_2_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_3_PAR = 1;
    private static final Integer UPDATED_HOLE_3_PAR = 2;
    private static final Integer SMALLER_HOLE_3_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_3_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_3_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_3_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_3_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_3_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_3_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_4_PAR = 1;
    private static final Integer UPDATED_HOLE_4_PAR = 2;
    private static final Integer SMALLER_HOLE_4_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_4_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_4_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_4_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_4_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_4_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_4_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_5_PAR = 1;
    private static final Integer UPDATED_HOLE_5_PAR = 2;
    private static final Integer SMALLER_HOLE_5_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_5_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_5_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_5_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_5_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_5_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_5_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_6_PAR = 1;
    private static final Integer UPDATED_HOLE_6_PAR = 2;
    private static final Integer SMALLER_HOLE_6_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_6_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_6_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_6_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_6_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_6_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_6_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_7_PAR = 1;
    private static final Integer UPDATED_HOLE_7_PAR = 2;
    private static final Integer SMALLER_HOLE_7_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_7_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_7_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_7_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_7_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_7_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_7_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_8_PAR = 1;
    private static final Integer UPDATED_HOLE_8_PAR = 2;
    private static final Integer SMALLER_HOLE_8_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_8_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_8_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_8_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_8_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_8_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_8_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_9_PAR = 1;
    private static final Integer UPDATED_HOLE_9_PAR = 2;
    private static final Integer SMALLER_HOLE_9_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_9_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_9_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_9_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_9_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_9_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_9_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_10_PAR = 1;
    private static final Integer UPDATED_HOLE_10_PAR = 2;
    private static final Integer SMALLER_HOLE_10_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_10_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_10_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_10_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_10_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_10_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_10_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_11_PAR = 1;
    private static final Integer UPDATED_HOLE_11_PAR = 2;
    private static final Integer SMALLER_HOLE_11_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_11_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_11_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_11_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_11_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_11_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_11_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_12_PAR = 1;
    private static final Integer UPDATED_HOLE_12_PAR = 2;
    private static final Integer SMALLER_HOLE_12_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_12_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_12_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_12_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_12_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_12_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_12_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_13_PAR = 1;
    private static final Integer UPDATED_HOLE_13_PAR = 2;
    private static final Integer SMALLER_HOLE_13_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_13_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_13_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_13_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_13_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_13_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_13_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_14_PAR = 1;
    private static final Integer UPDATED_HOLE_14_PAR = 2;
    private static final Integer SMALLER_HOLE_14_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_14_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_14_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_14_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_14_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_14_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_14_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_15_PAR = 1;
    private static final Integer UPDATED_HOLE_15_PAR = 2;
    private static final Integer SMALLER_HOLE_15_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_15_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_15_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_15_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_15_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_15_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_15_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_16_PAR = 1;
    private static final Integer UPDATED_HOLE_16_PAR = 2;
    private static final Integer SMALLER_HOLE_16_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_16_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_16_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_16_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_16_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_16_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_16_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_17_PAR = 1;
    private static final Integer UPDATED_HOLE_17_PAR = 2;
    private static final Integer SMALLER_HOLE_17_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_17_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_17_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_17_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_17_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_17_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_17_HANDICAP = 1 - 1;

    private static final Integer DEFAULT_HOLE_18_PAR = 1;
    private static final Integer UPDATED_HOLE_18_PAR = 2;
    private static final Integer SMALLER_HOLE_18_PAR = 1 - 1;

    private static final Integer DEFAULT_HOLE_18_YARDAGE = 1;
    private static final Integer UPDATED_HOLE_18_YARDAGE = 2;
    private static final Integer SMALLER_HOLE_18_YARDAGE = 1 - 1;

    private static final Integer DEFAULT_HOLE_18_HANDICAP = 1;
    private static final Integer UPDATED_HOLE_18_HANDICAP = 2;
    private static final Integer SMALLER_HOLE_18_HANDICAP = 1 - 1;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.CourseSearchRepositoryMockConfiguration
     */
    @Autowired
    private CourseSearchRepository mockCourseSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCourseMockMvc;

    private Course course;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourseResource courseResource = new CourseResource(courseRepository, mockCourseSearchRepository);
        this.restCourseMockMvc = MockMvcBuilders.standaloneSetup(courseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createEntity(EntityManager em) {
        Course course = new Course()
            .name(DEFAULT_NAME)
            .par(DEFAULT_PAR)
            .yardage(DEFAULT_YARDAGE)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .courseType(DEFAULT_COURSE_TYPE)
            .greenType(DEFAULT_GREEN_TYPE)
            .hole1Par(DEFAULT_HOLE_1_PAR)
            .hole1Yardage(DEFAULT_HOLE_1_YARDAGE)
            .hole1Handicap(DEFAULT_HOLE_1_HANDICAP)
            .hole2Par(DEFAULT_HOLE_2_PAR)
            .hole2Yardage(DEFAULT_HOLE_2_YARDAGE)
            .hole2Handicap(DEFAULT_HOLE_2_HANDICAP)
            .hole3Par(DEFAULT_HOLE_3_PAR)
            .hole3Yardage(DEFAULT_HOLE_3_YARDAGE)
            .hole3Handicap(DEFAULT_HOLE_3_HANDICAP)
            .hole4Par(DEFAULT_HOLE_4_PAR)
            .hole4Yardage(DEFAULT_HOLE_4_YARDAGE)
            .hole4Handicap(DEFAULT_HOLE_4_HANDICAP)
            .hole5Par(DEFAULT_HOLE_5_PAR)
            .hole5Yardage(DEFAULT_HOLE_5_YARDAGE)
            .hole5Handicap(DEFAULT_HOLE_5_HANDICAP)
            .hole6Par(DEFAULT_HOLE_6_PAR)
            .hole6Yardage(DEFAULT_HOLE_6_YARDAGE)
            .hole6Handicap(DEFAULT_HOLE_6_HANDICAP)
            .hole7Par(DEFAULT_HOLE_7_PAR)
            .hole7Yardage(DEFAULT_HOLE_7_YARDAGE)
            .hole7Handicap(DEFAULT_HOLE_7_HANDICAP)
            .hole8Par(DEFAULT_HOLE_8_PAR)
            .hole8Yardage(DEFAULT_HOLE_8_YARDAGE)
            .hole8Handicap(DEFAULT_HOLE_8_HANDICAP)
            .hole9Par(DEFAULT_HOLE_9_PAR)
            .hole9Yardage(DEFAULT_HOLE_9_YARDAGE)
            .hole9Handicap(DEFAULT_HOLE_9_HANDICAP)
            .hole10Par(DEFAULT_HOLE_10_PAR)
            .hole10Yardage(DEFAULT_HOLE_10_YARDAGE)
            .hole10Handicap(DEFAULT_HOLE_10_HANDICAP)
            .hole11Par(DEFAULT_HOLE_11_PAR)
            .hole11Yardage(DEFAULT_HOLE_11_YARDAGE)
            .hole11Handicap(DEFAULT_HOLE_11_HANDICAP)
            .hole12Par(DEFAULT_HOLE_12_PAR)
            .hole12Yardage(DEFAULT_HOLE_12_YARDAGE)
            .hole12Handicap(DEFAULT_HOLE_12_HANDICAP)
            .hole13Par(DEFAULT_HOLE_13_PAR)
            .hole13Yardage(DEFAULT_HOLE_13_YARDAGE)
            .hole13Handicap(DEFAULT_HOLE_13_HANDICAP)
            .hole14Par(DEFAULT_HOLE_14_PAR)
            .hole14Yardage(DEFAULT_HOLE_14_YARDAGE)
            .hole14Handicap(DEFAULT_HOLE_14_HANDICAP)
            .hole15Par(DEFAULT_HOLE_15_PAR)
            .hole15Yardage(DEFAULT_HOLE_15_YARDAGE)
            .hole15Handicap(DEFAULT_HOLE_15_HANDICAP)
            .hole16Par(DEFAULT_HOLE_16_PAR)
            .hole16Yardage(DEFAULT_HOLE_16_YARDAGE)
            .hole16Handicap(DEFAULT_HOLE_16_HANDICAP)
            .hole17Par(DEFAULT_HOLE_17_PAR)
            .hole17Yardage(DEFAULT_HOLE_17_YARDAGE)
            .hole17Handicap(DEFAULT_HOLE_17_HANDICAP)
            .hole18Par(DEFAULT_HOLE_18_PAR)
            .hole18Yardage(DEFAULT_HOLE_18_YARDAGE)
            .hole18Handicap(DEFAULT_HOLE_18_HANDICAP);
        return course;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Course createUpdatedEntity(EntityManager em) {
        Course course = new Course()
            .name(UPDATED_NAME)
            .par(UPDATED_PAR)
            .yardage(UPDATED_YARDAGE)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .courseType(UPDATED_COURSE_TYPE)
            .greenType(UPDATED_GREEN_TYPE)
            .hole1Par(UPDATED_HOLE_1_PAR)
            .hole1Yardage(UPDATED_HOLE_1_YARDAGE)
            .hole1Handicap(UPDATED_HOLE_1_HANDICAP)
            .hole2Par(UPDATED_HOLE_2_PAR)
            .hole2Yardage(UPDATED_HOLE_2_YARDAGE)
            .hole2Handicap(UPDATED_HOLE_2_HANDICAP)
            .hole3Par(UPDATED_HOLE_3_PAR)
            .hole3Yardage(UPDATED_HOLE_3_YARDAGE)
            .hole3Handicap(UPDATED_HOLE_3_HANDICAP)
            .hole4Par(UPDATED_HOLE_4_PAR)
            .hole4Yardage(UPDATED_HOLE_4_YARDAGE)
            .hole4Handicap(UPDATED_HOLE_4_HANDICAP)
            .hole5Par(UPDATED_HOLE_5_PAR)
            .hole5Yardage(UPDATED_HOLE_5_YARDAGE)
            .hole5Handicap(UPDATED_HOLE_5_HANDICAP)
            .hole6Par(UPDATED_HOLE_6_PAR)
            .hole6Yardage(UPDATED_HOLE_6_YARDAGE)
            .hole6Handicap(UPDATED_HOLE_6_HANDICAP)
            .hole7Par(UPDATED_HOLE_7_PAR)
            .hole7Yardage(UPDATED_HOLE_7_YARDAGE)
            .hole7Handicap(UPDATED_HOLE_7_HANDICAP)
            .hole8Par(UPDATED_HOLE_8_PAR)
            .hole8Yardage(UPDATED_HOLE_8_YARDAGE)
            .hole8Handicap(UPDATED_HOLE_8_HANDICAP)
            .hole9Par(UPDATED_HOLE_9_PAR)
            .hole9Yardage(UPDATED_HOLE_9_YARDAGE)
            .hole9Handicap(UPDATED_HOLE_9_HANDICAP)
            .hole10Par(UPDATED_HOLE_10_PAR)
            .hole10Yardage(UPDATED_HOLE_10_YARDAGE)
            .hole10Handicap(UPDATED_HOLE_10_HANDICAP)
            .hole11Par(UPDATED_HOLE_11_PAR)
            .hole11Yardage(UPDATED_HOLE_11_YARDAGE)
            .hole11Handicap(UPDATED_HOLE_11_HANDICAP)
            .hole12Par(UPDATED_HOLE_12_PAR)
            .hole12Yardage(UPDATED_HOLE_12_YARDAGE)
            .hole12Handicap(UPDATED_HOLE_12_HANDICAP)
            .hole13Par(UPDATED_HOLE_13_PAR)
            .hole13Yardage(UPDATED_HOLE_13_YARDAGE)
            .hole13Handicap(UPDATED_HOLE_13_HANDICAP)
            .hole14Par(UPDATED_HOLE_14_PAR)
            .hole14Yardage(UPDATED_HOLE_14_YARDAGE)
            .hole14Handicap(UPDATED_HOLE_14_HANDICAP)
            .hole15Par(UPDATED_HOLE_15_PAR)
            .hole15Yardage(UPDATED_HOLE_15_YARDAGE)
            .hole15Handicap(UPDATED_HOLE_15_HANDICAP)
            .hole16Par(UPDATED_HOLE_16_PAR)
            .hole16Yardage(UPDATED_HOLE_16_YARDAGE)
            .hole16Handicap(UPDATED_HOLE_16_HANDICAP)
            .hole17Par(UPDATED_HOLE_17_PAR)
            .hole17Yardage(UPDATED_HOLE_17_YARDAGE)
            .hole17Handicap(UPDATED_HOLE_17_HANDICAP)
            .hole18Par(UPDATED_HOLE_18_PAR)
            .hole18Yardage(UPDATED_HOLE_18_YARDAGE)
            .hole18Handicap(UPDATED_HOLE_18_HANDICAP);
        return course;
    }

    @BeforeEach
    public void initTest() {
        course = createEntity(em);
    }

    @Test
    @Transactional
    public void createCourse() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isCreated());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate + 1);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCourse.getPar()).isEqualTo(DEFAULT_PAR);
        assertThat(testCourse.getYardage()).isEqualTo(DEFAULT_YARDAGE);
        assertThat(testCourse.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCourse.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testCourse.getCourseType()).isEqualTo(DEFAULT_COURSE_TYPE);
        assertThat(testCourse.getGreenType()).isEqualTo(DEFAULT_GREEN_TYPE);
        assertThat(testCourse.getHole1Par()).isEqualTo(DEFAULT_HOLE_1_PAR);
        assertThat(testCourse.getHole1Yardage()).isEqualTo(DEFAULT_HOLE_1_YARDAGE);
        assertThat(testCourse.getHole1Handicap()).isEqualTo(DEFAULT_HOLE_1_HANDICAP);
        assertThat(testCourse.getHole2Par()).isEqualTo(DEFAULT_HOLE_2_PAR);
        assertThat(testCourse.getHole2Yardage()).isEqualTo(DEFAULT_HOLE_2_YARDAGE);
        assertThat(testCourse.getHole2Handicap()).isEqualTo(DEFAULT_HOLE_2_HANDICAP);
        assertThat(testCourse.getHole3Par()).isEqualTo(DEFAULT_HOLE_3_PAR);
        assertThat(testCourse.getHole3Yardage()).isEqualTo(DEFAULT_HOLE_3_YARDAGE);
        assertThat(testCourse.getHole3Handicap()).isEqualTo(DEFAULT_HOLE_3_HANDICAP);
        assertThat(testCourse.getHole4Par()).isEqualTo(DEFAULT_HOLE_4_PAR);
        assertThat(testCourse.getHole4Yardage()).isEqualTo(DEFAULT_HOLE_4_YARDAGE);
        assertThat(testCourse.getHole4Handicap()).isEqualTo(DEFAULT_HOLE_4_HANDICAP);
        assertThat(testCourse.getHole5Par()).isEqualTo(DEFAULT_HOLE_5_PAR);
        assertThat(testCourse.getHole5Yardage()).isEqualTo(DEFAULT_HOLE_5_YARDAGE);
        assertThat(testCourse.getHole5Handicap()).isEqualTo(DEFAULT_HOLE_5_HANDICAP);
        assertThat(testCourse.getHole6Par()).isEqualTo(DEFAULT_HOLE_6_PAR);
        assertThat(testCourse.getHole6Yardage()).isEqualTo(DEFAULT_HOLE_6_YARDAGE);
        assertThat(testCourse.getHole6Handicap()).isEqualTo(DEFAULT_HOLE_6_HANDICAP);
        assertThat(testCourse.getHole7Par()).isEqualTo(DEFAULT_HOLE_7_PAR);
        assertThat(testCourse.getHole7Yardage()).isEqualTo(DEFAULT_HOLE_7_YARDAGE);
        assertThat(testCourse.getHole7Handicap()).isEqualTo(DEFAULT_HOLE_7_HANDICAP);
        assertThat(testCourse.getHole8Par()).isEqualTo(DEFAULT_HOLE_8_PAR);
        assertThat(testCourse.getHole8Yardage()).isEqualTo(DEFAULT_HOLE_8_YARDAGE);
        assertThat(testCourse.getHole8Handicap()).isEqualTo(DEFAULT_HOLE_8_HANDICAP);
        assertThat(testCourse.getHole9Par()).isEqualTo(DEFAULT_HOLE_9_PAR);
        assertThat(testCourse.getHole9Yardage()).isEqualTo(DEFAULT_HOLE_9_YARDAGE);
        assertThat(testCourse.getHole9Handicap()).isEqualTo(DEFAULT_HOLE_9_HANDICAP);
        assertThat(testCourse.getHole10Par()).isEqualTo(DEFAULT_HOLE_10_PAR);
        assertThat(testCourse.getHole10Yardage()).isEqualTo(DEFAULT_HOLE_10_YARDAGE);
        assertThat(testCourse.getHole10Handicap()).isEqualTo(DEFAULT_HOLE_10_HANDICAP);
        assertThat(testCourse.getHole11Par()).isEqualTo(DEFAULT_HOLE_11_PAR);
        assertThat(testCourse.getHole11Yardage()).isEqualTo(DEFAULT_HOLE_11_YARDAGE);
        assertThat(testCourse.getHole11Handicap()).isEqualTo(DEFAULT_HOLE_11_HANDICAP);
        assertThat(testCourse.getHole12Par()).isEqualTo(DEFAULT_HOLE_12_PAR);
        assertThat(testCourse.getHole12Yardage()).isEqualTo(DEFAULT_HOLE_12_YARDAGE);
        assertThat(testCourse.getHole12Handicap()).isEqualTo(DEFAULT_HOLE_12_HANDICAP);
        assertThat(testCourse.getHole13Par()).isEqualTo(DEFAULT_HOLE_13_PAR);
        assertThat(testCourse.getHole13Yardage()).isEqualTo(DEFAULT_HOLE_13_YARDAGE);
        assertThat(testCourse.getHole13Handicap()).isEqualTo(DEFAULT_HOLE_13_HANDICAP);
        assertThat(testCourse.getHole14Par()).isEqualTo(DEFAULT_HOLE_14_PAR);
        assertThat(testCourse.getHole14Yardage()).isEqualTo(DEFAULT_HOLE_14_YARDAGE);
        assertThat(testCourse.getHole14Handicap()).isEqualTo(DEFAULT_HOLE_14_HANDICAP);
        assertThat(testCourse.getHole15Par()).isEqualTo(DEFAULT_HOLE_15_PAR);
        assertThat(testCourse.getHole15Yardage()).isEqualTo(DEFAULT_HOLE_15_YARDAGE);
        assertThat(testCourse.getHole15Handicap()).isEqualTo(DEFAULT_HOLE_15_HANDICAP);
        assertThat(testCourse.getHole16Par()).isEqualTo(DEFAULT_HOLE_16_PAR);
        assertThat(testCourse.getHole16Yardage()).isEqualTo(DEFAULT_HOLE_16_YARDAGE);
        assertThat(testCourse.getHole16Handicap()).isEqualTo(DEFAULT_HOLE_16_HANDICAP);
        assertThat(testCourse.getHole17Par()).isEqualTo(DEFAULT_HOLE_17_PAR);
        assertThat(testCourse.getHole17Yardage()).isEqualTo(DEFAULT_HOLE_17_YARDAGE);
        assertThat(testCourse.getHole17Handicap()).isEqualTo(DEFAULT_HOLE_17_HANDICAP);
        assertThat(testCourse.getHole18Par()).isEqualTo(DEFAULT_HOLE_18_PAR);
        assertThat(testCourse.getHole18Yardage()).isEqualTo(DEFAULT_HOLE_18_YARDAGE);
        assertThat(testCourse.getHole18Handicap()).isEqualTo(DEFAULT_HOLE_18_HANDICAP);

        // Validate the Course in Elasticsearch
        verify(mockCourseSearchRepository, times(1)).save(testCourse);
    }

    @Test
    @Transactional
    public void createCourseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courseRepository.findAll().size();

        // Create the Course with an existing ID
        course.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourseMockMvc.perform(post("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeCreate);

        // Validate the Course in Elasticsearch
        verify(mockCourseSearchRepository, times(0)).save(course);
    }


    @Test
    @Transactional
    public void getAllCourses() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get all the courseList
        restCourseMockMvc.perform(get("/api/courses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].par").value(hasItem(DEFAULT_PAR)))
            .andExpect(jsonPath("$.[*].yardage").value(hasItem(DEFAULT_YARDAGE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].courseType").value(hasItem(DEFAULT_COURSE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].greenType").value(hasItem(DEFAULT_GREEN_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hole1Par").value(hasItem(DEFAULT_HOLE_1_PAR)))
            .andExpect(jsonPath("$.[*].hole1Yardage").value(hasItem(DEFAULT_HOLE_1_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole1Handicap").value(hasItem(DEFAULT_HOLE_1_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole2Par").value(hasItem(DEFAULT_HOLE_2_PAR)))
            .andExpect(jsonPath("$.[*].hole2Yardage").value(hasItem(DEFAULT_HOLE_2_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole2Handicap").value(hasItem(DEFAULT_HOLE_2_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole3Par").value(hasItem(DEFAULT_HOLE_3_PAR)))
            .andExpect(jsonPath("$.[*].hole3Yardage").value(hasItem(DEFAULT_HOLE_3_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole3Handicap").value(hasItem(DEFAULT_HOLE_3_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole4Par").value(hasItem(DEFAULT_HOLE_4_PAR)))
            .andExpect(jsonPath("$.[*].hole4Yardage").value(hasItem(DEFAULT_HOLE_4_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole4Handicap").value(hasItem(DEFAULT_HOLE_4_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole5Par").value(hasItem(DEFAULT_HOLE_5_PAR)))
            .andExpect(jsonPath("$.[*].hole5Yardage").value(hasItem(DEFAULT_HOLE_5_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole5Handicap").value(hasItem(DEFAULT_HOLE_5_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole6Par").value(hasItem(DEFAULT_HOLE_6_PAR)))
            .andExpect(jsonPath("$.[*].hole6Yardage").value(hasItem(DEFAULT_HOLE_6_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole6Handicap").value(hasItem(DEFAULT_HOLE_6_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole7Par").value(hasItem(DEFAULT_HOLE_7_PAR)))
            .andExpect(jsonPath("$.[*].hole7Yardage").value(hasItem(DEFAULT_HOLE_7_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole7Handicap").value(hasItem(DEFAULT_HOLE_7_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole8Par").value(hasItem(DEFAULT_HOLE_8_PAR)))
            .andExpect(jsonPath("$.[*].hole8Yardage").value(hasItem(DEFAULT_HOLE_8_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole8Handicap").value(hasItem(DEFAULT_HOLE_8_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole9Par").value(hasItem(DEFAULT_HOLE_9_PAR)))
            .andExpect(jsonPath("$.[*].hole9Yardage").value(hasItem(DEFAULT_HOLE_9_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole9Handicap").value(hasItem(DEFAULT_HOLE_9_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole10Par").value(hasItem(DEFAULT_HOLE_10_PAR)))
            .andExpect(jsonPath("$.[*].hole10Yardage").value(hasItem(DEFAULT_HOLE_10_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole10Handicap").value(hasItem(DEFAULT_HOLE_10_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole11Par").value(hasItem(DEFAULT_HOLE_11_PAR)))
            .andExpect(jsonPath("$.[*].hole11Yardage").value(hasItem(DEFAULT_HOLE_11_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole11Handicap").value(hasItem(DEFAULT_HOLE_11_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole12Par").value(hasItem(DEFAULT_HOLE_12_PAR)))
            .andExpect(jsonPath("$.[*].hole12Yardage").value(hasItem(DEFAULT_HOLE_12_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole12Handicap").value(hasItem(DEFAULT_HOLE_12_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole13Par").value(hasItem(DEFAULT_HOLE_13_PAR)))
            .andExpect(jsonPath("$.[*].hole13Yardage").value(hasItem(DEFAULT_HOLE_13_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole13Handicap").value(hasItem(DEFAULT_HOLE_13_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole14Par").value(hasItem(DEFAULT_HOLE_14_PAR)))
            .andExpect(jsonPath("$.[*].hole14Yardage").value(hasItem(DEFAULT_HOLE_14_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole14Handicap").value(hasItem(DEFAULT_HOLE_14_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole15Par").value(hasItem(DEFAULT_HOLE_15_PAR)))
            .andExpect(jsonPath("$.[*].hole15Yardage").value(hasItem(DEFAULT_HOLE_15_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole15Handicap").value(hasItem(DEFAULT_HOLE_15_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole16Par").value(hasItem(DEFAULT_HOLE_16_PAR)))
            .andExpect(jsonPath("$.[*].hole16Yardage").value(hasItem(DEFAULT_HOLE_16_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole16Handicap").value(hasItem(DEFAULT_HOLE_16_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole17Par").value(hasItem(DEFAULT_HOLE_17_PAR)))
            .andExpect(jsonPath("$.[*].hole17Yardage").value(hasItem(DEFAULT_HOLE_17_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole17Handicap").value(hasItem(DEFAULT_HOLE_17_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole18Par").value(hasItem(DEFAULT_HOLE_18_PAR)))
            .andExpect(jsonPath("$.[*].hole18Yardage").value(hasItem(DEFAULT_HOLE_18_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole18Handicap").value(hasItem(DEFAULT_HOLE_18_HANDICAP)));
    }
    
    @Test
    @Transactional
    public void getCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(course.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.par").value(DEFAULT_PAR))
            .andExpect(jsonPath("$.yardage").value(DEFAULT_YARDAGE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.courseType").value(DEFAULT_COURSE_TYPE.toString()))
            .andExpect(jsonPath("$.greenType").value(DEFAULT_GREEN_TYPE.toString()))
            .andExpect(jsonPath("$.hole1Par").value(DEFAULT_HOLE_1_PAR))
            .andExpect(jsonPath("$.hole1Yardage").value(DEFAULT_HOLE_1_YARDAGE))
            .andExpect(jsonPath("$.hole1Handicap").value(DEFAULT_HOLE_1_HANDICAP))
            .andExpect(jsonPath("$.hole2Par").value(DEFAULT_HOLE_2_PAR))
            .andExpect(jsonPath("$.hole2Yardage").value(DEFAULT_HOLE_2_YARDAGE))
            .andExpect(jsonPath("$.hole2Handicap").value(DEFAULT_HOLE_2_HANDICAP))
            .andExpect(jsonPath("$.hole3Par").value(DEFAULT_HOLE_3_PAR))
            .andExpect(jsonPath("$.hole3Yardage").value(DEFAULT_HOLE_3_YARDAGE))
            .andExpect(jsonPath("$.hole3Handicap").value(DEFAULT_HOLE_3_HANDICAP))
            .andExpect(jsonPath("$.hole4Par").value(DEFAULT_HOLE_4_PAR))
            .andExpect(jsonPath("$.hole4Yardage").value(DEFAULT_HOLE_4_YARDAGE))
            .andExpect(jsonPath("$.hole4Handicap").value(DEFAULT_HOLE_4_HANDICAP))
            .andExpect(jsonPath("$.hole5Par").value(DEFAULT_HOLE_5_PAR))
            .andExpect(jsonPath("$.hole5Yardage").value(DEFAULT_HOLE_5_YARDAGE))
            .andExpect(jsonPath("$.hole5Handicap").value(DEFAULT_HOLE_5_HANDICAP))
            .andExpect(jsonPath("$.hole6Par").value(DEFAULT_HOLE_6_PAR))
            .andExpect(jsonPath("$.hole6Yardage").value(DEFAULT_HOLE_6_YARDAGE))
            .andExpect(jsonPath("$.hole6Handicap").value(DEFAULT_HOLE_6_HANDICAP))
            .andExpect(jsonPath("$.hole7Par").value(DEFAULT_HOLE_7_PAR))
            .andExpect(jsonPath("$.hole7Yardage").value(DEFAULT_HOLE_7_YARDAGE))
            .andExpect(jsonPath("$.hole7Handicap").value(DEFAULT_HOLE_7_HANDICAP))
            .andExpect(jsonPath("$.hole8Par").value(DEFAULT_HOLE_8_PAR))
            .andExpect(jsonPath("$.hole8Yardage").value(DEFAULT_HOLE_8_YARDAGE))
            .andExpect(jsonPath("$.hole8Handicap").value(DEFAULT_HOLE_8_HANDICAP))
            .andExpect(jsonPath("$.hole9Par").value(DEFAULT_HOLE_9_PAR))
            .andExpect(jsonPath("$.hole9Yardage").value(DEFAULT_HOLE_9_YARDAGE))
            .andExpect(jsonPath("$.hole9Handicap").value(DEFAULT_HOLE_9_HANDICAP))
            .andExpect(jsonPath("$.hole10Par").value(DEFAULT_HOLE_10_PAR))
            .andExpect(jsonPath("$.hole10Yardage").value(DEFAULT_HOLE_10_YARDAGE))
            .andExpect(jsonPath("$.hole10Handicap").value(DEFAULT_HOLE_10_HANDICAP))
            .andExpect(jsonPath("$.hole11Par").value(DEFAULT_HOLE_11_PAR))
            .andExpect(jsonPath("$.hole11Yardage").value(DEFAULT_HOLE_11_YARDAGE))
            .andExpect(jsonPath("$.hole11Handicap").value(DEFAULT_HOLE_11_HANDICAP))
            .andExpect(jsonPath("$.hole12Par").value(DEFAULT_HOLE_12_PAR))
            .andExpect(jsonPath("$.hole12Yardage").value(DEFAULT_HOLE_12_YARDAGE))
            .andExpect(jsonPath("$.hole12Handicap").value(DEFAULT_HOLE_12_HANDICAP))
            .andExpect(jsonPath("$.hole13Par").value(DEFAULT_HOLE_13_PAR))
            .andExpect(jsonPath("$.hole13Yardage").value(DEFAULT_HOLE_13_YARDAGE))
            .andExpect(jsonPath("$.hole13Handicap").value(DEFAULT_HOLE_13_HANDICAP))
            .andExpect(jsonPath("$.hole14Par").value(DEFAULT_HOLE_14_PAR))
            .andExpect(jsonPath("$.hole14Yardage").value(DEFAULT_HOLE_14_YARDAGE))
            .andExpect(jsonPath("$.hole14Handicap").value(DEFAULT_HOLE_14_HANDICAP))
            .andExpect(jsonPath("$.hole15Par").value(DEFAULT_HOLE_15_PAR))
            .andExpect(jsonPath("$.hole15Yardage").value(DEFAULT_HOLE_15_YARDAGE))
            .andExpect(jsonPath("$.hole15Handicap").value(DEFAULT_HOLE_15_HANDICAP))
            .andExpect(jsonPath("$.hole16Par").value(DEFAULT_HOLE_16_PAR))
            .andExpect(jsonPath("$.hole16Yardage").value(DEFAULT_HOLE_16_YARDAGE))
            .andExpect(jsonPath("$.hole16Handicap").value(DEFAULT_HOLE_16_HANDICAP))
            .andExpect(jsonPath("$.hole17Par").value(DEFAULT_HOLE_17_PAR))
            .andExpect(jsonPath("$.hole17Yardage").value(DEFAULT_HOLE_17_YARDAGE))
            .andExpect(jsonPath("$.hole17Handicap").value(DEFAULT_HOLE_17_HANDICAP))
            .andExpect(jsonPath("$.hole18Par").value(DEFAULT_HOLE_18_PAR))
            .andExpect(jsonPath("$.hole18Yardage").value(DEFAULT_HOLE_18_YARDAGE))
            .andExpect(jsonPath("$.hole18Handicap").value(DEFAULT_HOLE_18_HANDICAP));
    }

    @Test
    @Transactional
    public void getNonExistingCourse() throws Exception {
        // Get the course
        restCourseMockMvc.perform(get("/api/courses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Update the course
        Course updatedCourse = courseRepository.findById(course.getId()).get();
        // Disconnect from session so that the updates on updatedCourse are not directly saved in db
        em.detach(updatedCourse);
        updatedCourse
            .name(UPDATED_NAME)
            .par(UPDATED_PAR)
            .yardage(UPDATED_YARDAGE)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .courseType(UPDATED_COURSE_TYPE)
            .greenType(UPDATED_GREEN_TYPE)
            .hole1Par(UPDATED_HOLE_1_PAR)
            .hole1Yardage(UPDATED_HOLE_1_YARDAGE)
            .hole1Handicap(UPDATED_HOLE_1_HANDICAP)
            .hole2Par(UPDATED_HOLE_2_PAR)
            .hole2Yardage(UPDATED_HOLE_2_YARDAGE)
            .hole2Handicap(UPDATED_HOLE_2_HANDICAP)
            .hole3Par(UPDATED_HOLE_3_PAR)
            .hole3Yardage(UPDATED_HOLE_3_YARDAGE)
            .hole3Handicap(UPDATED_HOLE_3_HANDICAP)
            .hole4Par(UPDATED_HOLE_4_PAR)
            .hole4Yardage(UPDATED_HOLE_4_YARDAGE)
            .hole4Handicap(UPDATED_HOLE_4_HANDICAP)
            .hole5Par(UPDATED_HOLE_5_PAR)
            .hole5Yardage(UPDATED_HOLE_5_YARDAGE)
            .hole5Handicap(UPDATED_HOLE_5_HANDICAP)
            .hole6Par(UPDATED_HOLE_6_PAR)
            .hole6Yardage(UPDATED_HOLE_6_YARDAGE)
            .hole6Handicap(UPDATED_HOLE_6_HANDICAP)
            .hole7Par(UPDATED_HOLE_7_PAR)
            .hole7Yardage(UPDATED_HOLE_7_YARDAGE)
            .hole7Handicap(UPDATED_HOLE_7_HANDICAP)
            .hole8Par(UPDATED_HOLE_8_PAR)
            .hole8Yardage(UPDATED_HOLE_8_YARDAGE)
            .hole8Handicap(UPDATED_HOLE_8_HANDICAP)
            .hole9Par(UPDATED_HOLE_9_PAR)
            .hole9Yardage(UPDATED_HOLE_9_YARDAGE)
            .hole9Handicap(UPDATED_HOLE_9_HANDICAP)
            .hole10Par(UPDATED_HOLE_10_PAR)
            .hole10Yardage(UPDATED_HOLE_10_YARDAGE)
            .hole10Handicap(UPDATED_HOLE_10_HANDICAP)
            .hole11Par(UPDATED_HOLE_11_PAR)
            .hole11Yardage(UPDATED_HOLE_11_YARDAGE)
            .hole11Handicap(UPDATED_HOLE_11_HANDICAP)
            .hole12Par(UPDATED_HOLE_12_PAR)
            .hole12Yardage(UPDATED_HOLE_12_YARDAGE)
            .hole12Handicap(UPDATED_HOLE_12_HANDICAP)
            .hole13Par(UPDATED_HOLE_13_PAR)
            .hole13Yardage(UPDATED_HOLE_13_YARDAGE)
            .hole13Handicap(UPDATED_HOLE_13_HANDICAP)
            .hole14Par(UPDATED_HOLE_14_PAR)
            .hole14Yardage(UPDATED_HOLE_14_YARDAGE)
            .hole14Handicap(UPDATED_HOLE_14_HANDICAP)
            .hole15Par(UPDATED_HOLE_15_PAR)
            .hole15Yardage(UPDATED_HOLE_15_YARDAGE)
            .hole15Handicap(UPDATED_HOLE_15_HANDICAP)
            .hole16Par(UPDATED_HOLE_16_PAR)
            .hole16Yardage(UPDATED_HOLE_16_YARDAGE)
            .hole16Handicap(UPDATED_HOLE_16_HANDICAP)
            .hole17Par(UPDATED_HOLE_17_PAR)
            .hole17Yardage(UPDATED_HOLE_17_YARDAGE)
            .hole17Handicap(UPDATED_HOLE_17_HANDICAP)
            .hole18Par(UPDATED_HOLE_18_PAR)
            .hole18Yardage(UPDATED_HOLE_18_YARDAGE)
            .hole18Handicap(UPDATED_HOLE_18_HANDICAP);

        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCourse)))
            .andExpect(status().isOk());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);
        Course testCourse = courseList.get(courseList.size() - 1);
        assertThat(testCourse.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCourse.getPar()).isEqualTo(UPDATED_PAR);
        assertThat(testCourse.getYardage()).isEqualTo(UPDATED_YARDAGE);
        assertThat(testCourse.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCourse.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testCourse.getCourseType()).isEqualTo(UPDATED_COURSE_TYPE);
        assertThat(testCourse.getGreenType()).isEqualTo(UPDATED_GREEN_TYPE);
        assertThat(testCourse.getHole1Par()).isEqualTo(UPDATED_HOLE_1_PAR);
        assertThat(testCourse.getHole1Yardage()).isEqualTo(UPDATED_HOLE_1_YARDAGE);
        assertThat(testCourse.getHole1Handicap()).isEqualTo(UPDATED_HOLE_1_HANDICAP);
        assertThat(testCourse.getHole2Par()).isEqualTo(UPDATED_HOLE_2_PAR);
        assertThat(testCourse.getHole2Yardage()).isEqualTo(UPDATED_HOLE_2_YARDAGE);
        assertThat(testCourse.getHole2Handicap()).isEqualTo(UPDATED_HOLE_2_HANDICAP);
        assertThat(testCourse.getHole3Par()).isEqualTo(UPDATED_HOLE_3_PAR);
        assertThat(testCourse.getHole3Yardage()).isEqualTo(UPDATED_HOLE_3_YARDAGE);
        assertThat(testCourse.getHole3Handicap()).isEqualTo(UPDATED_HOLE_3_HANDICAP);
        assertThat(testCourse.getHole4Par()).isEqualTo(UPDATED_HOLE_4_PAR);
        assertThat(testCourse.getHole4Yardage()).isEqualTo(UPDATED_HOLE_4_YARDAGE);
        assertThat(testCourse.getHole4Handicap()).isEqualTo(UPDATED_HOLE_4_HANDICAP);
        assertThat(testCourse.getHole5Par()).isEqualTo(UPDATED_HOLE_5_PAR);
        assertThat(testCourse.getHole5Yardage()).isEqualTo(UPDATED_HOLE_5_YARDAGE);
        assertThat(testCourse.getHole5Handicap()).isEqualTo(UPDATED_HOLE_5_HANDICAP);
        assertThat(testCourse.getHole6Par()).isEqualTo(UPDATED_HOLE_6_PAR);
        assertThat(testCourse.getHole6Yardage()).isEqualTo(UPDATED_HOLE_6_YARDAGE);
        assertThat(testCourse.getHole6Handicap()).isEqualTo(UPDATED_HOLE_6_HANDICAP);
        assertThat(testCourse.getHole7Par()).isEqualTo(UPDATED_HOLE_7_PAR);
        assertThat(testCourse.getHole7Yardage()).isEqualTo(UPDATED_HOLE_7_YARDAGE);
        assertThat(testCourse.getHole7Handicap()).isEqualTo(UPDATED_HOLE_7_HANDICAP);
        assertThat(testCourse.getHole8Par()).isEqualTo(UPDATED_HOLE_8_PAR);
        assertThat(testCourse.getHole8Yardage()).isEqualTo(UPDATED_HOLE_8_YARDAGE);
        assertThat(testCourse.getHole8Handicap()).isEqualTo(UPDATED_HOLE_8_HANDICAP);
        assertThat(testCourse.getHole9Par()).isEqualTo(UPDATED_HOLE_9_PAR);
        assertThat(testCourse.getHole9Yardage()).isEqualTo(UPDATED_HOLE_9_YARDAGE);
        assertThat(testCourse.getHole9Handicap()).isEqualTo(UPDATED_HOLE_9_HANDICAP);
        assertThat(testCourse.getHole10Par()).isEqualTo(UPDATED_HOLE_10_PAR);
        assertThat(testCourse.getHole10Yardage()).isEqualTo(UPDATED_HOLE_10_YARDAGE);
        assertThat(testCourse.getHole10Handicap()).isEqualTo(UPDATED_HOLE_10_HANDICAP);
        assertThat(testCourse.getHole11Par()).isEqualTo(UPDATED_HOLE_11_PAR);
        assertThat(testCourse.getHole11Yardage()).isEqualTo(UPDATED_HOLE_11_YARDAGE);
        assertThat(testCourse.getHole11Handicap()).isEqualTo(UPDATED_HOLE_11_HANDICAP);
        assertThat(testCourse.getHole12Par()).isEqualTo(UPDATED_HOLE_12_PAR);
        assertThat(testCourse.getHole12Yardage()).isEqualTo(UPDATED_HOLE_12_YARDAGE);
        assertThat(testCourse.getHole12Handicap()).isEqualTo(UPDATED_HOLE_12_HANDICAP);
        assertThat(testCourse.getHole13Par()).isEqualTo(UPDATED_HOLE_13_PAR);
        assertThat(testCourse.getHole13Yardage()).isEqualTo(UPDATED_HOLE_13_YARDAGE);
        assertThat(testCourse.getHole13Handicap()).isEqualTo(UPDATED_HOLE_13_HANDICAP);
        assertThat(testCourse.getHole14Par()).isEqualTo(UPDATED_HOLE_14_PAR);
        assertThat(testCourse.getHole14Yardage()).isEqualTo(UPDATED_HOLE_14_YARDAGE);
        assertThat(testCourse.getHole14Handicap()).isEqualTo(UPDATED_HOLE_14_HANDICAP);
        assertThat(testCourse.getHole15Par()).isEqualTo(UPDATED_HOLE_15_PAR);
        assertThat(testCourse.getHole15Yardage()).isEqualTo(UPDATED_HOLE_15_YARDAGE);
        assertThat(testCourse.getHole15Handicap()).isEqualTo(UPDATED_HOLE_15_HANDICAP);
        assertThat(testCourse.getHole16Par()).isEqualTo(UPDATED_HOLE_16_PAR);
        assertThat(testCourse.getHole16Yardage()).isEqualTo(UPDATED_HOLE_16_YARDAGE);
        assertThat(testCourse.getHole16Handicap()).isEqualTo(UPDATED_HOLE_16_HANDICAP);
        assertThat(testCourse.getHole17Par()).isEqualTo(UPDATED_HOLE_17_PAR);
        assertThat(testCourse.getHole17Yardage()).isEqualTo(UPDATED_HOLE_17_YARDAGE);
        assertThat(testCourse.getHole17Handicap()).isEqualTo(UPDATED_HOLE_17_HANDICAP);
        assertThat(testCourse.getHole18Par()).isEqualTo(UPDATED_HOLE_18_PAR);
        assertThat(testCourse.getHole18Yardage()).isEqualTo(UPDATED_HOLE_18_YARDAGE);
        assertThat(testCourse.getHole18Handicap()).isEqualTo(UPDATED_HOLE_18_HANDICAP);

        // Validate the Course in Elasticsearch
        verify(mockCourseSearchRepository, times(1)).save(testCourse);
    }

    @Test
    @Transactional
    public void updateNonExistingCourse() throws Exception {
        int databaseSizeBeforeUpdate = courseRepository.findAll().size();

        // Create the Course

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourseMockMvc.perform(put("/api/courses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(course)))
            .andExpect(status().isBadRequest());

        // Validate the Course in the database
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Course in Elasticsearch
        verify(mockCourseSearchRepository, times(0)).save(course);
    }

    @Test
    @Transactional
    public void deleteCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);

        int databaseSizeBeforeDelete = courseRepository.findAll().size();

        // Delete the course
        restCourseMockMvc.perform(delete("/api/courses/{id}", course.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Course> courseList = courseRepository.findAll();
        assertThat(courseList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Course in Elasticsearch
        verify(mockCourseSearchRepository, times(1)).deleteById(course.getId());
    }

    @Test
    @Transactional
    public void searchCourse() throws Exception {
        // Initialize the database
        courseRepository.saveAndFlush(course);
        when(mockCourseSearchRepository.search(queryStringQuery("id:" + course.getId())))
            .thenReturn(Collections.singletonList(course));
        // Search the course
        restCourseMockMvc.perform(get("/api/_search/courses?query=id:" + course.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(course.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].par").value(hasItem(DEFAULT_PAR)))
            .andExpect(jsonPath("$.[*].yardage").value(hasItem(DEFAULT_YARDAGE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].courseType").value(hasItem(DEFAULT_COURSE_TYPE)))
            .andExpect(jsonPath("$.[*].greenType").value(hasItem(DEFAULT_GREEN_TYPE)))
            .andExpect(jsonPath("$.[*].hole1Par").value(hasItem(DEFAULT_HOLE_1_PAR)))
            .andExpect(jsonPath("$.[*].hole1Yardage").value(hasItem(DEFAULT_HOLE_1_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole1Handicap").value(hasItem(DEFAULT_HOLE_1_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole2Par").value(hasItem(DEFAULT_HOLE_2_PAR)))
            .andExpect(jsonPath("$.[*].hole2Yardage").value(hasItem(DEFAULT_HOLE_2_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole2Handicap").value(hasItem(DEFAULT_HOLE_2_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole3Par").value(hasItem(DEFAULT_HOLE_3_PAR)))
            .andExpect(jsonPath("$.[*].hole3Yardage").value(hasItem(DEFAULT_HOLE_3_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole3Handicap").value(hasItem(DEFAULT_HOLE_3_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole4Par").value(hasItem(DEFAULT_HOLE_4_PAR)))
            .andExpect(jsonPath("$.[*].hole4Yardage").value(hasItem(DEFAULT_HOLE_4_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole4Handicap").value(hasItem(DEFAULT_HOLE_4_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole5Par").value(hasItem(DEFAULT_HOLE_5_PAR)))
            .andExpect(jsonPath("$.[*].hole5Yardage").value(hasItem(DEFAULT_HOLE_5_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole5Handicap").value(hasItem(DEFAULT_HOLE_5_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole6Par").value(hasItem(DEFAULT_HOLE_6_PAR)))
            .andExpect(jsonPath("$.[*].hole6Yardage").value(hasItem(DEFAULT_HOLE_6_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole6Handicap").value(hasItem(DEFAULT_HOLE_6_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole7Par").value(hasItem(DEFAULT_HOLE_7_PAR)))
            .andExpect(jsonPath("$.[*].hole7Yardage").value(hasItem(DEFAULT_HOLE_7_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole7Handicap").value(hasItem(DEFAULT_HOLE_7_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole8Par").value(hasItem(DEFAULT_HOLE_8_PAR)))
            .andExpect(jsonPath("$.[*].hole8Yardage").value(hasItem(DEFAULT_HOLE_8_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole8Handicap").value(hasItem(DEFAULT_HOLE_8_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole9Par").value(hasItem(DEFAULT_HOLE_9_PAR)))
            .andExpect(jsonPath("$.[*].hole9Yardage").value(hasItem(DEFAULT_HOLE_9_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole9Handicap").value(hasItem(DEFAULT_HOLE_9_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole10Par").value(hasItem(DEFAULT_HOLE_10_PAR)))
            .andExpect(jsonPath("$.[*].hole10Yardage").value(hasItem(DEFAULT_HOLE_10_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole10Handicap").value(hasItem(DEFAULT_HOLE_10_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole11Par").value(hasItem(DEFAULT_HOLE_11_PAR)))
            .andExpect(jsonPath("$.[*].hole11Yardage").value(hasItem(DEFAULT_HOLE_11_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole11Handicap").value(hasItem(DEFAULT_HOLE_11_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole12Par").value(hasItem(DEFAULT_HOLE_12_PAR)))
            .andExpect(jsonPath("$.[*].hole12Yardage").value(hasItem(DEFAULT_HOLE_12_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole12Handicap").value(hasItem(DEFAULT_HOLE_12_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole13Par").value(hasItem(DEFAULT_HOLE_13_PAR)))
            .andExpect(jsonPath("$.[*].hole13Yardage").value(hasItem(DEFAULT_HOLE_13_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole13Handicap").value(hasItem(DEFAULT_HOLE_13_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole14Par").value(hasItem(DEFAULT_HOLE_14_PAR)))
            .andExpect(jsonPath("$.[*].hole14Yardage").value(hasItem(DEFAULT_HOLE_14_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole14Handicap").value(hasItem(DEFAULT_HOLE_14_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole15Par").value(hasItem(DEFAULT_HOLE_15_PAR)))
            .andExpect(jsonPath("$.[*].hole15Yardage").value(hasItem(DEFAULT_HOLE_15_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole15Handicap").value(hasItem(DEFAULT_HOLE_15_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole16Par").value(hasItem(DEFAULT_HOLE_16_PAR)))
            .andExpect(jsonPath("$.[*].hole16Yardage").value(hasItem(DEFAULT_HOLE_16_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole16Handicap").value(hasItem(DEFAULT_HOLE_16_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole17Par").value(hasItem(DEFAULT_HOLE_17_PAR)))
            .andExpect(jsonPath("$.[*].hole17Yardage").value(hasItem(DEFAULT_HOLE_17_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole17Handicap").value(hasItem(DEFAULT_HOLE_17_HANDICAP)))
            .andExpect(jsonPath("$.[*].hole18Par").value(hasItem(DEFAULT_HOLE_18_PAR)))
            .andExpect(jsonPath("$.[*].hole18Yardage").value(hasItem(DEFAULT_HOLE_18_YARDAGE)))
            .andExpect(jsonPath("$.[*].hole18Handicap").value(hasItem(DEFAULT_HOLE_18_HANDICAP)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Course.class);
        Course course1 = new Course();
        course1.setId(1L);
        Course course2 = new Course();
        course2.setId(course1.getId());
        assertThat(course1).isEqualTo(course2);
        course2.setId(2L);
        assertThat(course1).isNotEqualTo(course2);
        course1.setId(null);
        assertThat(course1).isNotEqualTo(course2);
    }
}
