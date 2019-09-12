package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.SGApproach;
import com.furious.golf.repository.SGApproachRepository;
import com.furious.golf.repository.search.SGApproachSearchRepository;
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
 * Integration tests for the {@link SGApproachResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class SGApproachResourceIT {

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
    private SGApproachRepository sGApproachRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.SGApproachSearchRepositoryMockConfiguration
     */
    @Autowired
    private SGApproachSearchRepository mockSGApproachSearchRepository;

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

    private MockMvc restSGApproachMockMvc;

    private SGApproach sGApproach;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SGApproachResource sGApproachResource = new SGApproachResource(sGApproachRepository, mockSGApproachSearchRepository);
        this.restSGApproachMockMvc = MockMvcBuilders.standaloneSetup(sGApproachResource)
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
    public static SGApproach createEntity(EntityManager em) {
        SGApproach sGApproach = new SGApproach()
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
        return sGApproach;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SGApproach createUpdatedEntity(EntityManager em) {
        SGApproach sGApproach = new SGApproach()
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
        return sGApproach;
    }

    @BeforeEach
    public void initTest() {
        sGApproach = createEntity(em);
    }

    @Test
    @Transactional
    public void createSGApproach() throws Exception {
        int databaseSizeBeforeCreate = sGApproachRepository.findAll().size();

        // Create the SGApproach
        restSGApproachMockMvc.perform(post("/api/sg-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGApproach)))
            .andExpect(status().isCreated());

        // Validate the SGApproach in the database
        List<SGApproach> sGApproachList = sGApproachRepository.findAll();
        assertThat(sGApproachList).hasSize(databaseSizeBeforeCreate + 1);
        SGApproach testSGApproach = sGApproachList.get(sGApproachList.size() - 1);
        assertThat(testSGApproach.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testSGApproach.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testSGApproach.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testSGApproach.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testSGApproach.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testSGApproach.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testSGApproach.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testSGApproach.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testSGApproach.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testSGApproach.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testSGApproach.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testSGApproach.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testSGApproach.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testSGApproach.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testSGApproach.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testSGApproach.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testSGApproach.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testSGApproach.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testSGApproach.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testSGApproach.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testSGApproach.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the SGApproach in Elasticsearch
        verify(mockSGApproachSearchRepository, times(1)).save(testSGApproach);
    }

    @Test
    @Transactional
    public void createSGApproachWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sGApproachRepository.findAll().size();

        // Create the SGApproach with an existing ID
        sGApproach.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSGApproachMockMvc.perform(post("/api/sg-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGApproach)))
            .andExpect(status().isBadRequest());

        // Validate the SGApproach in the database
        List<SGApproach> sGApproachList = sGApproachRepository.findAll();
        assertThat(sGApproachList).hasSize(databaseSizeBeforeCreate);

        // Validate the SGApproach in Elasticsearch
        verify(mockSGApproachSearchRepository, times(0)).save(sGApproach);
    }


    @Test
    @Transactional
    public void getAllSGApproaches() throws Exception {
        // Initialize the database
        sGApproachRepository.saveAndFlush(sGApproach);

        // Get all the sGApproachList
        restSGApproachMockMvc.perform(get("/api/sg-approaches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGApproach.getId().intValue())))
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
    public void getSGApproach() throws Exception {
        // Initialize the database
        sGApproachRepository.saveAndFlush(sGApproach);

        // Get the sGApproach
        restSGApproachMockMvc.perform(get("/api/sg-approaches/{id}", sGApproach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sGApproach.getId().intValue()))
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
    public void getNonExistingSGApproach() throws Exception {
        // Get the sGApproach
        restSGApproachMockMvc.perform(get("/api/sg-approaches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSGApproach() throws Exception {
        // Initialize the database
        sGApproachRepository.saveAndFlush(sGApproach);

        int databaseSizeBeforeUpdate = sGApproachRepository.findAll().size();

        // Update the sGApproach
        SGApproach updatedSGApproach = sGApproachRepository.findById(sGApproach.getId()).get();
        // Disconnect from session so that the updates on updatedSGApproach are not directly saved in db
        em.detach(updatedSGApproach);
        updatedSGApproach
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

        restSGApproachMockMvc.perform(put("/api/sg-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSGApproach)))
            .andExpect(status().isOk());

        // Validate the SGApproach in the database
        List<SGApproach> sGApproachList = sGApproachRepository.findAll();
        assertThat(sGApproachList).hasSize(databaseSizeBeforeUpdate);
        SGApproach testSGApproach = sGApproachList.get(sGApproachList.size() - 1);
        assertThat(testSGApproach.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testSGApproach.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testSGApproach.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testSGApproach.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testSGApproach.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testSGApproach.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testSGApproach.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testSGApproach.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testSGApproach.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testSGApproach.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testSGApproach.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testSGApproach.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testSGApproach.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testSGApproach.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testSGApproach.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testSGApproach.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testSGApproach.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testSGApproach.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testSGApproach.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testSGApproach.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testSGApproach.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the SGApproach in Elasticsearch
        verify(mockSGApproachSearchRepository, times(1)).save(testSGApproach);
    }

    @Test
    @Transactional
    public void updateNonExistingSGApproach() throws Exception {
        int databaseSizeBeforeUpdate = sGApproachRepository.findAll().size();

        // Create the SGApproach

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSGApproachMockMvc.perform(put("/api/sg-approaches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sGApproach)))
            .andExpect(status().isBadRequest());

        // Validate the SGApproach in the database
        List<SGApproach> sGApproachList = sGApproachRepository.findAll();
        assertThat(sGApproachList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SGApproach in Elasticsearch
        verify(mockSGApproachSearchRepository, times(0)).save(sGApproach);
    }

    @Test
    @Transactional
    public void deleteSGApproach() throws Exception {
        // Initialize the database
        sGApproachRepository.saveAndFlush(sGApproach);

        int databaseSizeBeforeDelete = sGApproachRepository.findAll().size();

        // Delete the sGApproach
        restSGApproachMockMvc.perform(delete("/api/sg-approaches/{id}", sGApproach.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SGApproach> sGApproachList = sGApproachRepository.findAll();
        assertThat(sGApproachList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SGApproach in Elasticsearch
        verify(mockSGApproachSearchRepository, times(1)).deleteById(sGApproach.getId());
    }

    @Test
    @Transactional
    public void searchSGApproach() throws Exception {
        // Initialize the database
        sGApproachRepository.saveAndFlush(sGApproach);
        when(mockSGApproachSearchRepository.search(queryStringQuery("id:" + sGApproach.getId())))
            .thenReturn(Collections.singletonList(sGApproach));
        // Search the sGApproach
        restSGApproachMockMvc.perform(get("/api/_search/sg-approaches?query=id:" + sGApproach.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sGApproach.getId().intValue())))
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
        TestUtil.equalsVerifier(SGApproach.class);
        SGApproach sGApproach1 = new SGApproach();
        sGApproach1.setId(1L);
        SGApproach sGApproach2 = new SGApproach();
        sGApproach2.setId(sGApproach1.getId());
        assertThat(sGApproach1).isEqualTo(sGApproach2);
        sGApproach2.setId(2L);
        assertThat(sGApproach1).isNotEqualTo(sGApproach2);
        sGApproach1.setId(null);
        assertThat(sGApproach1).isNotEqualTo(sGApproach2);
    }
}
