package com.furious.golf.web.rest;

import com.furious.golf.domain.TournamentPuttingAnalysis;
import com.furious.golf.repository.TournamentPuttingAnalysisRepository;
import com.furious.golf.repository.search.TournamentPuttingAnalysisSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.TournamentPuttingAnalysis}.
 */
@RestController
@RequestMapping("/api")
public class TournamentPuttingAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(TournamentPuttingAnalysisResource.class);

    private static final String ENTITY_NAME = "tournamentPuttingAnalysis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TournamentPuttingAnalysisRepository tournamentPuttingAnalysisRepository;

    private final TournamentPuttingAnalysisSearchRepository tournamentPuttingAnalysisSearchRepository;

    public TournamentPuttingAnalysisResource(TournamentPuttingAnalysisRepository tournamentPuttingAnalysisRepository, TournamentPuttingAnalysisSearchRepository tournamentPuttingAnalysisSearchRepository) {
        this.tournamentPuttingAnalysisRepository = tournamentPuttingAnalysisRepository;
        this.tournamentPuttingAnalysisSearchRepository = tournamentPuttingAnalysisSearchRepository;
    }

    /**
     * {@code POST  /tournament-putting-analyses} : Create a new tournamentPuttingAnalysis.
     *
     * @param tournamentPuttingAnalysis the tournamentPuttingAnalysis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tournamentPuttingAnalysis, or with status {@code 400 (Bad Request)} if the tournamentPuttingAnalysis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tournament-putting-analyses")
    public ResponseEntity<TournamentPuttingAnalysis> createTournamentPuttingAnalysis(@RequestBody TournamentPuttingAnalysis tournamentPuttingAnalysis) throws URISyntaxException {
        log.debug("REST request to save TournamentPuttingAnalysis : {}", tournamentPuttingAnalysis);
        if (tournamentPuttingAnalysis.getId() != null) {
            throw new BadRequestAlertException("A new tournamentPuttingAnalysis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TournamentPuttingAnalysis result = tournamentPuttingAnalysisRepository.save(tournamentPuttingAnalysis);
        tournamentPuttingAnalysisSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tournament-putting-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tournament-putting-analyses} : Updates an existing tournamentPuttingAnalysis.
     *
     * @param tournamentPuttingAnalysis the tournamentPuttingAnalysis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tournamentPuttingAnalysis,
     * or with status {@code 400 (Bad Request)} if the tournamentPuttingAnalysis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tournamentPuttingAnalysis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tournament-putting-analyses")
    public ResponseEntity<TournamentPuttingAnalysis> updateTournamentPuttingAnalysis(@RequestBody TournamentPuttingAnalysis tournamentPuttingAnalysis) throws URISyntaxException {
        log.debug("REST request to update TournamentPuttingAnalysis : {}", tournamentPuttingAnalysis);
        if (tournamentPuttingAnalysis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TournamentPuttingAnalysis result = tournamentPuttingAnalysisRepository.save(tournamentPuttingAnalysis);
        tournamentPuttingAnalysisSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tournamentPuttingAnalysis.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tournament-putting-analyses} : get all the tournamentPuttingAnalyses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tournamentPuttingAnalyses in body.
     */
    @GetMapping("/tournament-putting-analyses")
    public List<TournamentPuttingAnalysis> getAllTournamentPuttingAnalyses() {
        log.debug("REST request to get all TournamentPuttingAnalyses");
        return tournamentPuttingAnalysisRepository.findAll();
    }

    /**
     * {@code GET  /tournament-putting-analyses/:id} : get the "id" tournamentPuttingAnalysis.
     *
     * @param id the id of the tournamentPuttingAnalysis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tournamentPuttingAnalysis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tournament-putting-analyses/{id}")
    public ResponseEntity<TournamentPuttingAnalysis> getTournamentPuttingAnalysis(@PathVariable Long id) {
        log.debug("REST request to get TournamentPuttingAnalysis : {}", id);
        Optional<TournamentPuttingAnalysis> tournamentPuttingAnalysis = tournamentPuttingAnalysisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tournamentPuttingAnalysis);
    }

    /**
     * {@code DELETE  /tournament-putting-analyses/:id} : delete the "id" tournamentPuttingAnalysis.
     *
     * @param id the id of the tournamentPuttingAnalysis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tournament-putting-analyses/{id}")
    public ResponseEntity<Void> deleteTournamentPuttingAnalysis(@PathVariable Long id) {
        log.debug("REST request to delete TournamentPuttingAnalysis : {}", id);
        tournamentPuttingAnalysisRepository.deleteById(id);
        tournamentPuttingAnalysisSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tournament-putting-analyses?query=:query} : search for the tournamentPuttingAnalysis corresponding
     * to the query.
     *
     * @param query the query of the tournamentPuttingAnalysis search.
     * @return the result of the search.
     */
    @GetMapping("/_search/tournament-putting-analyses")
    public List<TournamentPuttingAnalysis> searchTournamentPuttingAnalyses(@RequestParam String query) {
        log.debug("REST request to search TournamentPuttingAnalyses for query {}", query);
        return StreamSupport
            .stream(tournamentPuttingAnalysisSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
