package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.SandSaves;
import com.furious.golf.repository.SandSavesRepository;
import com.furious.golf.repository.search.SandSavesSearchRepository;
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
 * Integration tests for the {@link SandSavesResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class SandSavesResourceIT {

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
    private SandSavesRepository sandSavesRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.SandSavesSearchRepositoryMockConfiguration
     */
    @Autowired
    private SandSavesSearchRepository mockSandSavesSearchRepository;

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

    private MockMvc restSandSavesMockMvc;

    private SandSaves sandSaves;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SandSavesResource sandSavesResource = new SandSavesResource(sandSavesRepository, mockSandSavesSearchRepository);
        this.restSandSavesMockMvc = MockMvcBuilders.standaloneSetup(sandSavesResource)
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
    public static SandSaves createEntity(EntityManager em) {
        SandSaves sandSaves = new SandSaves()
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
        return sandSaves;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SandSaves createUpdatedEntity(EntityManager em) {
        SandSaves sandSaves = new SandSaves()
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
        return sandSaves;
    }

    @BeforeEach
    public void initTest() {
        sandSaves = createEntity(em);
    }

    @Test
    @Transactional
    public void createSandSaves() throws Exception {
        int databaseSizeBeforeCreate = sandSavesRepository.findAll().size();

        // Create the SandSaves
        restSandSavesMockMvc.perform(post("/api/sand-saves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sandSaves)))
            .andExpect(status().isCreated());

        // Validate the SandSaves in the database
        List<SandSaves> sandSavesList = sandSavesRepository.findAll();
        assertThat(sandSavesList).hasSize(databaseSizeBeforeCreate + 1);
        SandSaves testSandSaves = sandSavesList.get(sandSavesList.size() - 1);
        assertThat(testSandSaves.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testSandSaves.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testSandSaves.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testSandSaves.getHole1()).isEqualTo(DEFAULT_HOLE_1);
        assertThat(testSandSaves.getHole2()).isEqualTo(DEFAULT_HOLE_2);
        assertThat(testSandSaves.getHole3()).isEqualTo(DEFAULT_HOLE_3);
        assertThat(testSandSaves.getHole4()).isEqualTo(DEFAULT_HOLE_4);
        assertThat(testSandSaves.getHole5()).isEqualTo(DEFAULT_HOLE_5);
        assertThat(testSandSaves.getHole6()).isEqualTo(DEFAULT_HOLE_6);
        assertThat(testSandSaves.getHole7()).isEqualTo(DEFAULT_HOLE_7);
        assertThat(testSandSaves.getHole8()).isEqualTo(DEFAULT_HOLE_8);
        assertThat(testSandSaves.getHole9()).isEqualTo(DEFAULT_HOLE_9);
        assertThat(testSandSaves.getHole10()).isEqualTo(DEFAULT_HOLE_10);
        assertThat(testSandSaves.getHole11()).isEqualTo(DEFAULT_HOLE_11);
        assertThat(testSandSaves.getHole12()).isEqualTo(DEFAULT_HOLE_12);
        assertThat(testSandSaves.getHole13()).isEqualTo(DEFAULT_HOLE_13);
        assertThat(testSandSaves.getHole14()).isEqualTo(DEFAULT_HOLE_14);
        assertThat(testSandSaves.getHole15()).isEqualTo(DEFAULT_HOLE_15);
        assertThat(testSandSaves.getHole16()).isEqualTo(DEFAULT_HOLE_16);
        assertThat(testSandSaves.getHole17()).isEqualTo(DEFAULT_HOLE_17);
        assertThat(testSandSaves.getHole18()).isEqualTo(DEFAULT_HOLE_18);

        // Validate the SandSaves in Elasticsearch
        verify(mockSandSavesSearchRepository, times(1)).save(testSandSaves);
    }

    @Test
    @Transactional
    public void createSandSavesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sandSavesRepository.findAll().size();

        // Create the SandSaves with an existing ID
        sandSaves.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSandSavesMockMvc.perform(post("/api/sand-saves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sandSaves)))
            .andExpect(status().isBadRequest());

        // Validate the SandSaves in the database
        List<SandSaves> sandSavesList = sandSavesRepository.findAll();
        assertThat(sandSavesList).hasSize(databaseSizeBeforeCreate);

        // Validate the SandSaves in Elasticsearch
        verify(mockSandSavesSearchRepository, times(0)).save(sandSaves);
    }


    @Test
    @Transactional
    public void getAllSandSaves() throws Exception {
        // Initialize the database
        sandSavesRepository.saveAndFlush(sandSaves);

        // Get all the sandSavesList
        restSandSavesMockMvc.perform(get("/api/sand-saves?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sandSaves.getId().intValue())))
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
    public void getSandSaves() throws Exception {
        // Initialize the database
        sandSavesRepository.saveAndFlush(sandSaves);

        // Get the sandSaves
        restSandSavesMockMvc.perform(get("/api/sand-saves/{id}", sandSaves.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sandSaves.getId().intValue()))
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
    public void getNonExistingSandSaves() throws Exception {
        // Get the sandSaves
        restSandSavesMockMvc.perform(get("/api/sand-saves/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSandSaves() throws Exception {
        // Initialize the database
        sandSavesRepository.saveAndFlush(sandSaves);

        int databaseSizeBeforeUpdate = sandSavesRepository.findAll().size();

        // Update the sandSaves
        SandSaves updatedSandSaves = sandSavesRepository.findById(sandSaves.getId()).get();
        // Disconnect from session so that the updates on updatedSandSaves are not directly saved in db
        em.detach(updatedSandSaves);
        updatedSandSaves
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

        restSandSavesMockMvc.perform(put("/api/sand-saves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSandSaves)))
            .andExpect(status().isOk());

        // Validate the SandSaves in the database
        List<SandSaves> sandSavesList = sandSavesRepository.findAll();
        assertThat(sandSavesList).hasSize(databaseSizeBeforeUpdate);
        SandSaves testSandSaves = sandSavesList.get(sandSavesList.size() - 1);
        assertThat(testSandSaves.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testSandSaves.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testSandSaves.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testSandSaves.getHole1()).isEqualTo(UPDATED_HOLE_1);
        assertThat(testSandSaves.getHole2()).isEqualTo(UPDATED_HOLE_2);
        assertThat(testSandSaves.getHole3()).isEqualTo(UPDATED_HOLE_3);
        assertThat(testSandSaves.getHole4()).isEqualTo(UPDATED_HOLE_4);
        assertThat(testSandSaves.getHole5()).isEqualTo(UPDATED_HOLE_5);
        assertThat(testSandSaves.getHole6()).isEqualTo(UPDATED_HOLE_6);
        assertThat(testSandSaves.getHole7()).isEqualTo(UPDATED_HOLE_7);
        assertThat(testSandSaves.getHole8()).isEqualTo(UPDATED_HOLE_8);
        assertThat(testSandSaves.getHole9()).isEqualTo(UPDATED_HOLE_9);
        assertThat(testSandSaves.getHole10()).isEqualTo(UPDATED_HOLE_10);
        assertThat(testSandSaves.getHole11()).isEqualTo(UPDATED_HOLE_11);
        assertThat(testSandSaves.getHole12()).isEqualTo(UPDATED_HOLE_12);
        assertThat(testSandSaves.getHole13()).isEqualTo(UPDATED_HOLE_13);
        assertThat(testSandSaves.getHole14()).isEqualTo(UPDATED_HOLE_14);
        assertThat(testSandSaves.getHole15()).isEqualTo(UPDATED_HOLE_15);
        assertThat(testSandSaves.getHole16()).isEqualTo(UPDATED_HOLE_16);
        assertThat(testSandSaves.getHole17()).isEqualTo(UPDATED_HOLE_17);
        assertThat(testSandSaves.getHole18()).isEqualTo(UPDATED_HOLE_18);

        // Validate the SandSaves in Elasticsearch
        verify(mockSandSavesSearchRepository, times(1)).save(testSandSaves);
    }

    @Test
    @Transactional
    public void updateNonExistingSandSaves() throws Exception {
        int databaseSizeBeforeUpdate = sandSavesRepository.findAll().size();

        // Create the SandSaves

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSandSavesMockMvc.perform(put("/api/sand-saves")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sandSaves)))
            .andExpect(status().isBadRequest());

        // Validate the SandSaves in the database
        List<SandSaves> sandSavesList = sandSavesRepository.findAll();
        assertThat(sandSavesList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SandSaves in Elasticsearch
        verify(mockSandSavesSearchRepository, times(0)).save(sandSaves);
    }

    @Test
    @Transactional
    public void deleteSandSaves() throws Exception {
        // Initialize the database
        sandSavesRepository.saveAndFlush(sandSaves);

        int databaseSizeBeforeDelete = sandSavesRepository.findAll().size();

        // Delete the sandSaves
        restSandSavesMockMvc.perform(delete("/api/sand-saves/{id}", sandSaves.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SandSaves> sandSavesList = sandSavesRepository.findAll();
        assertThat(sandSavesList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SandSaves in Elasticsearch
        verify(mockSandSavesSearchRepository, times(1)).deleteById(sandSaves.getId());
    }

    @Test
    @Transactional
    public void searchSandSaves() throws Exception {
        // Initialize the database
        sandSavesRepository.saveAndFlush(sandSaves);
        when(mockSandSavesSearchRepository.search(queryStringQuery("id:" + sandSaves.getId())))
            .thenReturn(Collections.singletonList(sandSaves));
        // Search the sandSaves
        restSandSavesMockMvc.perform(get("/api/_search/sand-saves?query=id:" + sandSaves.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sandSaves.getId().intValue())))
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
        TestUtil.equalsVerifier(SandSaves.class);
        SandSaves sandSaves1 = new SandSaves();
        sandSaves1.setId(1L);
        SandSaves sandSaves2 = new SandSaves();
        sandSaves2.setId(sandSaves1.getId());
        assertThat(sandSaves1).isEqualTo(sandSaves2);
        sandSaves2.setId(2L);
        assertThat(sandSaves1).isNotEqualTo(sandSaves2);
        sandSaves1.setId(null);
        assertThat(sandSaves1).isNotEqualTo(sandSaves2);
    }
}
