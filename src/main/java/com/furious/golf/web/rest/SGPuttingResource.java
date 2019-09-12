package com.furious.golf.web.rest;

import com.furious.golf.domain.SGPutting;
import com.furious.golf.repository.SGPuttingRepository;
import com.furious.golf.repository.search.SGPuttingSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGPutting}.
 */
@RestController
@RequestMapping("/api")
public class SGPuttingResource {

    private final Logger log = LoggerFactory.getLogger(SGPuttingResource.class);

    private static final String ENTITY_NAME = "sGPutting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGPuttingRepository sGPuttingRepository;

    private final SGPuttingSearchRepository sGPuttingSearchRepository;

    public SGPuttingResource(SGPuttingRepository sGPuttingRepository, SGPuttingSearchRepository sGPuttingSearchRepository) {
        this.sGPuttingRepository = sGPuttingRepository;
        this.sGPuttingSearchRepository = sGPuttingSearchRepository;
    }

    /**
     * {@code POST  /sg-puttings} : Create a new sGPutting.
     *
     * @param sGPutting the sGPutting to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGPutting, or with status {@code 400 (Bad Request)} if the sGPutting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-puttings")
    public ResponseEntity<SGPutting> createSGPutting(@RequestBody SGPutting sGPutting) throws URISyntaxException {
        log.debug("REST request to save SGPutting : {}", sGPutting);
        if (sGPutting.getId() != null) {
            throw new BadRequestAlertException("A new sGPutting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGPutting result = sGPuttingRepository.save(sGPutting);
        sGPuttingSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-puttings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-puttings} : Updates an existing sGPutting.
     *
     * @param sGPutting the sGPutting to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGPutting,
     * or with status {@code 400 (Bad Request)} if the sGPutting is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGPutting couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-puttings")
    public ResponseEntity<SGPutting> updateSGPutting(@RequestBody SGPutting sGPutting) throws URISyntaxException {
        log.debug("REST request to update SGPutting : {}", sGPutting);
        if (sGPutting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGPutting result = sGPuttingRepository.save(sGPutting);
        sGPuttingSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGPutting.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-puttings} : get all the sGPuttings.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGPuttings in body.
     */
    @GetMapping("/sg-puttings")
    public List<SGPutting> getAllSGPuttings() {
        log.debug("REST request to get all SGPuttings");
        return sGPuttingRepository.findAll();
    }

    /**
     * {@code GET  /sg-puttings/:id} : get the "id" sGPutting.
     *
     * @param id the id of the sGPutting to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGPutting, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-puttings/{id}")
    public ResponseEntity<SGPutting> getSGPutting(@PathVariable Long id) {
        log.debug("REST request to get SGPutting : {}", id);
        Optional<SGPutting> sGPutting = sGPuttingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGPutting);
    }

    /**
     * {@code DELETE  /sg-puttings/:id} : delete the "id" sGPutting.
     *
     * @param id the id of the sGPutting to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-puttings/{id}")
    public ResponseEntity<Void> deleteSGPutting(@PathVariable Long id) {
        log.debug("REST request to delete SGPutting : {}", id);
        sGPuttingRepository.deleteById(id);
        sGPuttingSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-puttings?query=:query} : search for the sGPutting corresponding
     * to the query.
     *
     * @param query the query of the sGPutting search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-puttings")
    public List<SGPutting> searchSGPuttings(@RequestParam String query) {
        log.debug("REST request to search SGPuttings for query {}", query);
        return StreamSupport
            .stream(sGPuttingSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
