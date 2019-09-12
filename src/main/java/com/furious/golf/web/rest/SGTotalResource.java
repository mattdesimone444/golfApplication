package com.furious.golf.web.rest;

import com.furious.golf.domain.SGTotal;
import com.furious.golf.repository.SGTotalRepository;
import com.furious.golf.repository.search.SGTotalSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGTotal}.
 */
@RestController
@RequestMapping("/api")
public class SGTotalResource {

    private final Logger log = LoggerFactory.getLogger(SGTotalResource.class);

    private static final String ENTITY_NAME = "sGTotal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGTotalRepository sGTotalRepository;

    private final SGTotalSearchRepository sGTotalSearchRepository;

    public SGTotalResource(SGTotalRepository sGTotalRepository, SGTotalSearchRepository sGTotalSearchRepository) {
        this.sGTotalRepository = sGTotalRepository;
        this.sGTotalSearchRepository = sGTotalSearchRepository;
    }

    /**
     * {@code POST  /sg-totals} : Create a new sGTotal.
     *
     * @param sGTotal the sGTotal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGTotal, or with status {@code 400 (Bad Request)} if the sGTotal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-totals")
    public ResponseEntity<SGTotal> createSGTotal(@RequestBody SGTotal sGTotal) throws URISyntaxException {
        log.debug("REST request to save SGTotal : {}", sGTotal);
        if (sGTotal.getId() != null) {
            throw new BadRequestAlertException("A new sGTotal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGTotal result = sGTotalRepository.save(sGTotal);
        sGTotalSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-totals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-totals} : Updates an existing sGTotal.
     *
     * @param sGTotal the sGTotal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGTotal,
     * or with status {@code 400 (Bad Request)} if the sGTotal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGTotal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-totals")
    public ResponseEntity<SGTotal> updateSGTotal(@RequestBody SGTotal sGTotal) throws URISyntaxException {
        log.debug("REST request to update SGTotal : {}", sGTotal);
        if (sGTotal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGTotal result = sGTotalRepository.save(sGTotal);
        sGTotalSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGTotal.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-totals} : get all the sGTotals.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGTotals in body.
     */
    @GetMapping("/sg-totals")
    public List<SGTotal> getAllSGTotals() {
        log.debug("REST request to get all SGTotals");
        return sGTotalRepository.findAll();
    }

    /**
     * {@code GET  /sg-totals/:id} : get the "id" sGTotal.
     *
     * @param id the id of the sGTotal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGTotal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-totals/{id}")
    public ResponseEntity<SGTotal> getSGTotal(@PathVariable Long id) {
        log.debug("REST request to get SGTotal : {}", id);
        Optional<SGTotal> sGTotal = sGTotalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGTotal);
    }

    /**
     * {@code DELETE  /sg-totals/:id} : delete the "id" sGTotal.
     *
     * @param id the id of the sGTotal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-totals/{id}")
    public ResponseEntity<Void> deleteSGTotal(@PathVariable Long id) {
        log.debug("REST request to delete SGTotal : {}", id);
        sGTotalRepository.deleteById(id);
        sGTotalSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-totals?query=:query} : search for the sGTotal corresponding
     * to the query.
     *
     * @param query the query of the sGTotal search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-totals")
    public List<SGTotal> searchSGTotals(@RequestParam String query) {
        log.debug("REST request to search SGTotals for query {}", query);
        return StreamSupport
            .stream(sGTotalSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
