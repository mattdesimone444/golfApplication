package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.SGPutting;
import com.furious.golf.repository.SGPuttingRepository;
import com.furious.golf.repository.search.SGPuttingSearchRepository;
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
 * Integration tests for the {@link SGPuttingResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class SGPuttingResourceIT {

    private static final Long DEFAULT_PLAYER_ID = 1L;
    private static final Long UPDATED_PLAYER_ID = 2L;
    private static final Long SMALLER_PLAYER_ID = 1L - 1L;

    private static final Long DEFAULT_TOURNAMENT_ID = 1L;
    private static final Long UPDATED_TOURNAMENT_ID = 2L;
    private static final Long SMALLER_TOURNAMENT_ID = 1L - 1L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;
    private static final Long SMALLER_COURSE_ID = 1L - 1L;

    private static final Float DEFAULT_HOLE_1 = 1F;
    private static final Float UPDATED_HOLE_1 = 2F;
    private static final Float SMALLER_HOLE_1 = 1F - 1F;

    private static final Float DEFAULT_HOLE_2 = 1F;
    private static final Float UPDATED_HOLE_2 = 2F;
    private static final Float SMALLER_HOLE_2 = 1F - 1F;

    private static final Float DEFAULT_HOLE_3 = 1F;
    private static final Float UPDATED_HOLE_3 = 2F;
    private static final Float SMALLER_HOLE_3 = 1F - 1F;

    private static final Float DEFAULT_HOLE_4 = 1F;
    private static final Float UPDATED_HOLE_4 = 2F;
    private static final Float SMALLER_HOLE_4 = 1F - 1F;

    private static final Float DEFAULT_HOLE_5 = 1F;
    private static final Float UPDATED_HOLE_5 = 2F;
    private static final Float SMALLER_HOLE_5 = 1F - 1F;

    private static final Float DEFAULT_HOLE_6 = 1F;
    private static final Float UPDATED_HOLE_6 = 2F;
    private static final Float SMALLER_HOLE_6 = 1F - 1F;

    private static final Float DEFAULT_HOLE_7 = 1F;
    private static final Float UPDATED_HOLE_7 = 2F;
    private static final Float SMALLER_HOLE_7 = 1F - 1F;

    private static final Float DEFAULT_HOLE_8 = 1F;
    private static final Float UPDATED_HOLE_8 = 2F;
    private static final Float SMALLER_HOLE_8 = 1F - 1F;

    private static final Float DEFAULT_HOLE_9 = 1F;
    private static final Float UPDATED_HOLE_9 = 2F;
    private static final Float SMALLER_HOLE_9 = 1F - 1F;

    private static final Float DEFAULT_HOLE_10 = 1F;
    private static final Float UPDATED_HOLE_10 = 2F;
    private static final Float SMALLER_HOLE_10 = 1F - 1F;

    private static final Float DEFAULT_HOLE_11 = 1F;
    private static final Float UPDATED_HOLE_11 = 2F;
    private static final Float SMALLER_HOLE_11 = 1F - 1F;

    private static final Float DEFAULT_HOLE_12 = 1F;
    private static final Float UPDATED_HOLE_12 = 2F;
    private static final Float SMALLER_HOLE_12 = 1F - 1F;

    private static final Float DEFAULT_HOLE_13 = 1F;
    private static final Float UPDATED_HOLE_13 = 2F;
    private static final Float SMALLER_HOLE_13 = 1F - 1F;

    private static final Float DEFAULT_HOLE_14 = 1F;
    private static final Float UPDATED_HOLE_14 = 2F;
    private static final Float SMALLER_HOLE_14 = 1F - 1F;

    private static final Float DEFAULT_HOLE_15 = 1F;
    private static final Float UPDATED_HOLE_15 = 2F;
    private static final Float SMALLER_HOLE_15 = 1F - 1F;

    private static final Float DEFAULT_HOLE_16 = 1F;
    private static final Float UPDATED_HOLE_16 = 2F;
    private static final Float SMALLER_HOLE_16 = 1F - 1F;

    private static final Float DEFAULT_HOLE_17 = 1F;
    private static final Float UPDATED_HOLE_17 = 2F;
    private static final Float SMALLER_HOLE_17 = 1F - 1F;

    private static final Float DEFAULT_HOLE_18 = 1F;
    private static final Float UPDATED_HOLE_18 = 2F;
    private static final Float SMALLER_HOLE_18 = 1F - 1F;

    @Autowired
    private SGPuttingRepository sGPuttingRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.SGPuttingSearchRepositoryMockConfiguration
     */
    @Autowired
    private SGPuttingSearchRepository mockSGPuttingSearchRepository;

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

    private MockMvc restSGPuttingMockMvc;

    private SGPutting sGPutting;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SGPuttingResource sGPuttingResource = new SGPuttingResource(sGPuttingRepository, mockSGPuttingSearchRepository);
        this.restSGPuttingMockMvc = MockMvcBuilders.standaloneSetup(sGPuttingResource)
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
    public static SGPutting createEntity(EntityManager em) {
        SGPutting sGPutting = new SGPutting()
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
        return sGPutting;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SGPutting createUpdatedEntity(EntityManager em) {
        SGPutting sGPutting = new SGPutting()
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
        return sGPutting;
    }

    @BeforeEach
    public void initTest() {
        sGPutting = createEntity(em);
    }

    @Test
    @Transactional
    public void createSGPutting() throws Exception {
        int databaseSizeBeforeCreate = sGPuttingRepository.findAll().size();

        // Create the SGPutting
        restSGPuttingMockMvc.perform(post("/api/sg-puttings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGPutting)))
            .andExpect(status().isCreated());

        // Validate the SGPutting in the database
        List<SGPutting> sGPuttingList = sGPuttingRepository.findAll();
        assertThat(sGPuttingList).hasSize(databaseSizeBeforeCreate + 1);
        SGPutting testSGPutting = sGPuttingList.get(sGPuttingList.size() - 1);
        assertThat(testSGPutting.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testSGPutting.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testSGPutting.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testSGPutting.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testSGPutting.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testSGPutting.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testSGPutting.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testSGPutting.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testSGPutting.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testSGPutting.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testSGPutting.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testSGPutting.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testSGPutting.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testSGPutting.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testSGPutting.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testSGPutting.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testSGPutting.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testSGPutting.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testSGPutting.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testSGPutting.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testSGPutting.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the SGPutting in Elasticsearch
        verify(mockSGPuttingSearchRepository, times(1)).save(testSGPutting);
    }

    @Test
    @Transactional
    public void createSGPuttingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sGPuttingRepository.findAll().size();

        // Create the SGPutting with an existing ID
        sGPutting.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSGPuttingMockMvc.perform(post("/api/sg-puttings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGPutting)))
            .andExpect(status().isBadRequest());

        // Validate the SGPutting in the database
        List<SGPutting> sGPuttingList = sGPuttingRepository.findAll();
        assertThat(sGPuttingList).hasSize(databaseSizeBeforeCreate);

        // Validate the SGPutting in Elasticsearch
        verify(mockSGPuttingSearchRepository, times(0)).save(sGPutting);
    }


    @Test
    @Transactional
    public void getAllSGPuttings() throws Exception {
        // Initialize the database
        sGPuttingRepository.saveAndFlush(sGPutting);

        // Get all the sGPuttingList
        restSGPuttingMockMvc.perform(get("/api/sg-puttings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGPutting.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].hole1").value(hasItem(DEFAULT_HOLE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].hole2").value(hasItem(DEFAULT_HOLE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].hole3").value(hasItem(DEFAULT_HOLE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].hole4").value(hasItem(DEFAULT_HOLE_4.doubleValue())))
            .andExpect(jsonPath("$.[*].hole5").value(hasItem(DEFAULT_HOLE_5.doubleValue())))
            .andExpect(jsonPath("$.[*].hole6").value(hasItem(DEFAULT_HOLE_6.doubleValue())))
            .andExpect(jsonPath("$.[*].hole7").value(hasItem(DEFAULT_HOLE_7.doubleValue())))
            .andExpect(jsonPath("$.[*].hole8").value(hasItem(DEFAULT_HOLE_8.doubleValue())))
            .andExpect(jsonPath("$.[*].hole9").value(hasItem(DEFAULT_HOLE_9.doubleValue())))
            .andExpect(jsonPath("$.[*].hole10").value(hasItem(DEFAULT_HOLE_10.doubleValue())))
            .andExpect(jsonPath("$.[*].hole11").value(hasItem(DEFAULT_HOLE_11.doubleValue())))
            .andExpect(jsonPath("$.[*].hole12").value(hasItem(DEFAULT_HOLE_12.doubleValue())))
            .andExpect(jsonPath("$.[*].hole13").value(hasItem(DEFAULT_HOLE_13.doubleValue())))
            .andExpect(jsonPath("$.[*].hole14").value(hasItem(DEFAULT_HOLE_14.doubleValue())))
            .andExpect(jsonPath("$.[*].hole15").value(hasItem(DEFAULT_HOLE_15.doubleValue())))
            .andExpect(jsonPath("$.[*].hole16").value(hasItem(DEFAULT_HOLE_16.doubleValue())))
            .andExpect(jsonPath("$.[*].hole17").value(hasItem(DEFAULT_HOLE_17.doubleValue())))
            .andExpect(jsonPath("$.[*].hole18").value(hasItem(DEFAULT_HOLE_18.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getSGPutting() throws Exception {
        // Initialize the database
        sGPuttingRepository.saveAndFlush(sGPutting);

        // Get the sGPutting
        restSGPuttingMockMvc.perform(get("/api/sg-puttings/{id}", sGPutting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sGPutting.getId().intValue()))
            .andExpect(jsonPath("$.playerId").value(DEFAULT_PLAYER_ID.intValue()))
            .andExpect(jsonPath("$.tournamentId").value(DEFAULT_TOURNAMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.hole1").value(DEFAULT_HOLE_1.doubleValue()))
            .andExpect(jsonPath("$.hole2").value(DEFAULT_HOLE_2.doubleValue()))
            .andExpect(jsonPath("$.hole3").value(DEFAULT_HOLE_3.doubleValue()))
            .andExpect(jsonPath("$.hole4").value(DEFAULT_HOLE_4.doubleValue()))
            .andExpect(jsonPath("$.hole5").value(DEFAULT_HOLE_5.doubleValue()))
            .andExpect(jsonPath("$.hole6").value(DEFAULT_HOLE_6.doubleValue()))
            .andExpect(jsonPath("$.hole7").value(DEFAULT_HOLE_7.doubleValue()))
            .andExpect(jsonPath("$.hole8").value(DEFAULT_HOLE_8.doubleValue()))
            .andExpect(jsonPath("$.hole9").value(DEFAULT_HOLE_9.doubleValue()))
            .andExpect(jsonPath("$.hole10").value(DEFAULT_HOLE_10.doubleValue()))
            .andExpect(jsonPath("$.hole11").value(DEFAULT_HOLE_11.doubleValue()))
            .andExpect(jsonPath("$.hole12").value(DEFAULT_HOLE_12.doubleValue()))
            .andExpect(jsonPath("$.hole13").value(DEFAULT_HOLE_13.doubleValue()))
            .andExpect(jsonPath("$.hole14").value(DEFAULT_HOLE_14.doubleValue()))
            .andExpect(jsonPath("$.hole15").value(DEFAULT_HOLE_15.doubleValue()))
            .andExpect(jsonPath("$.hole16").value(DEFAULT_HOLE_16.doubleValue()))
            .andExpect(jsonPath("$.hole17").value(DEFAULT_HOLE_17.doubleValue()))
            .andExpect(jsonPath("$.hole18").value(DEFAULT_HOLE_18.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSGPutting() throws Exception {
        // Get the sGPutting
        restSGPuttingMockMvc.perform(get("/api/sg-puttings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSGPutting() throws Exception {
        // Initialize the database
        sGPuttingRepository.saveAndFlush(sGPutting);

        int databaseSizeBeforeUpdate = sGPuttingRepository.findAll().size();

        // Update the sGPutting
        SGPutting updatedSGPutting = sGPuttingRepository.findById(sGPutting.getId()).get();
        // Disconnect from session so that the updates on updatedSGPutting are not directly saved in db
        em.detach(updatedSGPutting);
        updatedSGPutting
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

        restSGPuttingMockMvc.perform(put("/api/sg-puttings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSGPutting)))
            .andExpect(status().isOk());

        // Validate the SGPutting in the database
        List<SGPutting> sGPuttingList = sGPuttingRepository.findAll();
        assertThat(sGPuttingList).hasSize(databaseSizeBeforeUpdate);
        SGPutting testSGPutting = sGPuttingList.get(sGPuttingList.size() - 1);
        assertThat(testSGPutting.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testSGPutting.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testSGPutting.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testSGPutting.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testSGPutting.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testSGPutting.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testSGPutting.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testSGPutting.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testSGPutting.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testSGPutting.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testSGPutting.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testSGPutting.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testSGPutting.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testSGPutting.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testSGPutting.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testSGPutting.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testSGPutting.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testSGPutting.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testSGPutting.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testSGPutting.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testSGPutting.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the SGPutting in Elasticsearch
        verify(mockSGPuttingSearchRepository, times(1)).save(testSGPutting);
    }

    @Test
    @Transactional
    public void updateNonExistingSGPutting() throws Exception {
        int databaseSizeBeforeUpdate = sGPuttingRepository.findAll().size();

        // Create the SGPutting

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSGPuttingMockMvc.perform(put("/api/sg-puttings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGPutting)))
            .andExpect(status().isBadRequest());

        // Validate the SGPutting in the database
        List<SGPutting> sGPuttingList = sGPuttingRepository.findAll();
        assertThat(sGPuttingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SGPutting in Elasticsearch
        verify(mockSGPuttingSearchRepository, times(0)).save(sGPutting);
    }

    @Test
    @Transactional
    public void deleteSGPutting() throws Exception {
        // Initialize the database
        sGPuttingRepository.saveAndFlush(sGPutting);

        int databaseSizeBeforeDelete = sGPuttingRepository.findAll().size();

        // Delete the sGPutting
        restSGPuttingMockMvc.perform(delete("/api/sg-puttings/{id}", sGPutting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SGPutting> sGPuttingList = sGPuttingRepository.findAll();
        assertThat(sGPuttingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SGPutting in Elasticsearch
        verify(mockSGPuttingSearchRepository, times(1)).deleteById(sGPutting.getId());
    }

    @Test
    @Transactional
    public void searchSGPutting() throws Exception {
        // Initialize the database
        sGPuttingRepository.saveAndFlush(sGPutting);
        when(mockSGPuttingSearchRepository.search(queryStringQuery("id:" + sGPutting.getId())))
            .thenReturn(Collections.singletonList(sGPutting));
        // Search the sGPutting
        restSGPuttingMockMvc.perform(get("/api/_search/sg-puttings?query=id:" + sGPutting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGPutting.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].hole1").value(hasItem(DEFAULT_HOLE_1.doubleValue())))
            .andExpect(jsonPath("$.[*].hole2").value(hasItem(DEFAULT_HOLE_2.doubleValue())))
            .andExpect(jsonPath("$.[*].hole3").value(hasItem(DEFAULT_HOLE_3.doubleValue())))
            .andExpect(jsonPath("$.[*].hole4").value(hasItem(DEFAULT_HOLE_4.doubleValue())))
            .andExpect(jsonPath("$.[*].hole5").value(hasItem(DEFAULT_HOLE_5.doubleValue())))
            .andExpect(jsonPath("$.[*].hole6").value(hasItem(DEFAULT_HOLE_6.doubleValue())))
            .andExpect(jsonPath("$.[*].hole7").value(hasItem(DEFAULT_HOLE_7.doubleValue())))
            .andExpect(jsonPath("$.[*].hole8").value(hasItem(DEFAULT_HOLE_8.doubleValue())))
            .andExpect(jsonPath("$.[*].hole9").value(hasItem(DEFAULT_HOLE_9.doubleValue())))
            .andExpect(jsonPath("$.[*].hole10").value(hasItem(DEFAULT_HOLE_10.doubleValue())))
            .andExpect(jsonPath("$.[*].hole11").value(hasItem(DEFAULT_HOLE_11.doubleValue())))
            .andExpect(jsonPath("$.[*].hole12").value(hasItem(DEFAULT_HOLE_12.doubleValue())))
            .andExpect(jsonPath("$.[*].hole13").value(hasItem(DEFAULT_HOLE_13.doubleValue())))
            .andExpect(jsonPath("$.[*].hole14").value(hasItem(DEFAULT_HOLE_14.doubleValue())))
            .andExpect(jsonPath("$.[*].hole15").value(hasItem(DEFAULT_HOLE_15.doubleValue())))
            .andExpect(jsonPath("$.[*].hole16").value(hasItem(DEFAULT_HOLE_16.doubleValue())))
            .andExpect(jsonPath("$.[*].hole17").value(hasItem(DEFAULT_HOLE_17.doubleValue())))
            .andExpect(jsonPath("$.[*].hole18").value(hasItem(DEFAULT_HOLE_18.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SGPutting.class);
        SGPutting sGPutting1 = new SGPutting();
        sGPutting1.setId(1L);
        SGPutting sGPutting2 = new SGPutting();
        sGPutting2.setId(sGPutting1.getId());
        assertThat(sGPutting1).isEqualTo(sGPutting2);
        sGPutting2.setId(2L);
        assertThat(sGPutting1).isNotEqualTo(sGPutting2);
        sGPutting1.setId(null);
        assertThat(sGPutting1).isNotEqualTo(sGPutting2);
    }
}
