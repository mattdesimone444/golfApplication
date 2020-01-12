package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.Tournament;
import com.furious.golf.repository.TournamentRepository;
import com.furious.golf.repository.search.TournamentSearchRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TournamentResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class TournamentResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEASON = 1;
    private static final Integer UPDATED_SEASON = 2;
    private static final Integer SMALLER_SEASON = 1 - 1;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_START_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_END_DATE = LocalDate.ofEpochDay(-1L);

    private static final Double DEFAULT_PURSE = 1D;
    private static final Double UPDATED_PURSE = 2D;
    private static final Double SMALLER_PURSE = 1D - 1D;

    private static final Long DEFAULT_COURSE_ID = 1L;
    private static final Long UPDATED_COURSE_ID = 2L;
    private static final Long SMALLER_COURSE_ID = 1L - 1L;

    private static final Long DEFAULT_PGA_ID = 1L;
    private static final Long UPDATED_PGA_ID = 2L;
    private static final Long SMALLER_PGA_ID = 1L - 1L;

    private static final Long DEFAULT_PGA_SEASON_ID = 1L;
    private static final Long UPDATED_PGA_SEASON_ID = 2L;
    private static final Long SMALLER_PGA_SEASON_ID = 1L - 1L;

    private static final Boolean DEFAULT_LOADED = false;
    private static final Boolean UPDATED_LOADED = true;

    @Autowired
    private TournamentRepository tournamentRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.TournamentSearchRepositoryMockConfiguration
     */
    @Autowired
    private TournamentSearchRepository mockTournamentSearchRepository;

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

    private MockMvc restTournamentMockMvc;

    private Tournament tournament;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TournamentResource tournamentResource = new TournamentResource(tournamentRepository, mockTournamentSearchRepository);
        this.restTournamentMockMvc = MockMvcBuilders.standaloneSetup(tournamentResource)
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
    public static Tournament createEntity(EntityManager em) {
        Tournament tournament = new Tournament()
            .name(DEFAULT_NAME)
            .season(DEFAULT_SEASON)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .purse(DEFAULT_PURSE)
            .courseId(DEFAULT_COURSE_ID)
            .pgaId(DEFAULT_PGA_ID)
            .pgaSeasonId(DEFAULT_PGA_SEASON_ID)
            .loaded(DEFAULT_LOADED);
        return tournament;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tournament createUpdatedEntity(EntityManager em) {
        Tournament tournament = new Tournament()
            .name(UPDATED_NAME)
            .season(UPDATED_SEASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .purse(UPDATED_PURSE)
            .courseId(UPDATED_COURSE_ID)
            .pgaId(UPDATED_PGA_ID)
            .pgaSeasonId(UPDATED_PGA_SEASON_ID)
            .loaded(UPDATED_LOADED);
        return tournament;
    }

    @BeforeEach
    public void initTest() {
        tournament = createEntity(em);
    }

    @Test
    @Transactional
    public void createTournament() throws Exception {
        int databaseSizeBeforeCreate = tournamentRepository.findAll().size();

        // Create the Tournament
        restTournamentMockMvc.perform(post("/api/tournaments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournament)))
            .andExpect(status().isCreated());

        // Validate the Tournament in the database
        List<Tournament> tournamentList = tournamentRepository.findAll();
        assertThat(tournamentList).hasSize(databaseSizeBeforeCreate + 1);
        Tournament testTournament = tournamentList.get(tournamentList.size() - 1);
        assertThat(testTournament.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTournament.getSeason()).isEqualTo(DEFAULT_SEASON);
        assertThat(testTournament.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testTournament.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testTournament.getPurse()).isEqualTo(DEFAULT_PURSE);
        assertThat(testTournament.getCourseId()).isEqualTo(DEFAULT_COURSE_ID);
        assertThat(testTournament.getPgaId()).isEqualTo(DEFAULT_PGA_ID);
        assertThat(testTournament.getPgaSeasonId()).isEqualTo(DEFAULT_PGA_SEASON_ID);
        assertThat(testTournament.isLoaded()).isEqualTo(DEFAULT_LOADED);

        // Validate the Tournament in Elasticsearch
        verify(mockTournamentSearchRepository, times(1)).save(testTournament);
    }

    @Test
    @Transactional
    public void createTournamentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tournamentRepository.findAll().size();

        // Create the Tournament with an existing ID
        tournament.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTournamentMockMvc.perform(post("/api/tournaments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournament)))
            .andExpect(status().isBadRequest());

        // Validate the Tournament in the database
        List<Tournament> tournamentList = tournamentRepository.findAll();
        assertThat(tournamentList).hasSize(databaseSizeBeforeCreate);

        // Validate the Tournament in Elasticsearch
        verify(mockTournamentSearchRepository, times(0)).save(tournament);
    }


    @Test
    @Transactional
    public void getAllTournaments() throws Exception {
        // Initialize the database
        tournamentRepository.saveAndFlush(tournament);

        // Get all the tournamentList
        restTournamentMockMvc.perform(get("/api/tournaments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tournament.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].season").value(hasItem(DEFAULT_SEASON)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].purse").value(hasItem(DEFAULT_PURSE.doubleValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].pgaId").value(hasItem(DEFAULT_PGA_ID.intValue())))
            .andExpect(jsonPath("$.[*].pgaSeasonId").value(hasItem(DEFAULT_PGA_SEASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].loaded").value(hasItem(DEFAULT_LOADED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTournament() throws Exception {
        // Initialize the database
        tournamentRepository.saveAndFlush(tournament);

        // Get the tournament
        restTournamentMockMvc.perform(get("/api/tournaments/{id}", tournament.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tournament.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.season").value(DEFAULT_SEASON))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.purse").value(DEFAULT_PURSE.doubleValue()))
            .andExpect(jsonPath("$.courseId").value(DEFAULT_COURSE_ID.intValue()))
            .andExpect(jsonPath("$.pgaId").value(DEFAULT_PGA_ID.intValue()))
            .andExpect(jsonPath("$.pgaSeasonId").value(DEFAULT_PGA_SEASON_ID.intValue()))
            .andExpect(jsonPath("$.loaded").value(DEFAULT_LOADED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTournament() throws Exception {
        // Get the tournament
        restTournamentMockMvc.perform(get("/api/tournaments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTournament() throws Exception {
        // Initialize the database
        tournamentRepository.saveAndFlush(tournament);

        int databaseSizeBeforeUpdate = tournamentRepository.findAll().size();

        // Update the tournament
        Tournament updatedTournament = tournamentRepository.findById(tournament.getId()).get();
        // Disconnect from session so that the updates on updatedTournament are not directly saved in db
        em.detach(updatedTournament);
        updatedTournament
            .name(UPDATED_NAME)
            .season(UPDATED_SEASON)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .purse(UPDATED_PURSE)
            .courseId(UPDATED_COURSE_ID)
            .pgaId(UPDATED_PGA_ID)
            .pgaSeasonId(UPDATED_PGA_SEASON_ID)
            .loaded(UPDATED_LOADED);

        restTournamentMockMvc.perform(put("/api/tournaments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTournament)))
            .andExpect(status().isOk());

        // Validate the Tournament in the database
        List<Tournament> tournamentList = tournamentRepository.findAll();
        assertThat(tournamentList).hasSize(databaseSizeBeforeUpdate);
        Tournament testTournament = tournamentList.get(tournamentList.size() - 1);
        assertThat(testTournament.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTournament.getSeason()).isEqualTo(UPDATED_SEASON);
        assertThat(testTournament.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testTournament.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testTournament.getPurse()).isEqualTo(UPDATED_PURSE);
        assertThat(testTournament.getCourseId()).isEqualTo(UPDATED_COURSE_ID);
        assertThat(testTournament.getPgaId()).isEqualTo(UPDATED_PGA_ID);
        assertThat(testTournament.getPgaSeasonId()).isEqualTo(UPDATED_PGA_SEASON_ID);
        assertThat(testTournament.isLoaded()).isEqualTo(UPDATED_LOADED);

        // Validate the Tournament in Elasticsearch
        verify(mockTournamentSearchRepository, times(1)).save(testTournament);
    }

    @Test
    @Transactional
    public void updateNonExistingTournament() throws Exception {
        int databaseSizeBeforeUpdate = tournamentRepository.findAll().size();

        // Create the Tournament

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTournamentMockMvc.perform(put("/api/tournaments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tournament)))
            .andExpect(status().isBadRequest());

        // Validate the Tournament in the database
        List<Tournament> tournamentList = tournamentRepository.findAll();
        assertThat(tournamentList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Tournament in Elasticsearch
        verify(mockTournamentSearchRepository, times(0)).save(tournament);
    }

    @Test
    @Transactional
    public void deleteTournament() throws Exception {
        // Initialize the database
        tournamentRepository.saveAndFlush(tournament);

        int databaseSizeBeforeDelete = tournamentRepository.findAll().size();

        // Delete the tournament
        restTournamentMockMvc.perform(delete("/api/tournaments/{id}", tournament.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tournament> tournamentList = tournamentRepository.findAll();
        assertThat(tournamentList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Tournament in Elasticsearch
        verify(mockTournamentSearchRepository, times(1)).deleteById(tournament.getId());
    }

    @Test
    @Transactional
    public void searchTournament() throws Exception {
        // Initialize the database
        tournamentRepository.saveAndFlush(tournament);
        when(mockTournamentSearchRepository.search(queryStringQuery("id:" + tournament.getId())))
            .thenReturn(Collections.singletonList(tournament));
        // Search the tournament
        restTournamentMockMvc.perform(get("/api/_search/tournaments?query=id:" + tournament.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tournament.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].season").value(hasItem(DEFAULT_SEASON)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].purse").value(hasItem(DEFAULT_PURSE.doubleValue())))
            .andExpect(jsonPath("$.[*].courseId").value(hasItem(DEFAULT_COURSE_ID.intValue())))
            .andExpect(jsonPath("$.[*].pgaId").value(hasItem(DEFAULT_PGA_ID.intValue())))
            .andExpect(jsonPath("$.[*].pgaSeasonId").value(hasItem(DEFAULT_PGA_SEASON_ID.intValue())))
            .andExpect(jsonPath("$.[*].loaded").value(hasItem(DEFAULT_LOADED.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tournament.class);
        Tournament tournament1 = new Tournament();
        tournament1.setId(1L);
        Tournament tournament2 = new Tournament();
        tournament2.setId(tournament1.getId());
        assertThat(tournament1).isEqualTo(tournament2);
        tournament2.setId(2L);
        assertThat(tournament1).isNotEqualTo(tournament2);
        tournament1.setId(null);
        assertThat(tournament1).isNotEqualTo(tournament2);
    }
}
