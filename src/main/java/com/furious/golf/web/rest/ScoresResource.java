package com.furious.golf.web.rest;

import com.furious.golf.domain.Scores;
import com.furious.golf.repository.ScoresRepository;
import com.furious.golf.repository.search.ScoresSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.Scores}.
 */
@RestController
@RequestMapping("/api")
public class ScoresResource {

    private final Logger log = LoggerFactory.getLogger(ScoresResource.class);

    private static final String ENTITY_NAME = "scores";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScoresRepository scoresRepository;

    private final ScoresSearchRepository scoresSearchRepository;

    public ScoresResource(ScoresRepository scoresRepository, ScoresSearchRepository scoresSearchRepository) {
        this.scoresRepository = scoresRepository;
        this.scoresSearchRepository = scoresSearchRepository;
    }

    /**
     * {@code POST  /scores} : Create a new scores.
     *
     * @param scores the scores to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scores, or with status {@code 400 (Bad Request)} if the scores has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/scores")
    public ResponseEntity<Scores> createScores(@RequestBody Scores scores) throws URISyntaxException {
        log.debug("REST request to save Scores : {}", scores);
        if (scores.getId() != null) {
            throw new BadRequestAlertException("A new scores cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Scores result = scoresRepository.save(scores);
        scoresSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /scores} : Updates an existing scores.
     *
     * @param scores the scores to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scores,
     * or with status {@code 400 (Bad Request)} if the scores is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scores couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/scores")
    public ResponseEntity<Scores> updateScores(@RequestBody Scores scores) throws URISyntaxException {
        log.debug("REST request to update Scores : {}", scores);
        if (scores.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Scores result = scoresRepository.save(scores);
        scoresSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, scores.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /scores} : get all the scores.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scores in body.
     */
    @GetMapping("/scores")
    public List<Scores> getAllScores() {
        log.debug("REST request to get all Scores");
        return scoresRepository.findAll();
    }

    /**
     * {@code GET  /scores/:id} : get the "id" scores.
     *
     * @param id the id of the scores to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scores, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/scores/{id}")
    public ResponseEntity<Scores> getScores(@PathVariable Long id) {
        log.debug("REST request to get Scores : {}", id);
        Optional<Scores> scores = scoresRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scores);
    }

    /**
     * {@code DELETE  /scores/:id} : delete the "id" scores.
     *
     * @param id the id of the scores to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Void> deleteScores(@PathVariable Long id) {
        log.debug("REST request to delete Scores : {}", id);
        scoresRepository.deleteById(id);
        scoresSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/scores?query=:query} : search for the scores corresponding
     * to the query.
     *
     * @param query the query of the scores search.
     * @return the result of the search.
     */
    @GetMapping("/_search/scores")
    public List<Scores> searchScores(@RequestParam String query) {
        log.debug("REST request to search Scores for query {}", query);
        return StreamSupport
            .stream(scoresSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
