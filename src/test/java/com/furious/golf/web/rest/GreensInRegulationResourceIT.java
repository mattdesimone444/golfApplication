package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.GreensInRegulation;
import com.furious.golf.repository.GreensInRegulationRepository;
import com.furious.golf.repository.search.GreensInRegulationSearchRepository;
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
 * Integration tests for the {@link GreensInRegulationResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class GreensInRegulationResourceIT {

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
    private GreensInRegulationRepository greensInRegulationRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.GreensInRegulationSearchRepositoryMockConfiguration
     */
    @Autowired
    private GreensInRegulationSearchRepository mockGreensInRegulationSearchRepository;

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

    private MockMvc restGreensInRegulationMockMvc;

    private GreensInRegulation greensInRegulation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GreensInRegulationResource greensInRegulationResource = new GreensInRegulationResource(greensInRegulationRepository, mockGreensInRegulationSearchRepository);
        this.restGreensInRegulationMockMvc = MockMvcBuilders.standaloneSetup(greensInRegulationResource)
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
    public static GreensInRegulation createEntity(EntityManager em) {
        GreensInRegulation greensInRegulation = new GreensInRegulation()
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
        return greensInRegulation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GreensInRegulation createUpdatedEntity(EntityManager em) {
        GreensInRegulation greensInRegulation = new GreensInRegulation()
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
        return greensInRegulation;
    }

    @BeforeEach
    public void initTest() {
        greensInRegulation = createEntity(em);
    }

    @Test
    @Transactional
    public void createGreensInRegulation() throws Exception {
        int databaseSizeBeforeCreate = greensInRegulationRepository.findAll().size();

        // Create the GreensInRegulation
        restGreensInRegulationMockMvc.perform(post("/api/greens-in-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(greensInRegulation)))
            .andExpect(status().isCreated());

        // Validate the GreensInRegulation in the database
        List<GreensInRegulation> greensInRegulationList = greensInRegulationRepository.findAll();
        assertThat(greensInRegulationList).hasSize(databaseSizeBeforeCreate + 1);
        GreensInRegulation testGreensInRegulation = greensInRegulationList.get(greensInRegulationList.size() - 1);
        assertThat(testGreensInRegulation.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testGreensInRegulation.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testGreensInRegulation.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testGreensInRegulation.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testGreensInRegulation.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testGreensInRegulation.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testGreensInRegulation.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testGreensInRegulation.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testGreensInRegulation.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testGreensInRegulation.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testGreensInRegulation.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testGreensInRegulation.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testGreensInRegulation.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testGreensInRegulation.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testGreensInRegulation.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testGreensInRegulation.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testGreensInRegulation.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testGreensInRegulation.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testGreensInRegulation.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testGreensInRegulation.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testGreensInRegulation.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the GreensInRegulation in Elasticsearch
        verify(mockGreensInRegulationSearchRepository, times(1)).save(testGreensInRegulation);
    }

    @Test
    @Transactional
    public void createGreensInRegulationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = greensInRegulationRepository.findAll().size();

        // Create the GreensInRegulation with an existing ID
        greensInRegulation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGreensInRegulationMockMvc.perform(post("/api/greens-in-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(greensInRegulation)))
            .andExpect(status().isBadRequest());

        // Validate the GreensInRegulation in the database
        List<GreensInRegulation> greensInRegulationList = greensInRegulationRepository.findAll();
        assertThat(greensInRegulationList).hasSize(databaseSizeBeforeCreate);

        // Validate the GreensInRegulation in Elasticsearch
        verify(mockGreensInRegulationSearchRepository, times(0)).save(greensInRegulation);
    }


    @Test
    @Transactional
    public void getAllGreensInRegulations() throws Exception {
        // Initialize the database
        greensInRegulationRepository.saveAndFlush(greensInRegulation);

        // Get all the greensInRegulationList
        restGreensInRegulationMockMvc.perform(get("/api/greens-in-regulations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(greensInRegulation.getId().intValue())))
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
    public void getGreensInRegulation() throws Exception {
        // Initialize the database
        greensInRegulationRepository.saveAndFlush(greensInRegulation);

        // Get the greensInRegulation
        restGreensInRegulationMockMvc.perform(get("/api/greens-in-regulations/{id}", greensInRegulation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(greensInRegulation.getId().intValue()))
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
    public void getNonExistingGreensInRegulation() throws Exception {
        // Get the greensInRegulation
        restGreensInRegulationMockMvc.perform(get("/api/greens-in-regulations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGreensInRegulation() throws Exception {
        // Initialize the database
        greensInRegulationRepository.saveAndFlush(greensInRegulation);

        int databaseSizeBeforeUpdate = greensInRegulationRepository.findAll().size();

        // Update the greensInRegulation
        GreensInRegulation updatedGreensInRegulation = greensInRegulationRepository.findById(greensInRegulation.getId()).get();
        // Disconnect from session so that the updates on updatedGreensInRegulation are not directly saved in db
        em.detach(updatedGreensInRegulation);
        updatedGreensInRegulation
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

        restGreensInRegulationMockMvc.perform(put("/api/greens-in-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGreensInRegulation)))
            .andExpect(status().isOk());

        // Validate the GreensInRegulation in the database
        List<GreensInRegulation> greensInRegulationList = greensInRegulationRepository.findAll();
        assertThat(greensInRegulationList).hasSize(databaseSizeBeforeUpdate);
        GreensInRegulation testGreensInRegulation = greensInRegulationList.get(greensInRegulationList.size() - 1);
        assertThat(testGreensInRegulation.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testGreensInRegulation.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testGreensInRegulation.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testGreensInRegulation.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testGreensInRegulation.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testGreensInRegulation.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testGreensInRegulation.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testGreensInRegulation.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testGreensInRegulation.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testGreensInRegulation.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testGreensInRegulation.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testGreensInRegulation.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testGreensInRegulation.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testGreensInRegulation.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testGreensInRegulation.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testGreensInRegulation.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testGreensInRegulation.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testGreensInRegulation.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testGreensInRegulation.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testGreensInRegulation.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testGreensInRegulation.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the GreensInRegulation in Elasticsearch
        verify(mockGreensInRegulationSearchRepository, times(1)).save(testGreensInRegulation);
    }

    @Test
    @Transactional
    public void updateNonExistingGreensInRegulation() throws Exception {
        int databaseSizeBeforeUpdate = greensInRegulationRepository.findAll().size();

        // Create the GreensInRegulation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGreensInRegulationMockMvc.perform(put("/api/greens-in-regulations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(greensInRegulation)))
            .andExpect(status().isBadRequest());

        // Validate the GreensInRegulation in the database
        List<GreensInRegulation> greensInRegulationList = greensInRegulationRepository.findAll();
        assertThat(greensInRegulationList).hasSize(databaseSizeBeforeUpdate);

        // Validate the GreensInRegulation in Elasticsearch
        verify(mockGreensInRegulationSearchRepository, times(0)).save(greensInRegulation);
    }

    @Test
    @Transactional
    public void deleteGreensInRegulation() throws Exception {
        // Initialize the database
        greensInRegulationRepository.saveAndFlush(greensInRegulation);

        int databaseSizeBeforeDelete = greensInRegulationRepository.findAll().size();

        // Delete the greensInRegulation
        restGreensInRegulationMockMvc.perform(delete("/api/greens-in-regulations/{id}", greensInRegulation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GreensInRegulation> greensInRegulationList = greensInRegulationRepository.findAll();
        assertThat(greensInRegulationList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the GreensInRegulation in Elasticsearch
        verify(mockGreensInRegulationSearchRepository, times(1)).deleteById(greensInRegulation.getId());
    }

    @Test
    @Transactional
    public void searchGreensInRegulation() throws Exception {
        // Initialize the database
        greensInRegulationRepository.saveAndFlush(greensInRegulation);
        when(mockGreensInRegulationSearchRepository.search(queryStringQuery("id:" + greensInRegulation.getId())))
            .thenReturn(Collections.singletonList(greensInRegulation));
        // Search the greensInRegulation
        restGreensInRegulationMockMvc.perform(get("/api/_search/greens-in-regulations?query=id:" + greensInRegulation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(greensInRegulation.getId().intValue())))
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
        TestUtil.equalsVerifier(GreensInRegulation.class);
        GreensInRegulation greensInRegulation1 = new GreensInRegulation();
        greensInRegulation1.setId(1L);
        GreensInRegulation greensInRegulation2 = new GreensInRegulation();
        greensInRegulation2.setId(greensInRegulation1.getId());
        assertThat(greensInRegulation1).isEqualTo(greensInRegulation2);
        greensInRegulation2.setId(2L);
        assertThat(greensInRegulation1).isNotEqualTo(greensInRegulation2);
        greensInRegulation1.setId(null);
        assertThat(greensInRegulation1).isNotEqualTo(greensInRegulation2);
    }
}
