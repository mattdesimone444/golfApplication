package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.DistanceOfApproach;
import com.furious.golf.repository.DistanceOfApproachRepository;
import com.furious.golf.repository.search.DistanceOfApproachSearchRepository;
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
 * Integration tests for the {@link DistanceOfApproachResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class DistanceOfApproachResourceIT {

    private static final Long DEFAULT_PLAYER_ID = 1L;
    private static final Long UPDATED_PLAYER_ID = 2L;
    private static final Long SMALLER_PLAYER_ID = 1L - 1L;

    private static final Long DEFAULT_TOURNAMENT_ID = 1L;
    private static final Long UPDATED_TOURNAMENT_ID = 2L;
    private static final Long SMALLER_TOURNAMENT_ID = 1L - 1L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;
    private static final Long SMALLER_COURSE_ID = 1L - 1L;

    private static final Integer DEFAULT_HOLE_1 = 1;
    private static final Integer UPDATED_HOLE_1 = 2;
    private static final Integer SMALLER_HOLE_1 = 1 - 1;

    private static final Integer DEFAULT_HOLE_2 = 1;
    private static final Integer UPDATED_HOLE_2 = 2;
    private static final Integer SMALLER_HOLE_2 = 1 - 1;

    private static final Integer DEFAULT_HOLE_3 = 1;
    private static final Integer UPDATED_HOLE_3 = 2;
    private static final Integer SMALLER_HOLE_3 = 1 - 1;

    private static final Integer DEFAULT_HOLE_4 = 1;
    private static final Integer UPDATED_HOLE_4 = 2;
    private static final Integer SMALLER_HOLE_4 = 1 - 1;

    private static final Integer DEFAULT_HOLE_5 = 1;
    private static final Integer UPDATED_HOLE_5 = 2;
    private static final Integer SMALLER_HOLE_5 = 1 - 1;

    private static final Integer DEFAULT_HOLE_6 = 1;
    private static final Integer UPDATED_HOLE_6 = 2;
    private static final Integer SMALLER_HOLE_6 = 1 - 1;

    private static final Integer DEFAULT_HOLE_7 = 1;
    private static final Integer UPDATED_HOLE_7 = 2;
    private static final Integer SMALLER_HOLE_7 = 1 - 1;

    private static final Integer DEFAULT_HOLE_8 = 1;
    private static final Integer UPDATED_HOLE_8 = 2;
    private static final Integer SMALLER_HOLE_8 = 1 - 1;

    private static final Integer DEFAULT_HOLE_9 = 1;
    private static final Integer UPDATED_HOLE_9 = 2;
    private static final Integer SMALLER_HOLE_9 = 1 - 1;

    private static final Integer DEFAULT_HOLE_10 = 1;
    private static final Integer UPDATED_HOLE_10 = 2;
    private static final Integer SMALLER_HOLE_10 = 1 - 1;

    private static final Integer DEFAULT_HOLE_11 = 1;
    private static final Integer UPDATED_HOLE_11 = 2;
    private static final Integer SMALLER_HOLE_11 = 1 - 1;

    private static final Integer DEFAULT_HOLE_12 = 1;
    private static final Integer UPDATED_HOLE_12 = 2;
    private static final Integer SMALLER_HOLE_12 = 1 - 1;

    private static final Integer DEFAULT_HOLE_13 = 1;
    private static final Integer UPDATED_HOLE_13 = 2;
    private static final Integer SMALLER_HOLE_13 = 1 - 1;

    private static final Integer DEFAULT_HOLE_14 = 1;
    private static final Integer UPDATED_HOLE_14 = 2;
    private static final Integer SMALLER_HOLE_14 = 1 - 1;

    private static final Integer DEFAULT_HOLE_15 = 1;
    private static final Integer UPDATED_HOLE_15 = 2;
    private static final Integer SMALLER_HOLE_15 = 1 - 1;

    private static final Integer DEFAULT_HOLE_16 = 1;
    private static final Integer UPDATED_HOLE_16 = 2;
    private static final Integer SMALLER_HOLE_16 = 1 - 1;

    private static final Integer DEFAULT_HOLE_17 = 1;
    private static final Integer UPDATED_HOLE_17 = 2;
    private static final Integer SMALLER_HOLE_17 = 1 - 1;

    private static final Integer DEFAULT_HOLE_18 = 1;
    private static final Integer UPDATED_HOLE_18 = 2;
    private static final Integer SMALLER_HOLE_18 = 1 - 1;

    @Autowired
    private DistanceOfApproachRepository distanceOfApproachRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.DistanceOfApproachSearchRepositoryMockConfiguration
     */
    @Autowired
    private DistanceOfApproachSearchRepository mockDistanceOfApproachSearchRepository;

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

    private MockMvc restDistanceOfApproachMockMvc;

    private DistanceOfApproach distanceOfApproach;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DistanceOfApproachResource distanceOfApproachResource = new DistanceOfApproachResource(distanceOfApproachRepository, mockDistanceOfApproachSearchRepository);
        this.restDistanceOfApproachMockMvc = MockMvcBuilders.standaloneSetup(distanceOfApproachResource)
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
    public static DistanceOfApproach createEntity(EntityManager em) {
        DistanceOfApproach distanceOfApproach = new DistanceOfApproach()
            .playerId(DEFAULT_PLAYER_ID)
            .tournamentId(DEFAULT_TOURNAMENT_ID)
            .courseId(DEFAULT_COURSE_ID)
            .hole1(DEFAULT_HOLE_1)
            .hole2(DEFAULT_HOLE_2)
            .hole3(DEFAULT_HOLE_3)
            .hole4(DEFAULT_HOLE_4)
            .hole5(DEFAULT_HOLE_5)
            .hole6(DEFAULT_HOLE_6)
            .hole7(DEFAULT_HOLE_7)
            .hole8(DEFAULT_HOLE_8)
            .hole9(DEFAULT_HOLE_9)
            .hole10(DEFAULT_HOLE_10)
            .hole11(DEFAULT_HOLE_11)
            .hole12(DEFAULT_HOLE_12)
            .hole13(DEFAULT_HOLE_13)
            .hole14(DEFAULT_HOLE_14)
            .hole15(DEFAULT_HOLE_15)
            .hole16(DEFAULT_HOLE_16)
            .hole17(DEFAULT_HOLE_17)
            .hole18(DEFAULT_HOLE_18);
        return distanceOfApproach;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DistanceOfApproach createUpdatedEntity(EntityManager em) {
        DistanceOfApproach distanceOfApproach = new DistanceOfApproach()
            .playerId(UPDATED_PLAYER_ID)
            .tournamentId(UPDATED_TOURNAMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .hole1(UPDATED_HOLE_1)
            .hole2(UPDATED_HOLE_2)
            .hole3(UPDATED_HOLE_3)
            .hole4(UPDATED_HOLE_4)
            .hole5(UPDATED_HOLE_5)
            .hole6(UPDATED_HOLE_6)
            .hole7(UPDATED_HOLE_7)
            .hole8(UPDATED_HOLE_8)
            .hole9(UPDATED_HOLE_9)
            .hole10(UPDATED_HOLE_10)
            .hole11(UPDATED_HOLE_11)
            .hole12(UPDATED_HOLE_12)
            .hole13(UPDATED_HOLE_13)
            .hole14(UPDATED_HOLE_14)
            .hole15(UPDATED_HOLE_15)
            .hole16(UPDATED_HOLE_16)
            .hole17(UPDATED_HOLE_17)
            .hole18(UPDATED_HOLE_18);
        return distanceOfApproach;
    }

    @BeforeEach
    public void initTest() {
        distanceOfApproach = createEntity(em);
    }

    @Test
    @Transactional
    public void createDistanceOfApproach() throws Exception {
        int databaseSizeBeforeCreate = distanceOfApproachRepository.findAll().size();

        // Create the DistanceOfApproach
        restDistanceOfApproachMockMvc.perform(post("/api/distance-of-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distanceOfApproach)))
            .andExpect(status().isCreated());

        // Validate the DistanceOfApproach in the database
        List<DistanceOfApproach> distanceOfApproachList = distanceOfApproachRepository.findAll();
        assertThat(distanceOfApproachList).hasSize(databaseSizeBeforeCreate + 1);
        DistanceOfApproach testDistanceOfApproach = distanceOfApproachList.get(distanceOfApproachList.size() - 1);
        assertThat(testDistanceOfApproach.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testDistanceOfApproach.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testDistanceOfApproach.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testDistanceOfApproach.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testDistanceOfApproach.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testDistanceOfApproach.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testDistanceOfApproach.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testDistanceOfApproach.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testDistanceOfApproach.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testDistanceOfApproach.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testDistanceOfApproach.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testDistanceOfApproach.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testDistanceOfApproach.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testDistanceOfApproach.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testDistanceOfApproach.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testDistanceOfApproach.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testDistanceOfApproach.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testDistanceOfApproach.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testDistanceOfApproach.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testDistanceOfApproach.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testDistanceOfApproach.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the DistanceOfApproach in Elasticsearch
        verify(mockDistanceOfApproachSearchRepository, times(1)).save(testDistanceOfApproach);
    }

    @Test
    @Transactional
    public void createDistanceOfApproachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = distanceOfApproachRepository.findAll().size();

        // Create the DistanceOfApproach with an existing ID
        distanceOfApproach.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDistanceOfApproachMockMvc.perform(post("/api/distance-of-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distanceOfApproach)))
            .andExpect(status().isBadRequest());

        // Validate the DistanceOfApproach in the database
        List<DistanceOfApproach> distanceOfApproachList = distanceOfApproachRepository.findAll();
        assertThat(distanceOfApproachList).hasSize(databaseSizeBeforeCreate);

        // Validate the DistanceOfApproach in Elasticsearch
        verify(mockDistanceOfApproachSearchRepository, times(0)).save(distanceOfApproach);
    }


    @Test
    @Transactional
    public void getAllDistanceOfApproaches() throws Exception {
        // Initialize the database
        distanceOfApproachRepository.saveAndFlush(distanceOfApproach);

        // Get all the distanceOfApproachList
        restDistanceOfApproachMockMvc.perform(get("/api/distance-of-approaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distanceOfApproach.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].hole1").value(hasItem(DEFAULT_HOLE_1)))
            .andExpect(jsonPath("$.[*].hole2").value(hasItem(DEFAULT_HOLE_2)))
            .andExpect(jsonPath("$.[*].hole3").value(hasItem(DEFAULT_HOLE_3)))
            .andExpect(jsonPath("$.[*].hole4").value(hasItem(DEFAULT_HOLE_4)))
            .andExpect(jsonPath("$.[*].hole5").value(hasItem(DEFAULT_HOLE_5)))
            .andExpect(jsonPath("$.[*].hole6").value(hasItem(DEFAULT_HOLE_6)))
            .andExpect(jsonPath("$.[*].hole7").value(hasItem(DEFAULT_HOLE_7)))
            .andExpect(jsonPath("$.[*].hole8").value(hasItem(DEFAULT_HOLE_8)))
            .andExpect(jsonPath("$.[*].hole9").value(hasItem(DEFAULT_HOLE_9)))
            .andExpect(jsonPath("$.[*].hole10").value(hasItem(DEFAULT_HOLE_10)))
            .andExpect(jsonPath("$.[*].hole11").value(hasItem(DEFAULT_HOLE_11)))
            .andExpect(jsonPath("$.[*].hole12").value(hasItem(DEFAULT_HOLE_12)))
            .andExpect(jsonPath("$.[*].hole13").value(hasItem(DEFAULT_HOLE_13)))
            .andExpect(jsonPath("$.[*].hole14").value(hasItem(DEFAULT_HOLE_14)))
            .andExpect(jsonPath("$.[*].hole15").value(hasItem(DEFAULT_HOLE_15)))
            .andExpect(jsonPath("$.[*].hole16").value(hasItem(DEFAULT_HOLE_16)))
            .andExpect(jsonPath("$.[*].hole17").value(hasItem(DEFAULT_HOLE_17)))
            .andExpect(jsonPath("$.[*].hole18").value(hasItem(DEFAULT_HOLE_18)));
    }
    
    @Test
    @Transactional
    public void getDistanceOfApproach() throws Exception {
        // Initialize the database
        distanceOfApproachRepository.saveAndFlush(distanceOfApproach);

        // Get the distanceOfApproach
        restDistanceOfApproachMockMvc.perform(get("/api/distance-of-approaches/{id}", distanceOfApproach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(distanceOfApproach.getId().intValue()))
            .andExpect(jsonPath("$.playerId").value(DEFAULT_PLAYER_ID.intValue()))
            .andExpect(jsonPath("$.tournamentId").value(DEFAULT_TOURNAMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.hole1").value(DEFAULT_HOLE_1))
            .andExpect(jsonPath("$.hole2").value(DEFAULT_HOLE_2))
            .andExpect(jsonPath("$.hole3").value(DEFAULT_HOLE_3))
            .andExpect(jsonPath("$.hole4").value(DEFAULT_HOLE_4))
            .andExpect(jsonPath("$.hole5").value(DEFAULT_HOLE_5))
            .andExpect(jsonPath("$.hole6").value(DEFAULT_HOLE_6))
            .andExpect(jsonPath("$.hole7").value(DEFAULT_HOLE_7))
            .andExpect(jsonPath("$.hole8").value(DEFAULT_HOLE_8))
            .andExpect(jsonPath("$.hole9").value(DEFAULT_HOLE_9))
            .andExpect(jsonPath("$.hole10").value(DEFAULT_HOLE_10))
            .andExpect(jsonPath("$.hole11").value(DEFAULT_HOLE_11))
            .andExpect(jsonPath("$.hole12").value(DEFAULT_HOLE_12))
            .andExpect(jsonPath("$.hole13").value(DEFAULT_HOLE_13))
            .andExpect(jsonPath("$.hole14").value(DEFAULT_HOLE_14))
            .andExpect(jsonPath("$.hole15").value(DEFAULT_HOLE_15))
            .andExpect(jsonPath("$.hole16").value(DEFAULT_HOLE_16))
            .andExpect(jsonPath("$.hole17").value(DEFAULT_HOLE_17))
            .andExpect(jsonPath("$.hole18").value(DEFAULT_HOLE_18));
    }

    @Test
    @Transactional
    public void getNonExistingDistanceOfApproach() throws Exception {
        // Get the distanceOfApproach
        restDistanceOfApproachMockMvc.perform(get("/api/distance-of-approaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDistanceOfApproach() throws Exception {
        // Initialize the database
        distanceOfApproachRepository.saveAndFlush(distanceOfApproach);

        int databaseSizeBeforeUpdate = distanceOfApproachRepository.findAll().size();

        // Update the distanceOfApproach
        DistanceOfApproach updatedDistanceOfApproach = distanceOfApproachRepository.findById(distanceOfApproach.getId()).get();
        // Disconnect from session so that the updates on updatedDistanceOfApproach are not directly saved in db
        em.detach(updatedDistanceOfApproach);
        updatedDistanceOfApproach
            .playerId(UPDATED_PLAYER_ID)
            .tournamentId(UPDATED_TOURNAMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .hole1(UPDATED_HOLE_1)
            .hole2(UPDATED_HOLE_2)
            .hole3(UPDATED_HOLE_3)
            .hole4(UPDATED_HOLE_4)
            .hole5(UPDATED_HOLE_5)
            .hole6(UPDATED_HOLE_6)
            .hole7(UPDATED_HOLE_7)
            .hole8(UPDATED_HOLE_8)
            .hole9(UPDATED_HOLE_9)
            .hole10(UPDATED_HOLE_10)
            .hole11(UPDATED_HOLE_11)
            .hole12(UPDATED_HOLE_12)
            .hole13(UPDATED_HOLE_13)
            .hole14(UPDATED_HOLE_14)
            .hole15(UPDATED_HOLE_15)
            .hole16(UPDATED_HOLE_16)
            .hole17(UPDATED_HOLE_17)
            .hole18(UPDATED_HOLE_18);

        restDistanceOfApproachMockMvc.perform(put("/api/distance-of-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDistanceOfApproach)))
            .andExpect(status().isOk());

        // Validate the DistanceOfApproach in the database
        List<DistanceOfApproach> distanceOfApproachList = distanceOfApproachRepository.findAll();
        assertThat(distanceOfApproachList).hasSize(databaseSizeBeforeUpdate);
        DistanceOfApproach testDistanceOfApproach = distanceOfApproachList.get(distanceOfApproachList.size() - 1);
        assertThat(testDistanceOfApproach.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testDistanceOfApproach.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testDistanceOfApproach.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testDistanceOfApproach.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testDistanceOfApproach.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testDistanceOfApproach.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testDistanceOfApproach.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testDistanceOfApproach.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testDistanceOfApproach.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testDistanceOfApproach.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testDistanceOfApproach.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testDistanceOfApproach.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testDistanceOfApproach.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testDistanceOfApproach.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testDistanceOfApproach.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testDistanceOfApproach.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testDistanceOfApproach.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testDistanceOfApproach.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testDistanceOfApproach.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testDistanceOfApproach.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testDistanceOfApproach.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the DistanceOfApproach in Elasticsearch
        verify(mockDistanceOfApproachSearchRepository, times(1)).save(testDistanceOfApproach);
    }

    @Test
    @Transactional
    public void updateNonExistingDistanceOfApproach() throws Exception {
        int databaseSizeBeforeUpdate = distanceOfApproachRepository.findAll().size();

        // Create the DistanceOfApproach

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDistanceOfApproachMockMvc.perform(put("/api/distance-of-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(distanceOfApproach)))
            .andExpect(status().isBadRequest());

        // Validate the DistanceOfApproach in the database
        List<DistanceOfApproach> distanceOfApproachList = distanceOfApproachRepository.findAll();
        assertThat(distanceOfApproachList).hasSize(databaseSizeBeforeUpdate);

        // Validate the DistanceOfApproach in Elasticsearch
        verify(mockDistanceOfApproachSearchRepository, times(0)).save(distanceOfApproach);
    }

    @Test
    @Transactional
    public void deleteDistanceOfApproach() throws Exception {
        // Initialize the database
        distanceOfApproachRepository.saveAndFlush(distanceOfApproach);

        int databaseSizeBeforeDelete = distanceOfApproachRepository.findAll().size();

        // Delete the distanceOfApproach
        restDistanceOfApproachMockMvc.perform(delete("/api/distance-of-approaches/{id}", distanceOfApproach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DistanceOfApproach> distanceOfApproachList = distanceOfApproachRepository.findAll();
        assertThat(distanceOfApproachList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the DistanceOfApproach in Elasticsearch
        verify(mockDistanceOfApproachSearchRepository, times(1)).deleteById(distanceOfApproach.getId());
    }

    @Test
    @Transactional
    public void searchDistanceOfApproach() throws Exception {
        // Initialize the database
        distanceOfApproachRepository.saveAndFlush(distanceOfApproach);
        when(mockDistanceOfApproachSearchRepository.search(queryStringQuery("id:" + distanceOfApproach.getId())))
            .thenReturn(Collections.singletonList(distanceOfApproach));
        // Search the distanceOfApproach
        restDistanceOfApproachMockMvc.perform(get("/api/_search/distance-of-approaches?query=id:" + distanceOfApproach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(distanceOfApproach.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].hole1").value(hasItem(DEFAULT_HOLE_1)))
            .andExpect(jsonPath("$.[*].hole2").value(hasItem(DEFAULT_HOLE_2)))
            .andExpect(jsonPath("$.[*].hole3").value(hasItem(DEFAULT_HOLE_3)))
            .andExpect(jsonPath("$.[*].hole4").value(hasItem(DEFAULT_HOLE_4)))
            .andExpect(jsonPath("$.[*].hole5").value(hasItem(DEFAULT_HOLE_5)))
            .andExpect(jsonPath("$.[*].hole6").value(hasItem(DEFAULT_HOLE_6)))
            .andExpect(jsonPath("$.[*].hole7").value(hasItem(DEFAULT_HOLE_7)))
            .andExpect(jsonPath("$.[*].hole8").value(hasItem(DEFAULT_HOLE_8)))
            .andExpect(jsonPath("$.[*].hole9").value(hasItem(DEFAULT_HOLE_9)))
            .andExpect(jsonPath("$.[*].hole10").value(hasItem(DEFAULT_HOLE_10)))
            .andExpect(jsonPath("$.[*].hole11").value(hasItem(DEFAULT_HOLE_11)))
            .andExpect(jsonPath("$.[*].hole12").value(hasItem(DEFAULT_HOLE_12)))
            .andExpect(jsonPath("$.[*].hole13").value(hasItem(DEFAULT_HOLE_13)))
            .andExpect(jsonPath("$.[*].hole14").value(hasItem(DEFAULT_HOLE_14)))
            .andExpect(jsonPath("$.[*].hole15").value(hasItem(DEFAULT_HOLE_15)))
            .andExpect(jsonPath("$.[*].hole16").value(hasItem(DEFAULT_HOLE_16)))
            .andExpect(jsonPath("$.[*].hole17").value(hasItem(DEFAULT_HOLE_17)))
            .andExpect(jsonPath("$.[*].hole18").value(hasItem(DEFAULT_HOLE_18)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DistanceOfApproach.class);
        DistanceOfApproach distanceOfApproach1 = new DistanceOfApproach();
        distanceOfApproach1.setId(1L);
        DistanceOfApproach distanceOfApproach2 = new DistanceOfApproach();
        distanceOfApproach2.setId(distanceOfApproach1.getId());
        assertThat(distanceOfApproach1).isEqualTo(distanceOfApproach2);
        distanceOfApproach2.setId(2L);
        assertThat(distanceOfApproach1).isNotEqualTo(distanceOfApproach2);
        distanceOfApproach1.setId(null);
        assertThat(distanceOfApproach1).isNotEqualTo(distanceOfApproach2);
    }
}
