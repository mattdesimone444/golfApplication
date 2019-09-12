package com.furious.golf.web.rest;

import com.furious.golf.domain.PuttingAnalysis;
import com.furious.golf.repository.PuttingAnalysisRepository;
import com.furious.golf.repository.search.PuttingAnalysisSearchRepository;
import com.furious.golf.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.furious.golf.domain.PuttingAnalysis}.
 */
@RestController
@RequestMapping("/api")
public class PuttingAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(PuttingAnalysisResource.class);

    private static final String ENTITY_NAME = "puttingAnalysis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuttingAnalysisRepository puttingAnalysisRepository;

    private final PuttingAnalysisSearchRepository puttingAnalysisSearchRepository;

    public PuttingAnalysisResource(PuttingAnalysisRepository puttingAnalysisRepository, PuttingAnalysisSearchRepository puttingAnalysisSearchRepository) {
        this.puttingAnalysisRepository = puttingAnalysisRepository;
        this.puttingAnalysisSearchRepository = puttingAnalysisSearchRepository;
    }

    /**
     * {@code POST  /putting-analyses} : Create a new puttingAnalysis.
     *
     * @param puttingAnalysis the puttingAnalysis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puttingAnalysis, or with status {@code 400 (Bad Request)} if the puttingAnalysis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/putting-analyses")
    public ResponseEntity<PuttingAnalysis> createPuttingAnalysis(@RequestBody PuttingAnalysis puttingAnalysis) throws URISyntaxException {
        log.debug("REST request to save PuttingAnalysis : {}", puttingAnalysis);
        if (puttingAnalysis.getId() != null) {
            throw new BadRequestAlertException("A new puttingAnalysis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PuttingAnalysis result = puttingAnalysisRepository.save(puttingAnalysis);
        puttingAnalysisSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/putting-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /putting-analyses} : Updates an existing puttingAnalysis.
     *
     * @param puttingAnalysis the puttingAnalysis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puttingAnalysis,
     * or with status {@code 400 (Bad Request)} if the puttingAnalysis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puttingAnalysis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/putting-analyses")
    public ResponseEntity<PuttingAnalysis> updatePuttingAnalysis(@RequestBody PuttingAnalysis puttingAnalysis) throws URISyntaxException {
        log.debug("REST request to update PuttingAnalysis : {}", puttingAnalysis);
        if (puttingAnalysis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PuttingAnalysis result = puttingAnalysisRepository.save(puttingAnalysis);
        puttingAnalysisSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, puttingAnalysis.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /putting-analyses} : get all the puttingAnalyses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puttingAnalyses in body.
     */
    @GetMapping("/putting-analyses")
    public List<PuttingAnalysis> getAllPuttingAnalyses() {
        log.debug("REST request to get all PuttingAnalyses");
        return puttingAnalysisRepository.findAll();
    }

    /**
     * {@code GET  /putting-analyses/:id} : get the "id" puttingAnalysis.
     *
     * @param id the id of the puttingAnalysis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puttingAnalysis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/putting-analyses/{id}")
    public ResponseEntity<PuttingAnalysis> getPuttingAnalysis(@PathVariable Long id) {
        log.debug("REST request to get PuttingAnalysis : {}", id);
        Optional<PuttingAnalysis> puttingAnalysis = puttingAnalysisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(puttingAnalysis);
    }

    /**
     * {@code DELETE  /putting-analyses/:id} : delete the "id" puttingAnalysis.
     *
     * @param id the id of the puttingAnalysis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/putting-analyses/{id}")
    public ResponseEntity<Void> deletePuttingAnalysis(@PathVariable Long id) {
        log.debug("REST request to delete PuttingAnalysis : {}", id);
        puttingAnalysisRepository.deleteById(id);
        puttingAnalysisSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/putting-analyses?query=:query} : search for the puttingAnalysis corresponding
     * to the query.
     *
     * @param query the query of the puttingAnalysis search.
     * @return the result of the search.
     */
    @GetMapping("/_search/putting-analyses")
    public List<PuttingAnalysis> searchPuttingAnalyses(@RequestParam String query) {
        log.debug("REST request to search PuttingAnalyses for query {}", query);
        return StreamSupport
            .stream(puttingAnalysisSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
