package com.furious.golf.web.rest;

import com.furious.golf.domain.SGOffTheTee;
import com.furious.golf.repository.SGOffTheTeeRepository;
import com.furious.golf.repository.search.SGOffTheTeeSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.SGOffTheTee}.
 */
@RestController
@RequestMapping("/api")
public class SGOffTheTeeResource {

    private final Logger log = LoggerFactory.getLogger(SGOffTheTeeResource.class);

    private static final String ENTITY_NAME = "sGOffTheTee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SGOffTheTeeRepository sGOffTheTeeRepository;

    private final SGOffTheTeeSearchRepository sGOffTheTeeSearchRepository;

    public SGOffTheTeeResource(SGOffTheTeeRepository sGOffTheTeeRepository, SGOffTheTeeSearchRepository sGOffTheTeeSearchRepository) {
        this.sGOffTheTeeRepository = sGOffTheTeeRepository;
        this.sGOffTheTeeSearchRepository = sGOffTheTeeSearchRepository;
    }

    /**
     * {@code POST  /sg-off-the-tees} : Create a new sGOffTheTee.
     *
     * @param sGOffTheTee the sGOffTheTee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sGOffTheTee, or with status {@code 400 (Bad Request)} if the sGOffTheTee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sg-off-the-tees")
    public ResponseEntity<SGOffTheTee> createSGOffTheTee(@RequestBody SGOffTheTee sGOffTheTee) throws URISyntaxException {
        log.debug("REST request to save SGOffTheTee : {}", sGOffTheTee);
        if (sGOffTheTee.getId() != null) {
            throw new BadRequestAlertException("A new sGOffTheTee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SGOffTheTee result = sGOffTheTeeRepository.save(sGOffTheTee);
        sGOffTheTeeSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sg-off-the-tees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sg-off-the-tees} : Updates an existing sGOffTheTee.
     *
     * @param sGOffTheTee the sGOffTheTee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sGOffTheTee,
     * or with status {@code 400 (Bad Request)} if the sGOffTheTee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sGOffTheTee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sg-off-the-tees")
    public ResponseEntity<SGOffTheTee> updateSGOffTheTee(@RequestBody SGOffTheTee sGOffTheTee) throws URISyntaxException {
        log.debug("REST request to update SGOffTheTee : {}", sGOffTheTee);
        if (sGOffTheTee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SGOffTheTee result = sGOffTheTeeRepository.save(sGOffTheTee);
        sGOffTheTeeSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sGOffTheTee.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sg-off-the-tees} : get all the sGOffTheTees.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sGOffTheTees in body.
     */
    @GetMapping("/sg-off-the-tees")
    public List<SGOffTheTee> getAllSGOffTheTees() {
        log.debug("REST request to get all SGOffTheTees");
        return sGOffTheTeeRepository.findAll();
    }

    /**
     * {@code GET  /sg-off-the-tees/:id} : get the "id" sGOffTheTee.
     *
     * @param id the id of the sGOffTheTee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sGOffTheTee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sg-off-the-tees/{id}")
    public ResponseEntity<SGOffTheTee> getSGOffTheTee(@PathVariable Long id) {
        log.debug("REST request to get SGOffTheTee : {}", id);
        Optional<SGOffTheTee> sGOffTheTee = sGOffTheTeeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sGOffTheTee);
    }

    /**
     * {@code DELETE  /sg-off-the-tees/:id} : delete the "id" sGOffTheTee.
     *
     * @param id the id of the sGOffTheTee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sg-off-the-tees/{id}")
    public ResponseEntity<Void> deleteSGOffTheTee(@PathVariable Long id) {
        log.debug("REST request to delete SGOffTheTee : {}", id);
        sGOffTheTeeRepository.deleteById(id);
        sGOffTheTeeSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/sg-off-the-tees?query=:query} : search for the sGOffTheTee corresponding
     * to the query.
     *
     * @param query the query of the sGOffTheTee search.
     * @return the result of the search.
     */
    @GetMapping("/_search/sg-off-the-tees")
    public List<SGOffTheTee> searchSGOffTheTees(@RequestParam String query) {
        log.debug("REST request to search SGOffTheTees for query {}", query);
        return StreamSupport
            .stream(sGOffTheTeeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
