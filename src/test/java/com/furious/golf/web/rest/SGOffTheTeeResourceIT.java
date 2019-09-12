package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.SGOffTheTee;
import com.furious.golf.repository.SGOffTheTeeRepository;
import com.furious.golf.repository.search.SGOffTheTeeSearchRepository;
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
 * Integration tests for the {@link SGOffTheTeeResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class SGOffTheTeeResourceIT {

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
    private SGOffTheTeeRepository sGOffTheTeeRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.SGOffTheTeeSearchRepositoryMockConfiguration
     */
    @Autowired
    private SGOffTheTeeSearchRepository mockSGOffTheTeeSearchRepository;

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

    private MockMvc restSGOffTheTeeMockMvc;

    private SGOffTheTee sGOffTheTee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SGOffTheTeeResource sGOffTheTeeResource = new SGOffTheTeeResource(sGOffTheTeeRepository, mockSGOffTheTeeSearchRepository);
        this.restSGOffTheTeeMockMvc = MockMvcBuilders.standaloneSetup(sGOffTheTeeResource)
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
    public static SGOffTheTee createEntity(EntityManager em) {
        SGOffTheTee sGOffTheTee = new SGOffTheTee()
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
        return sGOffTheTee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SGOffTheTee createUpdatedEntity(EntityManager em) {
        SGOffTheTee sGOffTheTee = new SGOffTheTee()
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
        return sGOffTheTee;
    }

    @BeforeEach
    public void initTest() {
        sGOffTheTee = createEntity(em);
    }

    @Test
    @Transactional
    public void createSGOffTheTee() throws Exception {
        int databaseSizeBeforeCreate = sGOffTheTeeRepository.findAll().size();

        // Create the SGOffTheTee
        restSGOffTheTeeMockMvc.perform(post("/api/sg-off-the-tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGOffTheTee)))
            .andExpect(status().isCreated());

        // Validate the SGOffTheTee in the database
        List<SGOffTheTee> sGOffTheTeeList = sGOffTheTeeRepository.findAll();
        assertThat(sGOffTheTeeList).hasSize(databaseSizeBeforeCreate + 1);
        SGOffTheTee testSGOffTheTee = sGOffTheTeeList.get(sGOffTheTeeList.size() - 1);
        assertThat(testSGOffTheTee.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testSGOffTheTee.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testSGOffTheTee.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testSGOffTheTee.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testSGOffTheTee.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testSGOffTheTee.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testSGOffTheTee.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testSGOffTheTee.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testSGOffTheTee.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testSGOffTheTee.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testSGOffTheTee.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testSGOffTheTee.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testSGOffTheTee.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testSGOffTheTee.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testSGOffTheTee.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testSGOffTheTee.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testSGOffTheTee.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testSGOffTheTee.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testSGOffTheTee.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testSGOffTheTee.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testSGOffTheTee.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the SGOffTheTee in Elasticsearch
        verify(mockSGOffTheTeeSearchRepository, times(1)).save(testSGOffTheTee);
    }

    @Test
    @Transactional
    public void createSGOffTheTeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sGOffTheTeeRepository.findAll().size();

        // Create the SGOffTheTee with an existing ID
        sGOffTheTee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSGOffTheTeeMockMvc.perform(post("/api/sg-off-the-tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGOffTheTee)))
            .andExpect(status().isBadRequest());

        // Validate the SGOffTheTee in the database
        List<SGOffTheTee> sGOffTheTeeList = sGOffTheTeeRepository.findAll();
        assertThat(sGOffTheTeeList).hasSize(databaseSizeBeforeCreate);

        // Validate the SGOffTheTee in Elasticsearch
        verify(mockSGOffTheTeeSearchRepository, times(0)).save(sGOffTheTee);
    }


    @Test
    @Transactional
    public void getAllSGOffTheTees() throws Exception {
        // Initialize the database
        sGOffTheTeeRepository.saveAndFlush(sGOffTheTee);

        // Get all the sGOffTheTeeList
        restSGOffTheTeeMockMvc.perform(get("/api/sg-off-the-tees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGOffTheTee.getId().intValue())))
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
    public void getSGOffTheTee() throws Exception {
        // Initialize the database
        sGOffTheTeeRepository.saveAndFlush(sGOffTheTee);

        // Get the sGOffTheTee
        restSGOffTheTeeMockMvc.perform(get("/api/sg-off-the-tees/{id}", sGOffTheTee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sGOffTheTee.getId().intValue()))
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
    public void getNonExistingSGOffTheTee() throws Exception {
        // Get the sGOffTheTee
        restSGOffTheTeeMockMvc.perform(get("/api/sg-off-the-tees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSGOffTheTee() throws Exception {
        // Initialize the database
        sGOffTheTeeRepository.saveAndFlush(sGOffTheTee);

        int databaseSizeBeforeUpdate = sGOffTheTeeRepository.findAll().size();

        // Update the sGOffTheTee
        SGOffTheTee updatedSGOffTheTee = sGOffTheTeeRepository.findById(sGOffTheTee.getId()).get();
        // Disconnect from session so that the updates on updatedSGOffTheTee are not directly saved in db
        em.detach(updatedSGOffTheTee);
        updatedSGOffTheTee
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

        restSGOffTheTeeMockMvc.perform(put("/api/sg-off-the-tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSGOffTheTee)))
            .andExpect(status().isOk());

        // Validate the SGOffTheTee in the database
        List<SGOffTheTee> sGOffTheTeeList = sGOffTheTeeRepository.findAll();
        assertThat(sGOffTheTeeList).hasSize(databaseSizeBeforeUpdate);
        SGOffTheTee testSGOffTheTee = sGOffTheTeeList.get(sGOffTheTeeList.size() - 1);
        assertThat(testSGOffTheTee.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testSGOffTheTee.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testSGOffTheTee.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testSGOffTheTee.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testSGOffTheTee.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testSGOffTheTee.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testSGOffTheTee.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testSGOffTheTee.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testSGOffTheTee.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testSGOffTheTee.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testSGOffTheTee.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testSGOffTheTee.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testSGOffTheTee.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testSGOffTheTee.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testSGOffTheTee.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testSGOffTheTee.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testSGOffTheTee.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testSGOffTheTee.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testSGOffTheTee.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testSGOffTheTee.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testSGOffTheTee.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the SGOffTheTee in Elasticsearch
        verify(mockSGOffTheTeeSearchRepository, times(1)).save(testSGOffTheTee);
    }

    @Test
    @Transactional
    public void updateNonExistingSGOffTheTee() throws Exception {
        int databaseSizeBeforeUpdate = sGOffTheTeeRepository.findAll().size();

        // Create the SGOffTheTee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSGOffTheTeeMockMvc.perform(put("/api/sg-off-the-tees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGOffTheTee)))
            .andExpect(status().isBadRequest());

        // Validate the SGOffTheTee in the database
        List<SGOffTheTee> sGOffTheTeeList = sGOffTheTeeRepository.findAll();
        assertThat(sGOffTheTeeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SGOffTheTee in Elasticsearch
        verify(mockSGOffTheTeeSearchRepository, times(0)).save(sGOffTheTee);
    }

    @Test
    @Transactional
    public void deleteSGOffTheTee() throws Exception {
        // Initialize the database
        sGOffTheTeeRepository.saveAndFlush(sGOffTheTee);

        int databaseSizeBeforeDelete = sGOffTheTeeRepository.findAll().size();

        // Delete the sGOffTheTee
        restSGOffTheTeeMockMvc.perform(delete("/api/sg-off-the-tees/{id}", sGOffTheTee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SGOffTheTee> sGOffTheTeeList = sGOffTheTeeRepository.findAll();
        assertThat(sGOffTheTeeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SGOffTheTee in Elasticsearch
        verify(mockSGOffTheTeeSearchRepository, times(1)).deleteById(sGOffTheTee.getId());
    }

    @Test
    @Transactional
    public void searchSGOffTheTee() throws Exception {
        // Initialize the database
        sGOffTheTeeRepository.saveAndFlush(sGOffTheTee);
        when(mockSGOffTheTeeSearchRepository.search(queryStringQuery("id:" + sGOffTheTee.getId())))
            .thenReturn(Collections.singletonList(sGOffTheTee));
        // Search the sGOffTheTee
        restSGOffTheTeeMockMvc.perform(get("/api/_search/sg-off-the-tees?query=id:" + sGOffTheTee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGOffTheTee.getId().intValue())))
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
        TestUtil.equalsVerifier(SGOffTheTee.class);
        SGOffTheTee sGOffTheTee1 = new SGOffTheTee();
        sGOffTheTee1.setId(1L);
        SGOffTheTee sGOffTheTee2 = new SGOffTheTee();
        sGOffTheTee2.setId(sGOffTheTee1.getId());
        assertThat(sGOffTheTee1).isEqualTo(sGOffTheTee2);
        sGOffTheTee2.setId(2L);
        assertThat(sGOffTheTee1).isNotEqualTo(sGOffTheTee2);
        sGOffTheTee1.setId(null);
        assertThat(sGOffTheTee1).isNotEqualTo(sGOffTheTee2);
    }
}
