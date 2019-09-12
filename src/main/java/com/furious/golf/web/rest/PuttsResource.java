package com.furious.golf.web.rest;

import com.furious.golf.domain.Putts;
import com.furious.golf.repository.PuttsRepository;
import com.furious.golf.repository.search.PuttsSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.Putts}.
 */
@RestController
@RequestMapping("/api")
public class PuttsResource {

    private final Logger log = LoggerFactory.getLogger(PuttsResource.class);

    private static final String ENTITY_NAME = "putts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuttsRepository puttsRepository;

    private final PuttsSearchRepository puttsSearchRepository;

    public PuttsResource(PuttsRepository puttsRepository, PuttsSearchRepository puttsSearchRepository) {
        this.puttsRepository = puttsRepository;
        this.puttsSearchRepository = puttsSearchRepository;
    }

    /**
     * {@code POST  /putts} : Create a new putts.
     *
     * @param putts the putts to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new putts, or with status {@code 400 (Bad Request)} if the putts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/putts")
    public ResponseEntity<Putts> createPutts(@RequestBody Putts putts) throws URISyntaxException {
        log.debug("REST request to save Putts : {}", putts);
        if (putts.getId() != null) {
            throw new BadRequestAlertException("A new putts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Putts result = puttsRepository.save(putts);
        puttsSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/putts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /putts} : Updates an existing putts.
     *
     * @param putts the putts to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated putts,
     * or with status {@code 400 (Bad Request)} if the putts is not valid,
     * or with status {@code 500 (Internal Server Error)} if the putts couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/putts")
    public ResponseEntity<Putts> updatePutts(@RequestBody Putts putts) throws URISyntaxException {
        log.debug("REST request to update Putts : {}", putts);
        if (putts.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Putts result = puttsRepository.save(putts);
        puttsSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, putts.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /putts} : get all the putts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of putts in body.
     */
    @GetMapping("/putts")
    public List<Putts> getAllPutts() {
        log.debug("REST request to get all Putts");
        return puttsRepository.findAll();
    }

    /**
     * {@code GET  /putts/:id} : get the "id" putts.
     *
     * @param id the id of the putts to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the putts, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/putts/{id}")
    public ResponseEntity<Putts> getPutts(@PathVariable Long id) {
        log.debug("REST request to get Putts : {}", id);
        Optional<Putts> putts = puttsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(putts);
    }

    /**
     * {@code DELETE  /putts/:id} : delete the "id" putts.
     *
     * @param id the id of the putts to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/putts/{id}")
    public ResponseEntity<Void> deletePutts(@PathVariable Long id) {
        log.debug("REST request to delete Putts : {}", id);
        puttsRepository.deleteById(id);
        puttsSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/putts?query=:query} : search for the putts corresponding
     * to the query.
     *
     * @param query the query of the putts search.
     * @return the result of the search.
     */
    @GetMapping("/_search/putts")
    public List<Putts> searchPutts(@RequestParam String query) {
        log.debug("REST request to search Putts for query {}", query);
        return StreamSupport
            .stream(puttsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
