package com.furious.golf.web.rest;

import com.furious.golf.domain.SGTeeToGreen;
import com.furious.golf.repository.SGTeeToGreenRepository;
import com.furious.golf.repository.search.SGTeeToGreenSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGTeeToGreen}.
 */
@RestController
@RequestMapping("/api")
public class SGTeeToGreenResource {

    private final Logger log = LoggerFactory.getLogger(SGTeeToGreenResource.class);

    private static final String ENTITY_NAME = "sGTeeToGreen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGTeeToGreenRepository sGTeeToGreenRepository;

    private final SGTeeToGreenSearchRepository sGTeeToGreenSearchRepository;

    public SGTeeToGreenResource(SGTeeToGreenRepository sGTeeToGreenRepository, SGTeeToGreenSearchRepository sGTeeToGreenSearchRepository) {
        this.sGTeeToGreenRepository = sGTeeToGreenRepository;
        this.sGTeeToGreenSearchRepository = sGTeeToGreenSearchRepository;
    }

    /**
     * {@code POST  /sg-tee-to-greens} : Create a new sGTeeToGreen.
     *
     * @param sGTeeToGreen the sGTeeToGreen to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGTeeToGreen, or with status {@code 400 (Bad Request)} if the sGTeeToGreen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-tee-to-greens")
    public ResponseEntity<SGTeeToGreen> createSGTeeToGreen(@RequestBody SGTeeToGreen sGTeeToGreen) throws URISyntaxException {
        log.debug("REST request to save SGTeeToGreen : {}", sGTeeToGreen);
        if (sGTeeToGreen.getId() != null) {
            throw new BadRequestAlertException("A new sGTeeToGreen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGTeeToGreen result = sGTeeToGreenRepository.save(sGTeeToGreen);
        sGTeeToGreenSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-tee-to-greens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-tee-to-greens} : Updates an existing sGTeeToGreen.
     *
     * @param sGTeeToGreen the sGTeeToGreen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGTeeToGreen,
     * or with status {@code 400 (Bad Request)} if the sGTeeToGreen is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGTeeToGreen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-tee-to-greens")
    public ResponseEntity<SGTeeToGreen> updateSGTeeToGreen(@RequestBody SGTeeToGreen sGTeeToGreen) throws URISyntaxException {
        log.debug("REST request to update SGTeeToGreen : {}", sGTeeToGreen);
        if (sGTeeToGreen.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGTeeToGreen result = sGTeeToGreenRepository.save(sGTeeToGreen);
        sGTeeToGreenSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGTeeToGreen.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-tee-to-greens} : get all the sGTeeToGreens.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGTeeToGreens in body.
     */
    @GetMapping("/sg-tee-to-greens")
    public List<SGTeeToGreen> getAllSGTeeToGreens() {
        log.debug("REST request to get all SGTeeToGreens");
        return sGTeeToGreenRepository.findAll();
    }

    /**
     * {@code GET  /sg-tee-to-greens/:id} : get the "id" sGTeeToGreen.
     *
     * @param id the id of the sGTeeToGreen to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGTeeToGreen, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-tee-to-greens/{id}")
    public ResponseEntity<SGTeeToGreen> getSGTeeToGreen(@PathVariable Long id) {
        log.debug("REST request to get SGTeeToGreen : {}", id);
        Optional<SGTeeToGreen> sGTeeToGreen = sGTeeToGreenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGTeeToGreen);
    }

    /**
     * {@code DELETE  /sg-tee-to-greens/:id} : delete the "id" sGTeeToGreen.
     *
     * @param id the id of the sGTeeToGreen to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-tee-to-greens/{id}")
    public ResponseEntity<Void> deleteSGTeeToGreen(@PathVariable Long id) {
        log.debug("REST request to delete SGTeeToGreen : {}", id);
        sGTeeToGreenRepository.deleteById(id);
        sGTeeToGreenSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-tee-to-greens?query=:query} : search for the sGTeeToGreen corresponding
     * to the query.
     *
     * @param query the query of the sGTeeToGreen search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-tee-to-greens")
    public List<SGTeeToGreen> searchSGTeeToGreens(@RequestParam String query) {
        log.debug("REST request to search SGTeeToGreens for query {}", query);
        return StreamSupport
            .stream(sGTeeToGreenSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
