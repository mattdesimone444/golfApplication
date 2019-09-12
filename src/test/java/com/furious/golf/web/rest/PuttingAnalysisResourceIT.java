package com.furious.golf.web.rest;

import com.furious.golf.GolfApplicationApp;
import com.furious.golf.domain.PuttingAnalysis;
import com.furious.golf.repository.PuttingAnalysisRepository;
import com.furious.golf.repository.search.PuttingAnalysisSearchRepository;
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
 * Integration tests for the {@link PuttingAnalysisResource} REST controller.
 */
@SpringBootTest(classes = GolfApplicationApp.class)
public class PuttingAnalysisResourceIT {

    private static final Long DEFAULT_PUTTIN_ANALYSIS_ID = 1L;
    private static final Long UPDATED_PUTTIN_ANALYSIS_ID = 2L;
    private static final Long SMALLER_PUTTIN_ANALYSIS_ID = 1L - 1L;

    private static final String DEFAULT_LONGEST = "AAAAAAAAAA";
    private static final String UPDATED_LONGEST = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL = "BBBBBBBBBB";

    private static final String DEFAULT_LESS_THREE = "AAAAAAAAAA";
    private static final String UPDATED_LESS_THREE = "BBBBBBBBBB";

    private static final String DEFAULT_LESS_TEN = "AAAAAAAAAA";
    private static final String UPDATED_LESS_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_THREE_TO_FIVE = "AAAAAAAAAA";
    private static final String UPDATED_THREE_TO_FIVE = "BBBBBBBBBB";

    private static final String DEFAULT_FIVE_TO_SEVEN = "AAAAAAAAAA";
    private static final String UPDATED_FIVE_TO_SEVEN = "BBBBBBBBBB";

    private static final String DEFAULT_SEVEN_TO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_SEVEN_TO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_FOUR_TO_EIGHT = "AAAAAAAAAA";
    private static final String UPDATED_FOUR_TO_EIGHT = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_TO_FIFTEEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN_TO_FIFTEEN = "BBBBBBBBBB";

    private static final String DEFAULT_FIFTEEN_TO_TWENTY = "AAAAAAAAAA";
    private static final String UPDATED_FIFTEEN_TO_TWENTY = "BBBBBBBBBB";

    private static final String DEFAULT_TWENTY_TO_TWENTY_FIVE = "AAAAAAAAAA";
    private static final String UPDATED_TWENTY_TO_TWENTY_FIVE = "BBBBBBBBBB";

    private static final String DEFAULT_LESS_TWENTY_FIVE = "AAAAAAAAAA";
    private static final String UPDATED_LESS_TWENTY_FIVE = "BBBBBBBBBB";

    @Autowired
    private PuttingAnalysisRepository puttingAnalysisRepository;

    /**
     * This repository is mocked in the com.furious.golf.repository.search test package.
     *
     * @see com.furious.golf.repository.search.PuttingAnalysisSearchRepositoryMockConfiguration
     */
    @Autowired
    private PuttingAnalysisSearchRepository mockPuttingAnalysisSearchRepository;

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

    private MockMvc restPuttingAnalysisMockMvc;

    private PuttingAnalysis puttingAnalysis;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PuttingAnalysisResource puttingAnalysisResource = new PuttingAnalysisResource(puttingAnalysisRepository, mockPuttingAnalysisSearchRepository);
        this.restPuttingAnalysisMockMvc = MockMvcBuilders.standaloneSetup(puttingAnalysisResource)
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
    public static PuttingAnalysis createEntity(EntityManager em) {
        PuttingAnalysis puttingAnalysis = new PuttingAnalysis()
            .puttinAnalysisId(DEFAULT_PUTTIN_ANALYSIS_ID)
            .longest(DEFAULT_LONGEST)
            .total(DEFAULT_TOTAL)
            .lessThree(DEFAULT_LESS_THREE)
            .lessTen(DEFAULT_LESS_TEN)
            .threeToFive(DEFAULT_THREE_TO_FIVE)
            .fiveToSeven(DEFAULT_FIVE_TO_SEVEN)
            .sevenToTen(DEFAULT_SEVEN_TO_TEN)
            .fourToEight(DEFAULT_FOUR_TO_EIGHT)
            .tenToFifteen(DEFAULT_TEN_TO_FIFTEEN)
            .fifteenToTwenty(DEFAULT_FIFTEEN_TO_TWENTY)
            .twentyToTwentyFive(DEFAULT_TWENTY_TO_TWENTY_FIVE)
            .lessTwentyFive(DEFAULT_LESS_TWENTY_FIVE);
        return puttingAnalysis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PuttingAnalysis createUpdatedEntity(EntityManager em) {
        PuttingAnalysis puttingAnalysis = new PuttingAnalysis()
            .puttinAnalysisId(UPDATED_PUTTIN_ANALYSIS_ID)
            .longest(UPDATED_LONGEST)
            .total(UPDATED_TOTAL)
            .lessThree(UPDATED_LESS_THREE)
            .lessTen(UPDATED_LESS_TEN)
            .threeToFive(UPDATED_THREE_TO_FIVE)
            .fiveToSeven(UPDATED_FIVE_TO_SEVEN)
            .sevenToTen(UPDATED_SEVEN_TO_TEN)
            .fourToEight(UPDATED_FOUR_TO_EIGHT)
            .tenToFifteen(UPDATED_TEN_TO_FIFTEEN)
            .fifteenToTwenty(UPDATED_FIFTEEN_TO_TWENTY)
            .twentyToTwentyFive(UPDATED_TWENTY_TO_TWENTY_FIVE)
            .lessTwentyFive(UPDATED_LESS_TWENTY_FIVE);
        return puttingAnalysis;
    }

    @BeforeEach
    public void initTest() {
        puttingAnalysis = createEntity(em);
    }

    @Test
    @Transactional
    public void createPuttingAnalysis() throws Exception {
        int databaseSizeBeforeCreate = puttingAnalysisRepository.findAll().size();

        // Create the PuttingAnalysis
        restPuttingAnalysisMockMvc.perform(post("/api/putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puttingAnalysis)))
            .andExpect(status().isCreated());

        // Validate the PuttingAnalysis in the database
        List<PuttingAnalysis> puttingAnalysisList = puttingAnalysisRepository.findAll();
        assertThat(puttingAnalysisList).hasSize(databaseSizeBeforeCreate + 1);
        PuttingAnalysis testPuttingAnalysis = puttingAnalysisList.get(puttingAnalysisList.size() - 1);
        assertThat(testPuttingAnalysis.getPuttinAnalysisId()).isEqualTo(DEFAULT_PUTTIN_ANALYSIS_ID);
        assertThat(testPuttingAnalysis.getLongest()).isEqualTo(DEFAULT_LONGEST);
        assertThat(testPuttingAnalysis.getTotal()).isEqualTo(DEFAULT_TOTAL);
        assertThat(testPuttingAnalysis.getLessThree()).isEqualTo(DEFAULT_LESS_THREE);
        assertThat(testPuttingAnalysis.getLessTen()).isEqualTo(DEFAULT_LESS_TEN);
        assertThat(testPuttingAnalysis.getThreeToFive()).isEqualTo(DEFAULT_THREE_TO_FIVE);
        assertThat(testPuttingAnalysis.getFiveToSeven()).isEqualTo(DEFAULT_FIVE_TO_SEVEN);
        assertThat(testPuttingAnalysis.getSevenToTen()).isEqualTo(DEFAULT_SEVEN_TO_TEN);
        assertThat(testPuttingAnalysis.getFourToEight()).isEqualTo(DEFAULT_FOUR_TO_EIGHT);
        assertThat(testPuttingAnalysis.getTenToFifteen()).isEqualTo(DEFAULT_TEN_TO_FIFTEEN);
        assertThat(testPuttingAnalysis.getFifteenToTwenty()).isEqualTo(DEFAULT_FIFTEEN_TO_TWENTY);
        assertThat(testPuttingAnalysis.getTwentyToTwentyFive()).isEqualTo(DEFAULT_TWENTY_TO_TWENTY_FIVE);
        assertThat(testPuttingAnalysis.getLessTwentyFive()).isEqualTo(DEFAULT_LESS_TWENTY_FIVE);

        // Validate the PuttingAnalysis in Elasticsearch
        verify(mockPuttingAnalysisSearchRepository, times(1)).save(testPuttingAnalysis);
    }

    @Test
    @Transactional
    public void createPuttingAnalysisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = puttingAnalysisRepository.findAll().size();

        // Create the PuttingAnalysis with an existing ID
        puttingAnalysis.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPuttingAnalysisMockMvc.perform(post("/api/putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puttingAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the PuttingAnalysis in the database
        List<PuttingAnalysis> puttingAnalysisList = puttingAnalysisRepository.findAll();
        assertThat(puttingAnalysisList).hasSize(databaseSizeBeforeCreate);

        // Validate the PuttingAnalysis in Elasticsearch
        verify(mockPuttingAnalysisSearchRepository, times(0)).save(puttingAnalysis);
    }


    @Test
    @Transactional
    public void getAllPuttingAnalyses() throws Exception {
        // Initialize the database
        puttingAnalysisRepository.saveAndFlush(puttingAnalysis);

        // Get all the puttingAnalysisList
        restPuttingAnalysisMockMvc.perform(get("/api/putting-analyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puttingAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].puttinAnalysisId").value(hasItem(DEFAULT_PUTTIN_ANALYSIS_ID.intValue())))
            .andExpect(jsonPath("$.[*].longest").value(hasItem(DEFAULT_LONGEST.toString())))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL.toString())))
            .andExpect(jsonPath("$.[*].lessThree").value(hasItem(DEFAULT_LESS_THREE.toString())))
            .andExpect(jsonPath("$.[*].lessTen").value(hasItem(DEFAULT_LESS_TEN.toString())))
            .andExpect(jsonPath("$.[*].threeToFive").value(hasItem(DEFAULT_THREE_TO_FIVE.toString())))
            .andExpect(jsonPath("$.[*].fiveToSeven").value(hasItem(DEFAULT_FIVE_TO_SEVEN.toString())))
            .andExpect(jsonPath("$.[*].sevenToTen").value(hasItem(DEFAULT_SEVEN_TO_TEN.toString())))
            .andExpect(jsonPath("$.[*].fourToEight").value(hasItem(DEFAULT_FOUR_TO_EIGHT.toString())))
            .andExpect(jsonPath("$.[*].tenToFifteen").value(hasItem(DEFAULT_TEN_TO_FIFTEEN.toString())))
            .andExpect(jsonPath("$.[*].fifteenToTwenty").value(hasItem(DEFAULT_FIFTEEN_TO_TWENTY.toString())))
            .andExpect(jsonPath("$.[*].twentyToTwentyFive").value(hasItem(DEFAULT_TWENTY_TO_TWENTY_FIVE.toString())))
            .andExpect(jsonPath("$.[*].lessTwentyFive").value(hasItem(DEFAULT_LESS_TWENTY_FIVE.toString())));
    }
    
    @Test
    @Transactional
    public void getPuttingAnalysis() throws Exception {
        // Initialize the database
        puttingAnalysisRepository.saveAndFlush(puttingAnalysis);

        // Get the puttingAnalysis
        restPuttingAnalysisMockMvc.perform(get("/api/putting-analyses/{id}", puttingAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(puttingAnalysis.getId().intValue()))
            .andExpect(jsonPath("$.puttinAnalysisId").value(DEFAULT_PUTTIN_ANALYSIS_ID.intValue()))
            .andExpect(jsonPath("$.longest").value(DEFAULT_LONGEST.toString()))
            .andExpect(jsonPath("$.total").value(DEFAULT_TOTAL.toString()))
            .andExpect(jsonPath("$.lessThree").value(DEFAULT_LESS_THREE.toString()))
            .andExpect(jsonPath("$.lessTen").value(DEFAULT_LESS_TEN.toString()))
            .andExpect(jsonPath("$.threeToFive").value(DEFAULT_THREE_TO_FIVE.toString()))
            .andExpect(jsonPath("$.fiveToSeven").value(DEFAULT_FIVE_TO_SEVEN.toString()))
            .andExpect(jsonPath("$.sevenToTen").value(DEFAULT_SEVEN_TO_TEN.toString()))
            .andExpect(jsonPath("$.fourToEight").value(DEFAULT_FOUR_TO_EIGHT.toString()))
            .andExpect(jsonPath("$.tenToFifteen").value(DEFAULT_TEN_TO_FIFTEEN.toString()))
            .andExpect(jsonPath("$.fifteenToTwenty").value(DEFAULT_FIFTEEN_TO_TWENTY.toString()))
            .andExpect(jsonPath("$.twentyToTwentyFive").value(DEFAULT_TWENTY_TO_TWENTY_FIVE.toString()))
            .andExpect(jsonPath("$.lessTwentyFive").value(DEFAULT_LESS_TWENTY_FIVE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPuttingAnalysis() throws Exception {
        // Get the puttingAnalysis
        restPuttingAnalysisMockMvc.perform(get("/api/putting-analyses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePuttingAnalysis() throws Exception {
        // Initialize the database
        puttingAnalysisRepository.saveAndFlush(puttingAnalysis);

        int databaseSizeBeforeUpdate = puttingAnalysisRepository.findAll().size();

        // Update the puttingAnalysis
        PuttingAnalysis updatedPuttingAnalysis = puttingAnalysisRepository.findById(puttingAnalysis.getId()).get();
        // Disconnect from session so that the updates on updatedPuttingAnalysis are not directly saved in db
        em.detach(updatedPuttingAnalysis);
        updatedPuttingAnalysis
            .puttinAnalysisId(UPDATED_PUTTIN_ANALYSIS_ID)
            .longest(UPDATED_LONGEST)
            .total(UPDATED_TOTAL)
            .lessThree(UPDATED_LESS_THREE)
            .lessTen(UPDATED_LESS_TEN)
            .threeToFive(UPDATED_THREE_TO_FIVE)
            .fiveToSeven(UPDATED_FIVE_TO_SEVEN)
            .sevenToTen(UPDATED_SEVEN_TO_TEN)
            .fourToEight(UPDATED_FOUR_TO_EIGHT)
            .tenToFifteen(UPDATED_TEN_TO_FIFTEEN)
            .fifteenToTwenty(UPDATED_FIFTEEN_TO_TWENTY)
            .twentyToTwentyFive(UPDATED_TWENTY_TO_TWENTY_FIVE)
            .lessTwentyFive(UPDATED_LESS_TWENTY_FIVE);

        restPuttingAnalysisMockMvc.perform(put("/api/putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPuttingAnalysis)))
            .andExpect(status().isOk());

        // Validate the PuttingAnalysis in the database
        List<PuttingAnalysis> puttingAnalysisList = puttingAnalysisRepository.findAll();
        assertThat(puttingAnalysisList).hasSize(databaseSizeBeforeUpdate);
        PuttingAnalysis testPuttingAnalysis = puttingAnalysisList.get(puttingAnalysisList.size() - 1);
        assertThat(testPuttingAnalysis.getPuttinAnalysisId()).isEqualTo(UPDATED_PUTTIN_ANALYSIS_ID);
        assertThat(testPuttingAnalysis.getLongest()).isEqualTo(UPDATED_LONGEST);
        assertThat(testPuttingAnalysis.getTotal()).isEqualTo(UPDATED_TOTAL);
        assertThat(testPuttingAnalysis.getLessThree()).isEqualTo(UPDATED_LESS_THREE);
        assertThat(testPuttingAnalysis.getLessTen()).isEqualTo(UPDATED_LESS_TEN);
        assertThat(testPuttingAnalysis.getThreeToFive()).isEqualTo(UPDATED_THREE_TO_FIVE);
        assertThat(testPuttingAnalysis.getFiveToSeven()).isEqualTo(UPDATED_FIVE_TO_SEVEN);
        assertThat(testPuttingAnalysis.getSevenToTen()).isEqualTo(UPDATED_SEVEN_TO_TEN);
        assertThat(testPuttingAnalysis.getFourToEight()).isEqualTo(UPDATED_FOUR_TO_EIGHT);
        assertThat(testPuttingAnalysis.getTenToFifteen()).isEqualTo(UPDATED_TEN_TO_FIFTEEN);
        assertThat(testPuttingAnalysis.getFifteenToTwenty()).isEqualTo(UPDATED_FIFTEEN_TO_TWENTY);
        assertThat(testPuttingAnalysis.getTwentyToTwentyFive()).isEqualTo(UPDATED_TWENTY_TO_TWENTY_FIVE);
        assertThat(testPuttingAnalysis.getLessTwentyFive()).isEqualTo(UPDATED_LESS_TWENTY_FIVE);

        // Validate the PuttingAnalysis in Elasticsearch
        verify(mockPuttingAnalysisSearchRepository, times(1)).save(testPuttingAnalysis);
    }

    @Test
    @Transactional
    public void updateNonExistingPuttingAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = puttingAnalysisRepository.findAll().size();

        // Create the PuttingAnalysis

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPuttingAnalysisMockMvc.perform(put("/api/putting-analyses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(puttingAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the PuttingAnalysis in the database
        List<PuttingAnalysis> puttingAnalysisList = puttingAnalysisRepository.findAll();
        assertThat(puttingAnalysisList).hasSize(databaseSizeBeforeUpdate);

        // Validate the PuttingAnalysis in Elasticsearch
        verify(mockPuttingAnalysisSearchRepository, times(0)).save(puttingAnalysis);
    }

    @Test
    @Transactional
    public void deletePuttingAnalysis() throws Exception {
        // Initialize the database
        puttingAnalysisRepository.saveAndFlush(puttingAnalysis);

        int databaseSizeBeforeDelete = puttingAnalysisRepository.findAll().size();

        // Delete the puttingAnalysis
        restPuttingAnalysisMockMvc.perform(delete("/api/putting-analyses/{id}", puttingAnalysis.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PuttingAnalysis> puttingAnalysisList = puttingAnalysisRepository.findAll();
        assertThat(puttingAnalysisList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the PuttingAnalysis in Elasticsearch
        verify(mockPuttingAnalysisSearchRepository, times(1)).deleteById(puttingAnalysis.getId());
    }

    @Test
    @Transactional
    public void searchPuttingAnalysis() throws Exception {
        // Initialize the database
        puttingAnalysisRepository.saveAndFlush(puttingAnalysis);
        when(mockPuttingAnalysisSearchRepository.search(queryStringQuery("id:" + puttingAnalysis.getId())))
            .thenReturn(Collections.singletonList(puttingAnalysis));
        // Search the puttingAnalysis
        restPuttingAnalysisMockMvc.perform(get("/api/_search/putting-analyses?query=id:" + puttingAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(puttingAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].puttinAnalysisId").value(hasItem(DEFAULT_PUTTIN_ANALYSIS_ID.intValue())))
            .andExpect(jsonPath("$.[*].longest").value(hasItem(DEFAULT_LONGEST)))
            .andExpect(jsonPath("$.[*].total").value(hasItem(DEFAULT_TOTAL)))
            .andExpect(jsonPath("$.[*].lessThree").value(hasItem(DEFAULT_LESS_THREE)))
            .andExpect(jsonPath("$.[*].lessTen").value(hasItem(DEFAULT_LESS_TEN)))
            .andExpect(jsonPath("$.[*].threeToFive").value(hasItem(DEFAULT_THREE_TO_FIVE)))
            .andExpect(jsonPath("$.[*].fiveToSeven").value(hasItem(DEFAULT_FIVE_TO_SEVEN)))
            .andExpect(jsonPath("$.[*].sevenToTen").value(hasItem(DEFAULT_SEVEN_TO_TEN)))
            .andExpect(jsonPath("$.[*].fourToEight").value(hasItem(DEFAULT_FOUR_TO_EIGHT)))
            .andExpect(jsonPath("$.[*].tenToFifteen").value(hasItem(DEFAULT_TEN_TO_FIFTEEN)))
            .andExpect(jsonPath("$.[*].fifteenToTwenty").value(hasItem(DEFAULT_FIFTEEN_TO_TWENTY)))
            .andExpect(jsonPath("$.[*].twentyToTwentyFive").value(hasItem(DEFAULT_TWENTY_TO_TWENTY_FIVE)))
            .andExpect(jsonPath("$.[*].lessTwentyFive").value(hasItem(DEFAULT_LESS_TWENTY_FIVE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PuttingAnalysis.class);
        PuttingAnalysis puttingAnalysis1 = new PuttingAnalysis();
        puttingAnalysis1.setId(1L);
        PuttingAnalysis puttingAnalysis2 = new PuttingAnalysis();
        puttingAnalysis2.setId(puttingAnalysis1.getId());
        assertThat(puttingAnalysis1).isEqualTo(puttingAnalysis2);
        puttingAnalysis2.setId(2L);
        assertThat(puttingAnalysis1).isNotEqualTo(puttingAnalysis2);
        puttingAnalysis1.setId(null);
        assertThat(puttingAnalysis1).isNotEqualTo(puttingAnalysis2);
    }
}
