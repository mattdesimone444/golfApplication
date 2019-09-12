package com.furious.golf.web.rest;

import com.furious.golf.domain.FairwaysHit;
import com.furious.golf.repository.FairwaysHitRepository;
import com.furious.golf.repository.search.FairwaysHitSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.FairwaysHit}.
 */
@RestController
@RequestMapping("/api")
public class FairwaysHitResource {

    private final Logger log = LoggerFactory.getLogger(FairwaysHitResource.class);

    private static final String ENTITY_NAME = "fairwaysHit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FairwaysHitRepository fairwaysHitRepository;

    private final FairwaysHitSearchRepository fairwaysHitSearchRepository;

    public FairwaysHitResource(FairwaysHitRepository fairwaysHitRepository, FairwaysHitSearchRepository fairwaysHitSearchRepository) {
        this.fairwaysHitRepository = fairwaysHitRepository;
        this.fairwaysHitSearchRepository = fairwaysHitSearchRepository;
    }

    /**
     * {@code POST  /fairways-hits} : Create a new fairwaysHit.
     *
     * @param fairwaysHit the fairwaysHit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fairwaysHit, or with status {@code 400 (Bad Request)} if the fairwaysHit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fairways-hits")
    public ResponseEntity<FairwaysHit> createFairwaysHit(@RequestBody FairwaysHit fairwaysHit) throws URISyntaxException {
        log.debug("REST request to save FairwaysHit : {}", fairwaysHit);
        if (fairwaysHit.getId() != null) {
            throw new BadRequestAlertException("A new fairwaysHit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FairwaysHit result = fairwaysHitRepository.save(fairwaysHit);
        fairwaysHitSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/fairways-hits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fairways-hits} : Updates an existing fairwaysHit.
     *
     * @param fairwaysHit the fairwaysHit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fairwaysHit,
     * or with status {@code 400 (Bad Request)} if the fairwaysHit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fairwaysHit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fairways-hits")
    public ResponseEntity<FairwaysHit> updateFairwaysHit(@RequestBody FairwaysHit fairwaysHit) throws URISyntaxException {
        log.debug("REST request to update FairwaysHit : {}", fairwaysHit);
        if (fairwaysHit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FairwaysHit result = fairwaysHitRepository.save(fairwaysHit);
        fairwaysHitSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fairwaysHit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fairways-hits} : get all the fairwaysHits.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fairwaysHits in body.
     */
    @GetMapping("/fairways-hits")
    public List<FairwaysHit> getAllFairwaysHits() {
        log.debug("REST request to get all FairwaysHits");
        return fairwaysHitRepository.findAll();
    }

    /**
     * {@code GET  /fairways-hits/:id} : get the "id" fairwaysHit.
     *
     * @param id the id of the fairwaysHit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fairwaysHit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fairways-hits/{id}")
    public ResponseEntity<FairwaysHit> getFairwaysHit(@PathVariable Long id) {
        log.debug("REST request to get FairwaysHit : {}", id);
        Optional<FairwaysHit> fairwaysHit = fairwaysHitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fairwaysHit);
    }

    /**
     * {@code DELETE  /fairways-hits/:id} : delete the "id" fairwaysHit.
     *
     * @param id the id of the fairwaysHit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fairways-hits/{id}")
    public ResponseEntity<Void> deleteFairwaysHit(@PathVariable Long id) {
        log.debug("REST request to delete FairwaysHit : {}", id);
        fairwaysHitRepository.deleteById(id);
        fairwaysHitSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/fairways-hits?query=:query} : search for the fairwaysHit corresponding
     * to the query.
     *
     * @param query the query of the fairwaysHit search.
     * @return the result of the search.
     */
    @GetMapping("/_search/fairways-hits")
    public List<FairwaysHit> searchFairwaysHits(@RequestParam String query) {
        log.debug("REST request to search FairwaysHits for query {}", query);
        return StreamSupport
            .stream(fairwaysHitSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
