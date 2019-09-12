package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.Putts;
import com.furious.golf.repository.PuttsRepository;
import com.furious.golf.repository.search.PuttsSearchRepository;
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
 * Integration tests for the {@link PuttsResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class PuttsResourceIT {

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
    private PuttsRepository puttsRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.PuttsSearchRepositoryMockConfiguration
     */
    @Autowired
    private PuttsSearchRepository mockPuttsSearchRepository;

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

    private MockMvc restPuttsMockMvc;

    private Putts putts;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PuttsResource puttsResource = new PuttsResource(puttsRepository, mockPuttsSearchRepository);
        this.restPuttsMockMvc = MockMvcBuilders.standaloneSetup(puttsResource)
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
    public static Putts createEntity(EntityManager em) {
        Putts putts = new Putts()
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
        return putts;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Putts createUpdatedEntity(EntityManager em) {
        Putts putts = new Putts()
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
        return putts;
    }

    @BeforeEach
    public void initTest() {
        putts = createEntity(em);
    }

    @Test
    @Transactional
    public void createPutts() throws Exception {
        int databaseSizeBeforeCreate = puttsRepository.findAll().size();

        // Create the Putts
        restPuttsMockMvc.perform(post("/api/putts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(putts)))
            .andExpect(status().isCreated());

        // Validate the Putts in the database
        List<Putts> puttsList = puttsRepository.findAll();
        assertThat(puttsList).hasSize(databaseSizeBeforeCreate + 1);
        Putts testPutts = puttsList.get(puttsList.size() - 1);
        assertThat(testPutts.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testPutts.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testPutts.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testPutts.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testPutts.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testPutts.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testPutts.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testPutts.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testPutts.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testPutts.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testPutts.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testPutts.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testPutts.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testPutts.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testPutts.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testPutts.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testPutts.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testPutts.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testPutts.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testPutts.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testPutts.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the Putts in Elasticsearch
        verify(mockPuttsSearchRepository, times(1)).save(testPutts);
    }

    @Test
    @Transactional
    public void createPuttsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puttsRepository.findAll().size();

        // Create the Putts with an existing ID
        putts.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuttsMockMvc.perform(post("/api/putts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(putts)))
            .andExpect(status().isBadRequest());

        // Validate the Putts in the database
        List<Putts> puttsList = puttsRepository.findAll();
        assertThat(puttsList).hasSize(databaseSizeBeforeCreate);

        // Validate the Putts in Elasticsearch
        verify(mockPuttsSearchRepository, times(0)).save(putts);
    }


    @Test
    @Transactional
    public void getAllPutts() throws Exception {
        // Initialize the database
        puttsRepository.saveAndFlush(putts);

        // Get all the puttsList
        restPuttsMockMvc.perform(get("/api/putts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(putts.getId().intValue())))
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
    public void getPutts() throws Exception {
        // Initialize the database
        puttsRepository.saveAndFlush(putts);

        // Get the putts
        restPuttsMockMvc.perform(get("/api/putts/{id}", putts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(putts.getId().intValue()))
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
    public void getNonExistingPutts() throws Exception {
        // Get the putts
        restPuttsMockMvc.perform(get("/api/putts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePutts() throws Exception {
        // Initialize the database
        puttsRepository.saveAndFlush(putts);

        int databaseSizeBeforeUpdate = puttsRepository.findAll().size();

        // Update the putts
        Putts updatedPutts = puttsRepository.findById(putts.getId()).get();
        // Disconnect from session so that the updates on updatedPutts are not directly saved in db
        em.detach(updatedPutts);
        updatedPutts
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

        restPuttsMockMvc.perform(put("/api/putts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPutts)))
            .andExpect(status().isOk());

        // Validate the Putts in the database
        List<Putts> puttsList = puttsRepository.findAll();
        assertThat(puttsList).hasSize(databaseSizeBeforeUpdate);
        Putts testPutts = puttsList.get(puttsList.size() - 1);
        assertThat(testPutts.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testPutts.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testPutts.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testPutts.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testPutts.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testPutts.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testPutts.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testPutts.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testPutts.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testPutts.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testPutts.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testPutts.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testPutts.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testPutts.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testPutts.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testPutts.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testPutts.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testPutts.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testPutts.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testPutts.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testPutts.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the Putts in Elasticsearch
        verify(mockPuttsSearchRepository, times(1)).save(testPutts);
    }

    @Test
    @Transactional
    public void updateNonExistingPutts() throws Exception {
        int databaseSizeBeforeUpdate = puttsRepository.findAll().size();

        // Create the Putts

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuttsMockMvc.perform(put("/api/putts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(putts)))
            .andExpect(status().isBadRequest());

        // Validate the Putts in the database
        List<Putts> puttsList = puttsRepository.findAll();
        assertThat(puttsList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Putts in Elasticsearch
        verify(mockPuttsSearchRepository, times(0)).save(putts);
    }

    @Test
    @Transactional
    public void deletePutts() throws Exception {
        // Initialize the database
        puttsRepository.saveAndFlush(putts);

        int databaseSizeBeforeDelete = puttsRepository.findAll().size();

        // Delete the putts
        restPuttsMockMvc.perform(delete("/api/putts/{id}", putts.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Putts> puttsList = puttsRepository.findAll();
        assertThat(puttsList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Putts in Elasticsearch
        verify(mockPuttsSearchRepository, times(1)).deleteById(putts.getId());
    }

    @Test
    @Transactional
    public void searchPutts() throws Exception {
        // Initialize the database
        puttsRepository.saveAndFlush(putts);
        when(mockPuttsSearchRepository.search(queryStringQuery("id:" + putts.getId())))
            .thenReturn(Collections.singletonList(putts));
        // Search the putts
        restPuttsMockMvc.perform(get("/api/_search/putts?query=id:" + putts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(putts.getId().intValue())))
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
        TestUtil.equalsVerifier(Putts.class);
        Putts putts1 = new Putts();
        putts1.setId(1L);
        Putts putts2 = new Putts();
        putts2.setId(putts1.getId());
        assertThat(putts1).isEqualTo(putts2);
        putts2.setId(2L);
        assertThat(putts1).isNotEqualTo(putts2);
        putts1.setId(null);
        assertThat(putts1).isNotEqualTo(putts2);
    }
}
