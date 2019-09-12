package com.furious.golf.web.rest;

import com.furious.golf.domain.SandSaves;
import com.furious.golf.repository.SandSavesRepository;
import com.furious.golf.repository.search.SandSavesSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SandSaves}.
 */
@RestController
@RequestMapping("/api")
public class SandSavesResource {

    private final Logger log = LoggerFactory.getLogger(SandSavesResource.class);

    private static final String ENTITY_NAME = "sandSaves";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SandSavesRepository sandSavesRepository;

    private final SandSavesSearchRepository sandSavesSearchRepository;

    public SandSavesResource(SandSavesRepository sandSavesRepository, SandSavesSearchRepository sandSavesSearchRepository) {
        this.sandSavesRepository = sandSavesRepository;
        this.sandSavesSearchRepository = sandSavesSearchRepository;
    }

    /**
     * {@code POST  /sand-saves} : Create a new sandSaves.
     *
     * @param sandSaves the sandSaves to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sandSaves, or with status {@code 400 (Bad Request)} if the sandSaves has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sand-saves")
    public ResponseEntity<SandSaves> createSandSaves(@RequestBody SandSaves sandSaves) throws URISyntaxException {
        log.debug("REST request to save SandSaves : {}", sandSaves);
        if (sandSaves.getId() != null) {
            throw new BadRequestAlertException("A new sandSaves cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SandSaves result = sandSavesRepository.save(sandSaves);
        sandSavesSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sand-saves/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sand-saves} : Updates an existing sandSaves.
     *
     * @param sandSaves the sandSaves to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sandSaves,
     * or with status {@code 400 (Bad Request)} if the sandSaves is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sandSaves couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sand-saves")
    public ResponseEntity<SandSaves> updateSandSaves(@RequestBody SandSaves sandSaves) throws URISyntaxException {
        log.debug("REST request to update SandSaves : {}", sandSaves);
        if (sandSaves.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SandSaves result = sandSavesRepository.save(sandSaves);
        sandSavesSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sandSaves.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sand-saves} : get all the sandSaves.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sandSaves in body.
     */
    @GetMapping("/sand-saves")
    public List<SandSaves> getAllSandSaves() {
        log.debug("REST request to get all SandSaves");
        return sandSavesRepository.findAll();
    }

    /**
     * {@code GET  /sand-saves/:id} : get the "id" sandSaves.
     *
     * @param id the id of the sandSaves to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sandSaves, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sand-saves/{id}")
    public ResponseEntity<SandSaves> getSandSaves(@PathVariable Long id) {
        log.debug("REST request to get SandSaves : {}", id);
        Optional<SandSaves> sandSaves = sandSavesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sandSaves);
    }

    /**
     * {@code DELETE  /sand-saves/:id} : delete the "id" sandSaves.
     *
     * @param id the id of the sandSaves to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sand-saves/{id}")
    public ResponseEntity<Void> deleteSandSaves(@PathVariable Long id) {
        log.debug("REST request to delete SandSaves : {}", id);
        sandSavesRepository.deleteById(id);
        sandSavesSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sand-saves?query=:query} : search for the sandSaves corresponding
     * to the query.
     *
     * @param query the query of the sandSaves search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sand-saves")
    public List<SandSaves> searchSandSaves(@RequestParam String query) {
        log.debug("REST request to search SandSaves for query {}", query);
        return StreamSupport
            .stream(sandSavesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
