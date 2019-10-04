package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.Golfer;
import com.furious.golf.repository.GolferRepository;
import com.furious.golf.repository.search.GolferSearchRepository;
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
 * Integration tests for the {@link GolferResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class GolferResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_HEIGHT = 1D;
    private static final Double UPDATED_HEIGHT = 2D;
    private static final Double SMALLER_HEIGHT = 1D - 1D;

    private static final Integer DEFAULT_WEIGHT = 1;
    private static final Integer UPDATED_WEIGHT = 2;
    private static final Integer SMALLER_WEIGHT = 1 - 1;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;
    private static final Integer SMALLER_AGE = 1 - 1;

    private static final String DEFAULT_RESIDENCE_CITY = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCE_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCE_STATE = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCE_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PLAYS_FROM_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PLAYS_FROM_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PLAYS_FROM_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PLAYS_FROM_STATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TURNED_PRO = 1;
    private static final Integer UPDATED_TURNED_PRO = 2;
    private static final Integer SMALLER_TURNED_PRO = 1 - 1;

    private static final Long DEFAULT_PGA_ID = 1L;
    private static final Long UPDATED_PGA_ID = 2L;
    private static final Long SMALLER_PGA_ID = 1L - 1L;

    @Autowired
    private GolferRepository golferRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.GolferSearchRepositoryMockConfiguration
     */
    @Autowired
    private GolferSearchRepository mockGolferSearchRepository;

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

    private MockMvc restGolferMockMvc;

    private Golfer golfer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GolferResource golferResource = new GolferResource(golferRepository, mockGolferSearchRepository);
        this.restGolferMockMvc = MockMvcBuilders.standaloneSetup(golferResource)
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
    public static Golfer createEntity(EntityManager em) {
        Golfer golfer = new Golfer()
            .name(DEFAULT_NAME)
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .age(DEFAULT_AGE)
            .residenceCity(DEFAULT_RESIDENCE_CITY)
            .residenceState(DEFAULT_RESIDENCE_STATE)
            .playsFromCity(DEFAULT_PLAYS_FROM_CITY)
            .playsFromState(DEFAULT_PLAYS_FROM_STATE)
            .turnedPro(DEFAULT_TURNED_PRO)
            .pgaId(DEFAULT_PGA_ID);
        return golfer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Golfer createUpdatedEntity(EntityManager em) {
        Golfer golfer = new Golfer()
            .name(UPDATED_NAME)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .age(UPDATED_AGE)
            .residenceCity(UPDATED_RESIDENCE_CITY)
            .residenceState(UPDATED_RESIDENCE_STATE)
            .playsFromCity(UPDATED_PLAYS_FROM_CITY)
            .playsFromState(UPDATED_PLAYS_FROM_STATE)
            .turnedPro(UPDATED_TURNED_PRO)
            .pgaId(UPDATED_PGA_ID);
        return golfer;
    }

    @BeforeEach
    public void initTest() {
        golfer = createEntity(em);
    }

    @Test
    @Transactional
    public void createGolfer() throws Exception {
        int databaseSizeBeforeCreate = golferRepository.findAll().size();

        // Create the Golfer
        restGolferMockMvc.perform(post("/api/golfers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(golfer)))
            .andExpect(status().isCreated());

        // Validate the Golfer in the database
        List<Golfer> golferList = golferRepository.findAll();
        assertThat(golferList).hasSize(databaseSizeBeforeCreate + 1);
        Golfer testGolfer = golferList.get(golferList.size() - 1);
        assertThat(testGolfer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGolfer.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testGolfer.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testGolfer.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testGolfer.getResidenceCity()).isEqualTo(DEFAULT_RESIDENCE_CITY);
        assertThat(testGolfer.getResidenceState()).isEqualTo(DEFAULT_RESIDENCE_STATE);
        assertThat(testGolfer.getPlaysFromCity()).isEqualTo(DEFAULT_PLAYS_FROM_CITY);
        assertThat(testGolfer.getPlaysFromState()).isEqualTo(DEFAULT_PLAYS_FROM_STATE);
        assertThat(testGolfer.getTurnedPro()).isEqualTo(DEFAULT_TURNED_PRO);
        assertThat(testGolfer.getPgaId()).isEqualTo(DEFAULT_PGA_ID);

        // Validate the Golfer in Elasticsearch
        verify(mockGolferSearchRepository, times(1)).save(testGolfer);
    }

    @Test
    @Transactional
    public void createGolferWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = golferRepository.findAll().size();

        // Create the Golfer with an existing ID
        golfer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGolferMockMvc.perform(post("/api/golfers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(golfer)))
            .andExpect(status().isBadRequest());

        // Validate the Golfer in the database
        List<Golfer> golferList = golferRepository.findAll();
        assertThat(golferList).hasSize(databaseSizeBeforeCreate);

        // Validate the Golfer in Elasticsearch
        verify(mockGolferSearchRepository, times(0)).save(golfer);
    }


    @Test
    @Transactional
    public void getAllGolfers() throws Exception {
        // Initialize the database
        golferRepository.saveAndFlush(golfer);

        // Get all the golferList
        restGolferMockMvc.perform(get("/api/golfers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(golfer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].residenceCity").value(hasItem(DEFAULT_RESIDENCE_CITY.toString())))
            .andExpect(jsonPath("$.[*].residenceState").value(hasItem(DEFAULT_RESIDENCE_STATE.toString())))
            .andExpect(jsonPath("$.[*].playsFromCity").value(hasItem(DEFAULT_PLAYS_FROM_CITY.toString())))
            .andExpect(jsonPath("$.[*].playsFromState").value(hasItem(DEFAULT_PLAYS_FROM_STATE.toString())))
            .andExpect(jsonPath("$.[*].turnedPro").value(hasItem(DEFAULT_TURNED_PRO)))
            .andExpect(jsonPath("$.[*].pgaId").value(hasItem(DEFAULT_PGA_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getGolfer() throws Exception {
        // Initialize the database
        golferRepository.saveAndFlush(golfer);

        // Get the golfer
        restGolferMockMvc.perform(get("/api/golfers/{id}", golfer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(golfer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.residenceCity").value(DEFAULT_RESIDENCE_CITY.toString()))
            .andExpect(jsonPath("$.residenceState").value(DEFAULT_RESIDENCE_STATE.toString()))
            .andExpect(jsonPath("$.playsFromCity").value(DEFAULT_PLAYS_FROM_CITY.toString()))
            .andExpect(jsonPath("$.playsFromState").value(DEFAULT_PLAYS_FROM_STATE.toString()))
            .andExpect(jsonPath("$.turnedPro").value(DEFAULT_TURNED_PRO))
            .andExpect(jsonPath("$.pgaId").value(DEFAULT_PGA_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGolfer() throws Exception {
        // Get the golfer
        restGolferMockMvc.perform(get("/api/golfers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGolfer() throws Exception {
        // Initialize the database
        golferRepository.saveAndFlush(golfer);

        int databaseSizeBeforeUpdate = golferRepository.findAll().size();

        // Update the golfer
        Golfer updatedGolfer = golferRepository.findById(golfer.getId()).get();
        // Disconnect from session so that the updates on updatedGolfer are not directly saved in db
        em.detach(updatedGolfer);
        updatedGolfer
            .name(UPDATED_NAME)
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .age(UPDATED_AGE)
            .residenceCity(UPDATED_RESIDENCE_CITY)
            .residenceState(UPDATED_RESIDENCE_STATE)
            .playsFromCity(UPDATED_PLAYS_FROM_CITY)
            .playsFromState(UPDATED_PLAYS_FROM_STATE)
            .turnedPro(UPDATED_TURNED_PRO)
            .pgaId(UPDATED_PGA_ID);

        restGolferMockMvc.perform(put("/api/golfers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGolfer)))
            .andExpect(status().isOk());

        // Validate the Golfer in the database
        List<Golfer> golferList = golferRepository.findAll();
        assertThat(golferList).hasSize(databaseSizeBeforeUpdate);
        Golfer testGolfer = golferList.get(golferList.size() - 1);
        assertThat(testGolfer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGolfer.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testGolfer.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testGolfer.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testGolfer.getResidenceCity()).isEqualTo(UPDATED_RESIDENCE_CITY);
        assertThat(testGolfer.getResidenceState()).isEqualTo(UPDATED_RESIDENCE_STATE);
        assertThat(testGolfer.getPlaysFromCity()).isEqualTo(UPDATED_PLAYS_FROM_CITY);
        assertThat(testGolfer.getPlaysFromState()).isEqualTo(UPDATED_PLAYS_FROM_STATE);
        assertThat(testGolfer.getTurnedPro()).isEqualTo(UPDATED_TURNED_PRO);
        assertThat(testGolfer.getPgaId()).isEqualTo(UPDATED_PGA_ID);

        // Validate the Golfer in Elasticsearch
        verify(mockGolferSearchRepository, times(1)).save(testGolfer);
    }

    @Test
    @Transactional
    public void updateNonExistingGolfer() throws Exception {
        int databaseSizeBeforeUpdate = golferRepository.findAll().size();

        // Create the Golfer

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGolferMockMvc.perform(put("/api/golfers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(golfer)))
            .andExpect(status().isBadRequest());

        // Validate the Golfer in the database
        List<Golfer> golferList = golferRepository.findAll();
        assertThat(golferList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Golfer in Elasticsearch
        verify(mockGolferSearchRepository, times(0)).save(golfer);
    }

    @Test
    @Transactional
    public void deleteGolfer() throws Exception {
        // Initialize the database
        golferRepository.saveAndFlush(golfer);

        int databaseSizeBeforeDelete = golferRepository.findAll().size();

        // Delete the golfer
        restGolferMockMvc.perform(delete("/api/golfers/{id}", golfer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Golfer> golferList = golferRepository.findAll();
        assertThat(golferList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Golfer in Elasticsearch
        verify(mockGolferSearchRepository, times(1)).deleteById(golfer.getId());
    }

    @Test
    @Transactional
    public void searchGolfer() throws Exception {
        // Initialize the database
        golferRepository.saveAndFlush(golfer);
        when(mockGolferSearchRepository.search(queryStringQuery("id:" + golfer.getId())))
            .thenReturn(Collections.singletonList(golfer));
        // Search the golfer
        restGolferMockMvc.perform(get("/api/_search/golfers?query=id:" + golfer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(golfer.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].residenceCity").value(hasItem(DEFAULT_RESIDENCE_CITY)))
            .andExpect(jsonPath("$.[*].residenceState").value(hasItem(DEFAULT_RESIDENCE_STATE)))
            .andExpect(jsonPath("$.[*].playsFromCity").value(hasItem(DEFAULT_PLAYS_FROM_CITY)))
            .andExpect(jsonPath("$.[*].playsFromState").value(hasItem(DEFAULT_PLAYS_FROM_STATE)))
            .andExpect(jsonPath("$.[*].turnedPro").value(hasItem(DEFAULT_TURNED_PRO)))
            .andExpect(jsonPath("$.[*].pgaId").value(hasItem(DEFAULT_PGA_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Golfer.class);
        Golfer golfer1 = new Golfer();
        golfer1.setId(1L);
        Golfer golfer2 = new Golfer();
        golfer2.setId(golfer1.getId());
        assertThat(golfer1).isEqualTo(golfer2);
        golfer2.setId(2L);
        assertThat(golfer1).isNotEqualTo(golfer2);
        golfer1.setId(null);
        assertThat(golfer1).isNotEqualTo(golfer2);
    }
}
