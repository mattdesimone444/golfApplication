package com.furious.golf.web.rest;

import com.furious.golf.domain.SGArroundTheGreen;
import com.furious.golf.repository.SGArroundTheGreenRepository;
import com.furious.golf.repository.search.SGArroundTheGreenSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGArroundTheGreen}.
 */
@RestController
@RequestMapping("/api")
public class SGArroundTheGreenResource {

    private final Logger log = LoggerFactory.getLogger(SGArroundTheGreenResource.class);

    private static final String ENTITY_NAME = "sGArroundTheGreen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGArroundTheGreenRepository sGArroundTheGreenRepository;

    private final SGArroundTheGreenSearchRepository sGArroundTheGreenSearchRepository;

    public SGArroundTheGreenResource(SGArroundTheGreenRepository sGArroundTheGreenRepository, SGArroundTheGreenSearchRepository sGArroundTheGreenSearchRepository) {
        this.sGArroundTheGreenRepository = sGArroundTheGreenRepository;
        this.sGArroundTheGreenSearchRepository = sGArroundTheGreenSearchRepository;
    }

    /**
     * {@code POST  /sg-arround-the-greens} : Create a new sGArroundTheGreen.
     *
     * @param sGArroundTheGreen the sGArroundTheGreen to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGArroundTheGreen, or with status {@code 400 (Bad Request)} if the sGArroundTheGreen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-arround-the-greens")
    public ResponseEntity<SGArroundTheGreen> createSGArroundTheGreen(@RequestBody SGArroundTheGreen sGArroundTheGreen) throws URISyntaxException {
        log.debug("REST request to save SGArroundTheGreen : {}", sGArroundTheGreen);
        if (sGArroundTheGreen.getId() != null) {
            throw new BadRequestAlertException("A new sGArroundTheGreen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGArroundTheGreen result = sGArroundTheGreenRepository.save(sGArroundTheGreen);
        sGArroundTheGreenSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-arround-the-greens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-arround-the-greens} : Updates an existing sGArroundTheGreen.
     *
     * @param sGArroundTheGreen the sGArroundTheGreen to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGArroundTheGreen,
     * or with status {@code 400 (Bad Request)} if the sGArroundTheGreen is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGArroundTheGreen couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-arround-the-greens")
    public ResponseEntity<SGArroundTheGreen> updateSGArroundTheGreen(@RequestBody SGArroundTheGreen sGArroundTheGreen) throws URISyntaxException {
        log.debug("REST request to update SGArroundTheGreen : {}", sGArroundTheGreen);
        if (sGArroundTheGreen.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGArroundTheGreen result = sGArroundTheGreenRepository.save(sGArroundTheGreen);
        sGArroundTheGreenSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGArroundTheGreen.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-arround-the-greens} : get all the sGArroundTheGreens.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGArroundTheGreens in body.
     */
    @GetMapping("/sg-arround-the-greens")
    public List<SGArroundTheGreen> getAllSGArroundTheGreens() {
        log.debug("REST request to get all SGArroundTheGreens");
        return sGArroundTheGreenRepository.findAll();
    }

    /**
     * {@code GET  /sg-arround-the-greens/:id} : get the "id" sGArroundTheGreen.
     *
     * @param id the id of the sGArroundTheGreen to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGArroundTheGreen, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-arround-the-greens/{id}")
    public ResponseEntity<SGArroundTheGreen> getSGArroundTheGreen(@PathVariable Long id) {
        log.debug("REST request to get SGArroundTheGreen : {}", id);
        Optional<SGArroundTheGreen> sGArroundTheGreen = sGArroundTheGreenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGArroundTheGreen);
    }

    /**
     * {@code DELETE  /sg-arround-the-greens/:id} : delete the "id" sGArroundTheGreen.
     *
     * @param id the id of the sGArroundTheGreen to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-arround-the-greens/{id}")
    public ResponseEntity<Void> deleteSGArroundTheGreen(@PathVariable Long id) {
        log.debug("REST request to delete SGArroundTheGreen : {}", id);
        sGArroundTheGreenRepository.deleteById(id);
        sGArroundTheGreenSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-arround-the-greens?query=:query} : search for the sGArroundTheGreen corresponding
     * to the query.
     *
     * @param query the query of the sGArroundTheGreen search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-arround-the-greens")
    public List<SGArroundTheGreen> searchSGArroundTheGreens(@RequestParam String query) {
        log.debug("REST request to search SGArroundTheGreens for query {}", query);
        return StreamSupport
            .stream(sGArroundTheGreenSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
