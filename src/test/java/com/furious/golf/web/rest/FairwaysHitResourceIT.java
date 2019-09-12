package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.FairwaysHit;
import com.furious.golf.repository.FairwaysHitRepository;
import com.furious.golf.repository.search.FairwaysHitSearchRepository;
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
 * Integration tests for the {@link FairwaysHitResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class FairwaysHitResourceIT {

    private static final Long DEFAULT_PLAYER_ID = 1L;
    private static final Long UPDATED_PLAYER_ID = 2L;
    private static final Long SMALLER_PLAYER_ID = 1L - 1L;

    private static final Long DEFAULT_TOURNAMENT_ID = 1L;
    private static final Long UPDATED_TOURNAMENT_ID = 2L;
    private static final Long SMALLER_TOURNAMENT_ID = 1L - 1L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;
    private static final Long SMALLER_COURSE_ID = 1L - 1L;

    private static final String DEFAULT_HOLE_1 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_2 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_3 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_4 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_5 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_5 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_6 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_6 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_7 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_7 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_8 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_8 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_9 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_9 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_10 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_10 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_11 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_11 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_12 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_12 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_13 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_13 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_14 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_14 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_15 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_15 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_16 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_16 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_17 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_17 = "BBBBBBBBBB";

    private static final String DEFAULT_HOLE_18 = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_18 = "BBBBBBBBBB";

    @Autowired
    private FairwaysHitRepository fairwaysHitRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.FairwaysHitSearchRepositoryMockConfiguration
     */
    @Autowired
    private FairwaysHitSearchRepository mockFairwaysHitSearchRepository;

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

    private MockMvc restFairwaysHitMockMvc;

    private FairwaysHit fairwaysHit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FairwaysHitResource fairwaysHitResource = new FairwaysHitResource(fairwaysHitRepository, mockFairwaysHitSearchRepository);
        this.restFairwaysHitMockMvc = MockMvcBuilders.standaloneSetup(fairwaysHitResource)
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
    public static FairwaysHit createEntity(EntityManager em) {
        FairwaysHit fairwaysHit = new FairwaysHit()
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
        return fairwaysHit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FairwaysHit createUpdatedEntity(EntityManager em) {
        FairwaysHit fairwaysHit = new FairwaysHit()
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
        return fairwaysHit;
    }

    @BeforeEach
    public void initTest() {
        fairwaysHit = createEntity(em);
    }

    @Test
    @Transactional
    public void createFairwaysHit() throws Exception {
        int databaseSizeBeforeCreate = fairwaysHitRepository.findAll().size();

        // Create the FairwaysHit
        restFairwaysHitMockMvc.perform(post("/api/fairways-hits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fairwaysHit)))
            .andExpect(status().isCreated());

        // Validate the FairwaysHit in the database
        List<FairwaysHit> fairwaysHitList = fairwaysHitRepository.findAll();
        assertThat(fairwaysHitList).hasSize(databaseSizeBeforeCreate + 1);
        FairwaysHit testFairwaysHit = fairwaysHitList.get(fairwaysHitList.size() - 1);
        assertThat(testFairwaysHit.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testFairwaysHit.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testFairwaysHit.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testFairwaysHit.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testFairwaysHit.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testFairwaysHit.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testFairwaysHit.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testFairwaysHit.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testFairwaysHit.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testFairwaysHit.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testFairwaysHit.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testFairwaysHit.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testFairwaysHit.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testFairwaysHit.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testFairwaysHit.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testFairwaysHit.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testFairwaysHit.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testFairwaysHit.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testFairwaysHit.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testFairwaysHit.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testFairwaysHit.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the FairwaysHit in Elasticsearch
        verify(mockFairwaysHitSearchRepository, times(1)).save(testFairwaysHit);
    }

    @Test
    @Transactional
    public void createFairwaysHitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fairwaysHitRepository.findAll().size();

        // Create the FairwaysHit with an existing ID
        fairwaysHit.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFairwaysHitMockMvc.perform(post("/api/fairways-hits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fairwaysHit)))
            .andExpect(status().isBadRequest());

        // Validate the FairwaysHit in the database
        List<FairwaysHit> fairwaysHitList = fairwaysHitRepository.findAll();
        assertThat(fairwaysHitList).hasSize(databaseSizeBeforeCreate);

        // Validate the FairwaysHit in Elasticsearch
        verify(mockFairwaysHitSearchRepository, times(0)).save(fairwaysHit);
    }


    @Test
    @Transactional
    public void getAllFairwaysHits() throws Exception {
        // Initialize the database
        fairwaysHitRepository.saveAndFlush(fairwaysHit);

        // Get all the fairwaysHitList
        restFairwaysHitMockMvc.perform(get("/api/fairways-hits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fairwaysHit.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].hole1").value(hasItem(DEFAULT_HOLE_1.toString())))
            .andExpect(jsonPath("$.[*].hole2").value(hasItem(DEFAULT_HOLE_2.toString())))
            .andExpect(jsonPath("$.[*].hole3").value(hasItem(DEFAULT_HOLE_3.toString())))
            .andExpect(jsonPath("$.[*].hole4").value(hasItem(DEFAULT_HOLE_4.toString())))
            .andExpect(jsonPath("$.[*].hole5").value(hasItem(DEFAULT_HOLE_5.toString())))
            .andExpect(jsonPath("$.[*].hole6").value(hasItem(DEFAULT_HOLE_6.toString())))
            .andExpect(jsonPath("$.[*].hole7").value(hasItem(DEFAULT_HOLE_7.toString())))
            .andExpect(jsonPath("$.[*].hole8").value(hasItem(DEFAULT_HOLE_8.toString())))
            .andExpect(jsonPath("$.[*].hole9").value(hasItem(DEFAULT_HOLE_9.toString())))
            .andExpect(jsonPath("$.[*].hole10").value(hasItem(DEFAULT_HOLE_10.toString())))
            .andExpect(jsonPath("$.[*].hole11").value(hasItem(DEFAULT_HOLE_11.toString())))
            .andExpect(jsonPath("$.[*].hole12").value(hasItem(DEFAULT_HOLE_12.toString())))
            .andExpect(jsonPath("$.[*].hole13").value(hasItem(DEFAULT_HOLE_13.toString())))
            .andExpect(jsonPath("$.[*].hole14").value(hasItem(DEFAULT_HOLE_14.toString())))
            .andExpect(jsonPath("$.[*].hole15").value(hasItem(DEFAULT_HOLE_15.toString())))
            .andExpect(jsonPath("$.[*].hole16").value(hasItem(DEFAULT_HOLE_16.toString())))
            .andExpect(jsonPath("$.[*].hole17").value(hasItem(DEFAULT_HOLE_17.toString())))
            .andExpect(jsonPath("$.[*].hole18").value(hasItem(DEFAULT_HOLE_18.toString())));
    }
    
    @Test
    @Transactional
    public void getFairwaysHit() throws Exception {
        // Initialize the database
        fairwaysHitRepository.saveAndFlush(fairwaysHit);

        // Get the fairwaysHit
        restFairwaysHitMockMvc.perform(get("/api/fairways-hits/{id}", fairwaysHit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fairwaysHit.getId().intValue()))
            .andExpect(jsonPath("$.playerId").value(DEFAULT_PLAYER_ID.intValue()))
            .andExpect(jsonPath("$.tournamentId").value(DEFAULT_TOURNAMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.hole1").value(DEFAULT_HOLE_1.toString()))
            .andExpect(jsonPath("$.hole2").value(DEFAULT_HOLE_2.toString()))
            .andExpect(jsonPath("$.hole3").value(DEFAULT_HOLE_3.toString()))
            .andExpect(jsonPath("$.hole4").value(DEFAULT_HOLE_4.toString()))
            .andExpect(jsonPath("$.hole5").value(DEFAULT_HOLE_5.toString()))
            .andExpect(jsonPath("$.hole6").value(DEFAULT_HOLE_6.toString()))
            .andExpect(jsonPath("$.hole7").value(DEFAULT_HOLE_7.toString()))
            .andExpect(jsonPath("$.hole8").value(DEFAULT_HOLE_8.toString()))
            .andExpect(jsonPath("$.hole9").value(DEFAULT_HOLE_9.toString()))
            .andExpect(jsonPath("$.hole10").value(DEFAULT_HOLE_10.toString()))
            .andExpect(jsonPath("$.hole11").value(DEFAULT_HOLE_11.toString()))
            .andExpect(jsonPath("$.hole12").value(DEFAULT_HOLE_12.toString()))
            .andExpect(jsonPath("$.hole13").value(DEFAULT_HOLE_13.toString()))
            .andExpect(jsonPath("$.hole14").value(DEFAULT_HOLE_14.toString()))
            .andExpect(jsonPath("$.hole15").value(DEFAULT_HOLE_15.toString()))
            .andExpect(jsonPath("$.hole16").value(DEFAULT_HOLE_16.toString()))
            .andExpect(jsonPath("$.hole17").value(DEFAULT_HOLE_17.toString()))
            .andExpect(jsonPath("$.hole18").value(DEFAULT_HOLE_18.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFairwaysHit() throws Exception {
        // Get the fairwaysHit
        restFairwaysHitMockMvc.perform(get("/api/fairways-hits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFairwaysHit() throws Exception {
        // Initialize the database
        fairwaysHitRepository.saveAndFlush(fairwaysHit);

        int databaseSizeBeforeUpdate = fairwaysHitRepository.findAll().size();

        // Update the fairwaysHit
        FairwaysHit updatedFairwaysHit = fairwaysHitRepository.findById(fairwaysHit.getId()).get();
        // Disconnect from session so that the updates on updatedFairwaysHit are not directly saved in db
        em.detach(updatedFairwaysHit);
        updatedFairwaysHit
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

        restFairwaysHitMockMvc.perform(put("/api/fairways-hits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFairwaysHit)))
            .andExpect(status().isOk());

        // Validate the FairwaysHit in the database
        List<FairwaysHit> fairwaysHitList = fairwaysHitRepository.findAll();
        assertThat(fairwaysHitList).hasSize(databaseSizeBeforeUpdate);
        FairwaysHit testFairwaysHit = fairwaysHitList.get(fairwaysHitList.size() - 1);
        assertThat(testFairwaysHit.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testFairwaysHit.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testFairwaysHit.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testFairwaysHit.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testFairwaysHit.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testFairwaysHit.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testFairwaysHit.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testFairwaysHit.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testFairwaysHit.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testFairwaysHit.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testFairwaysHit.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testFairwaysHit.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testFairwaysHit.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testFairwaysHit.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testFairwaysHit.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testFairwaysHit.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testFairwaysHit.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testFairwaysHit.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testFairwaysHit.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testFairwaysHit.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testFairwaysHit.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the FairwaysHit in Elasticsearch
        verify(mockFairwaysHitSearchRepository, times(1)).save(testFairwaysHit);
    }

    @Test
    @Transactional
    public void updateNonExistingFairwaysHit() throws Exception {
        int databaseSizeBeforeUpdate = fairwaysHitRepository.findAll().size();

        // Create the FairwaysHit

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFairwaysHitMockMvc.perform(put("/api/fairways-hits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fairwaysHit)))
            .andExpect(status().isBadRequest());

        // Validate the FairwaysHit in the database
        List<FairwaysHit> fairwaysHitList = fairwaysHitRepository.findAll();
        assertThat(fairwaysHitList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FairwaysHit in Elasticsearch
        verify(mockFairwaysHitSearchRepository, times(0)).save(fairwaysHit);
    }

    @Test
    @Transactional
    public void deleteFairwaysHit() throws Exception {
        // Initialize the database
        fairwaysHitRepository.saveAndFlush(fairwaysHit);

        int databaseSizeBeforeDelete = fairwaysHitRepository.findAll().size();

        // Delete the fairwaysHit
        restFairwaysHitMockMvc.perform(delete("/api/fairways-hits/{id}", fairwaysHit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FairwaysHit> fairwaysHitList = fairwaysHitRepository.findAll();
        assertThat(fairwaysHitList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FairwaysHit in Elasticsearch
        verify(mockFairwaysHitSearchRepository, times(1)).deleteById(fairwaysHit.getId());
    }

    @Test
    @Transactional
    public void searchFairwaysHit() throws Exception {
        // Initialize the database
        fairwaysHitRepository.saveAndFlush(fairwaysHit);
        when(mockFairwaysHitSearchRepository.search(queryStringQuery("id:" + fairwaysHit.getId())))
            .thenReturn(Collections.singletonList(fairwaysHit));
        // Search the fairwaysHit
        restFairwaysHitMockMvc.perform(get("/api/_search/fairways-hits?query=id:" + fairwaysHit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fairwaysHit.getId().intValue())))
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
        TestUtil.equalsVerifier(FairwaysHit.class);
        FairwaysHit fairwaysHit1 = new FairwaysHit();
        fairwaysHit1.setId(1L);
        FairwaysHit fairwaysHit2 = new FairwaysHit();
        fairwaysHit2.setId(fairwaysHit1.getId());
        assertThat(fairwaysHit1).isEqualTo(fairwaysHit2);
        fairwaysHit2.setId(2L);
        assertThat(fairwaysHit1).isNotEqualTo(fairwaysHit2);
        fairwaysHit1.setId(null);
        assertThat(fairwaysHit1).isNotEqualTo(fairwaysHit2);
    }
}
