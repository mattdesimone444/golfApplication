package com.furious.golf.web.rest;

import com.furious.golf.domain.SGApproach;
import com.furious.golf.repository.SGApproachRepository;
import com.furious.golf.repository.search.SGApproachSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGApproach}.
 */
@RestController
@RequestMapping("/api")
public class SGApproachResource {

    private final Logger log = LoggerFactory.getLogger(SGApproachResource.class);

    private static final String ENTITY_NAME = "sGApproach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGApproachRepository sGApproachRepository;

    private final SGApproachSearchRepository sGApproachSearchRepository;

    public SGApproachResource(SGApproachRepository sGApproachRepository, SGApproachSearchRepository sGApproachSearchRepository) {
        this.sGApproachRepository = sGApproachRepository;
        this.sGApproachSearchRepository = sGApproachSearchRepository;
    }

    /**
     * {@code POST  /sg-approaches} : Create a new sGApproach.
     *
     * @param sGApproach the sGApproach to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGApproach, or with status {@code 400 (Bad Request)} if the sGApproach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-approaches")
    public ResponseEntity<SGApproach> createSGApproach(@RequestBody SGApproach sGApproach) throws URISyntaxException {
        log.debug("REST request to save SGApproach : {}", sGApproach);
        if (sGApproach.getId() != null) {
            throw new BadRequestAlertException("A new sGApproach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGApproach result = sGApproachRepository.save(sGApproach);
        sGApproachSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-approaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-approaches} : Updates an existing sGApproach.
     *
     * @param sGApproach the sGApproach to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGApproach,
     * or with status {@code 400 (Bad Request)} if the sGApproach is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGApproach couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-approaches")
    public ResponseEntity<SGApproach> updateSGApproach(@RequestBody SGApproach sGApproach) throws URISyntaxException {
        log.debug("REST request to update SGApproach : {}", sGApproach);
        if (sGApproach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGApproach result = sGApproachRepository.save(sGApproach);
        sGApproachSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGApproach.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-approaches} : get all the sGApproaches.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGApproaches in body.
     */
    @GetMapping("/sg-approaches")
    public List<SGApproach> getAllSGApproaches() {
        log.debug("REST request to get all SGApproaches");
        return sGApproachRepository.findAll();
    }

    /**
     * {@code GET  /sg-approaches/:id} : get the "id" sGApproach.
     *
     * @param id the id of the sGApproach to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGApproach, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-approaches/{id}")
    public ResponseEntity<SGApproach> getSGApproach(@PathVariable Long id) {
        log.debug("REST request to get SGApproach : {}", id);
        Optional<SGApproach> sGApproach = sGApproachRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGApproach);
    }

    /**
     * {@code DELETE  /sg-approaches/:id} : delete the "id" sGApproach.
     *
     * @param id the id of the sGApproach to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-approaches/{id}")
    public ResponseEntity<Void> deleteSGApproach(@PathVariable Long id) {
        log.debug("REST request to delete SGApproach : {}", id);
        sGApproachRepository.deleteById(id);
        sGApproachSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-approaches?query=:query} : search for the sGApproach corresponding
     * to the query.
     *
     * @param query the query of the sGApproach search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-approaches")
    public List<SGApproach> searchSGApproaches(@RequestParam String query) {
        log.debug("REST request to search SGApproaches for query {}", query);
        return StreamSupport
            .stream(sGApproachSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
