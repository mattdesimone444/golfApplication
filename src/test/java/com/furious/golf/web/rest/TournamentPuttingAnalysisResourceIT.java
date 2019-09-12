package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.TournamentPuttingAnalysis;
import com.furious.golf.repository.TournamentPuttingAnalysisRepository;
import com.furious.golf.repository.search.TournamentPuttingAnalysisSearchRepository;
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
 * Integration tests for the {@link TournamentPuttingAnalysisResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class TournamentPuttingAnalysisResourceIT {

    private static final Long DEFAULT_PLAYER_ID = 1L;
    private static final Long UPDATED_PLAYER_ID = 2L;
    private static final Long SMALLER_PLAYER_ID = 1L - 1L;

    private static final Long DEFAULT_TOURNAMENT_ID = 1L;
    private static final Long UPDATED_TOURNAMENT_ID = 2L;
    private static final Long SMALLER_TOURNAMENT_ID = 1L - 1L;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;
    private static final Long SMALLER_COURSE_ID = 1L - 1L;

    private static final Long DEFAULT_ROUND_ONE_ID = 1L;
    private static final Long UPDATED_ROUND_ONE_ID = 2L;
    private static final Long SMALLER_ROUND_ONE_ID = 1L - 1L;

    private static final Long DEFAULT_ROUND_TWO_ID = 1L;
    private static final Long UPDATED_ROUND_TWO_ID = 2L;
    private static final Long SMALLER_ROUND_TWO_ID = 1L - 1L;

    private static final Long DEFAULT_ROUND_THREE_ID = 1L;
    private static final Long UPDATED_ROUND_THREE_ID = 2L;
    private static final Long SMALLER_ROUND_THREE_ID = 1L - 1L;

    private static final Long DEFAULT_ROUND_FOUR_ID = 1L;
    private static final Long UPDATED_ROUND_FOUR_ID = 2L;
    private static final Long SMALLER_ROUND_FOUR_ID = 1L - 1L;

    @Autowired
    private TournamentPuttingAnalysisRepository tournamentPuttingAnalysisRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.TournamentPuttingAnalysisSearchRepositoryMockConfiguration
     */
    @Autowired
    private TournamentPuttingAnalysisSearchRepository mockTournamentPuttingAnalysisSearchRepository;

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

    private MockMvc restTournamentPuttingAnalysisMockMvc;

    private TournamentPuttingAnalysis tournamentPuttingAnalysis;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TournamentPuttingAnalysisResource tournamentPuttingAnalysisResource = new TournamentPuttingAnalysisResource(tournamentPuttingAnalysisRepository, mockTournamentPuttingAnalysisSearchRepository);
        this.restTournamentPuttingAnalysisMockMvc = MockMvcBuilders.standaloneSetup(tournamentPuttingAnalysisResource)
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
    public static TournamentPuttingAnalysis createEntity(EntityManager em) {
        TournamentPuttingAnalysis tournamentPuttingAnalysis = new TournamentPuttingAnalysis()
            .playerId(DEFAULT_PLAYER_ID)
            .tournamentId(DEFAULT_TOURNAMENT_ID)
            .courseId(DEFAULT_COURSE_ID)
            .roundOneId(DEFAULT_ROUND_ONE_ID)
            .roundTwoId(DEFAULT_ROUND_TWO_ID)
            .roundThreeId(DEFAULT_ROUND_THREE_ID)
            .roundFourId(DEFAULT_ROUND_FOUR_ID);
        return tournamentPuttingAnalysis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TournamentPuttingAnalysis createUpdatedEntity(EntityManager em) {
        TournamentPuttingAnalysis tournamentPuttingAnalysis = new TournamentPuttingAnalysis()
            .playerId(UPDATED_PLAYER_ID)
            .tournamentId(UPDATED_TOURNAMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .roundOneId(UPDATED_ROUND_ONE_ID)
            .roundTwoId(UPDATED_ROUND_TWO_ID)
            .roundThreeId(UPDATED_ROUND_THREE_ID)
            .roundFourId(UPDATED_ROUND_FOUR_ID);
        return tournamentPuttingAnalysis;
    }

    @BeforeEach
    public void initTest() {
        tournamentPuttingAnalysis = createEntity(em);
    }

    @Test
    @Transactional
    public void createTournamentPuttingAnalysis() throws Exception {
        int databaseSizeBeforeCreate = tournamentPuttingAnalysisRepository.findAll().size();

        // Create the TournamentPuttingAnalysis
        restTournamentPuttingAnalysisMockMvc.perform(post("/api/tournament-putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournamentPuttingAnalysis)))
            .andExpect(status().isCreated());

        // Validate the TournamentPuttingAnalysis in the database
        List<TournamentPuttingAnalysis> tournamentPuttingAnalysisList = tournamentPuttingAnalysisRepository.findAll();
        assertThat(tournamentPuttingAnalysisList).hasSize(databaseSizeBeforeCreate + 1);
        TournamentPuttingAnalysis testTournamentPuttingAnalysis = tournamentPuttingAnalysisList.get(tournamentPuttingAnalysisList.size() - 1);
        assertThat(testTournamentPuttingAnalysis.getPlayerId()).isEqualTo(DEFAULT_PLAYER_ID);
        assertThat(testTournamentPuttingAnalysis.getTournamentId()).isEqualTo(DEFAULT_TOURNAMENT_ID);
        assertThat(testTournamentPuttingAnalysis.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundOneId()).isEqualTo(DEFAULT_ROUND_ONE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundTwoId()).isEqualTo(DEFAULT_ROUND_TWO_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundThreeId()).isEqualTo(DEFAULT_ROUND_THREE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundFourId()).isEqualTo(DEFAULT_ROUND_FOUR_ID);

        // Validate the TournamentPuttingAnalysis in Elasticsearch
        verify(mockTournamentPuttingAnalysisSearchRepository, times(1)).save(testTournamentPuttingAnalysis);
    }

    @Test
    @Transactional
    public void createTournamentPuttingAnalysisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tournamentPuttingAnalysisRepository.findAll().size();

        // Create the TournamentPuttingAnalysis with an existing ID
        tournamentPuttingAnalysis.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTournamentPuttingAnalysisMockMvc.perform(post("/api/tournament-putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournamentPuttingAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the TournamentPuttingAnalysis in the database
        List<TournamentPuttingAnalysis> tournamentPuttingAnalysisList = tournamentPuttingAnalysisRepository.findAll();
        assertThat(tournamentPuttingAnalysisList).hasSize(databaseSizeBeforeCreate);

        // Validate the TournamentPuttingAnalysis in Elasticsearch
        verify(mockTournamentPuttingAnalysisSearchRepository, times(0)).save(tournamentPuttingAnalysis);
    }


    @Test
    @Transactional
    public void getAllTournamentPuttingAnalyses() throws Exception {
        // Initialize the database
        tournamentPuttingAnalysisRepository.saveAndFlush(tournamentPuttingAnalysis);

        // Get all the tournamentPuttingAnalysisList
        restTournamentPuttingAnalysisMockMvc.perform(get("/api/tournament-putting-analyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tournamentPuttingAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundOneId").value(hasItem(DEFAULT_ROUND_ONE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundTwoId").value(hasItem(DEFAULT_ROUND_TWO_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundThreeId").value(hasItem(DEFAULT_ROUND_THREE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundFourId").value(hasItem(DEFAULT_ROUND_FOUR_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getTournamentPuttingAnalysis() throws Exception {
        // Initialize the database
        tournamentPuttingAnalysisRepository.saveAndFlush(tournamentPuttingAnalysis);

        // Get the tournamentPuttingAnalysis
        restTournamentPuttingAnalysisMockMvc.perform(get("/api/tournament-putting-analyses/{id}", tournamentPuttingAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tournamentPuttingAnalysis.getId().intValue()))
            .andExpect(jsonPath("$.playerId").value(DEFAULT_PLAYER_ID.intValue()))
            .andExpect(jsonPath("$.tournamentId").value(DEFAULT_TOURNAMENT_ID.intValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.roundOneId").value(DEFAULT_ROUND_ONE_ID.intValue()))
            .andExpect(jsonPath("$.roundTwoId").value(DEFAULT_ROUND_TWO_ID.intValue()))
            .andExpect(jsonPath("$.roundThreeId").value(DEFAULT_ROUND_THREE_ID.intValue()))
            .andExpect(jsonPath("$.roundFourId").value(DEFAULT_ROUND_FOUR_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTournamentPuttingAnalysis() throws Exception {
        // Get the tournamentPuttingAnalysis
        restTournamentPuttingAnalysisMockMvc.perform(get("/api/tournament-putting-analyses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTournamentPuttingAnalysis() throws Exception {
        // Initialize the database
        tournamentPuttingAnalysisRepository.saveAndFlush(tournamentPuttingAnalysis);

        int databaseSizeBeforeUpdate = tournamentPuttingAnalysisRepository.findAll().size();

        // Update the tournamentPuttingAnalysis
        TournamentPuttingAnalysis updatedTournamentPuttingAnalysis = tournamentPuttingAnalysisRepository.findById(tournamentPuttingAnalysis.getId()).get();
        // Disconnect from session so that the updates on updatedTournamentPuttingAnalysis are not directly saved in db
        em.detach(updatedTournamentPuttingAnalysis);
        updatedTournamentPuttingAnalysis
            .playerId(UPDATED_PLAYER_ID)
            .tournamentId(UPDATED_TOURNAMENT_ID)
            .courseId(UPDATED_COURSE_ID)
            .roundOneId(UPDATED_ROUND_ONE_ID)
            .roundTwoId(UPDATED_ROUND_TWO_ID)
            .roundThreeId(UPDATED_ROUND_THREE_ID)
            .roundFourId(UPDATED_ROUND_FOUR_ID);

        restTournamentPuttingAnalysisMockMvc.perform(put("/api/tournament-putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTournamentPuttingAnalysis)))
            .andExpect(status().isOk());

        // Validate the TournamentPuttingAnalysis in the database
        List<TournamentPuttingAnalysis> tournamentPuttingAnalysisList = tournamentPuttingAnalysisRepository.findAll();
        assertThat(tournamentPuttingAnalysisList).hasSize(databaseSizeBeforeUpdate);
        TournamentPuttingAnalysis testTournamentPuttingAnalysis = tournamentPuttingAnalysisList.get(tournamentPuttingAnalysisList.size() - 1);
        assertThat(testTournamentPuttingAnalysis.getPlayerId()).isEqualTo(UPDATED_PLAYER_ID);
        assertThat(testTournamentPuttingAnalysis.getTournamentId()).isEqualTo(UPDATED_TOURNAMENT_ID);
        assertThat(testTournamentPuttingAnalysis.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundOneId()).isEqualTo(UPDATED_ROUND_ONE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundTwoId()).isEqualTo(UPDATED_ROUND_TWO_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundThreeId()).isEqualTo(UPDATED_ROUND_THREE_ID);
        assertThat(testTournamentPuttingAnalysis.getRoundFourId()).isEqualTo(UPDATED_ROUND_FOUR_ID);

        // Validate the TournamentPuttingAnalysis in Elasticsearch
        verify(mockTournamentPuttingAnalysisSearchRepository, times(1)).save(testTournamentPuttingAnalysis);
    }

    @Test
    @Transactional
    public void updateNonExistingTournamentPuttingAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = tournamentPuttingAnalysisRepository.findAll().size();

        // Create the TournamentPuttingAnalysis

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTournamentPuttingAnalysisMockMvc.perform(put("/api/tournament-putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournamentPuttingAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the TournamentPuttingAnalysis in the database
        List<TournamentPuttingAnalysis> tournamentPuttingAnalysisList = tournamentPuttingAnalysisRepository.findAll();
        assertThat(tournamentPuttingAnalysisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TournamentPuttingAnalysis in Elasticsearch
        verify(mockTournamentPuttingAnalysisSearchRepository, times(0)).save(tournamentPuttingAnalysis);
    }

    @Test
    @Transactional
    public void deleteTournamentPuttingAnalysis() throws Exception {
        // Initialize the database
        tournamentPuttingAnalysisRepository.saveAndFlush(tournamentPuttingAnalysis);

        int databaseSizeBeforeDelete = tournamentPuttingAnalysisRepository.findAll().size();

        // Delete the tournamentPuttingAnalysis
        restTournamentPuttingAnalysisMockMvc.perform(delete("/api/tournament-putting-analyses/{id}", tournamentPuttingAnalysis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TournamentPuttingAnalysis> tournamentPuttingAnalysisList = tournamentPuttingAnalysisRepository.findAll();
        assertThat(tournamentPuttingAnalysisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TournamentPuttingAnalysis in Elasticsearch
        verify(mockTournamentPuttingAnalysisSearchRepository, times(1)).deleteById(tournamentPuttingAnalysis.getId());
    }

    @Test
    @Transactional
    public void searchTournamentPuttingAnalysis() throws Exception {
        // Initialize the database
        tournamentPuttingAnalysisRepository.saveAndFlush(tournamentPuttingAnalysis);
        when(mockTournamentPuttingAnalysisSearchRepository.search(queryStringQuery("id:" + tournamentPuttingAnalysis.getId())))
            .thenReturn(Collections.singletonList(tournamentPuttingAnalysis));
        // Search the tournamentPuttingAnalysis
        restTournamentPuttingAnalysisMockMvc.perform(get("/api/_search/tournament-putting-analyses?query=id:" + tournamentPuttingAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tournamentPuttingAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].playerId").value(hasItem(DEFAULT_PLAYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].tournamentId").value(hasItem(DEFAULT_TOURNAMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundOneId").value(hasItem(DEFAULT_ROUND_ONE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundTwoId").value(hasItem(DEFAULT_ROUND_TWO_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundThreeId").value(hasItem(DEFAULT_ROUND_THREE_ID.intValue())))
            .andExpect(jsonPath("$.[*].roundFourId").value(hasItem(DEFAULT_ROUND_FOUR_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TournamentPuttingAnalysis.class);
        TournamentPuttingAnalysis tournamentPuttingAnalysis1 = new TournamentPuttingAnalysis();
        tournamentPuttingAnalysis1.setId(1L);
        TournamentPuttingAnalysis tournamentPuttingAnalysis2 = new TournamentPuttingAnalysis();
        tournamentPuttingAnalysis2.setId(tournamentPuttingAnalysis1.getId());
        assertThat(tournamentPuttingAnalysis1).isEqualTo(tournamentPuttingAnalysis2);
        tournamentPuttingAnalysis2.setId(2L);
        assertThat(tournamentPuttingAnalysis1).isNotEqualTo(tournamentPuttingAnalysis2);
        tournamentPuttingAnalysis1.setId(null);
        assertThat(tournamentPuttingAnalysis1).isNotEqualTo(tournamentPuttingAnalysis2);
    }
}
