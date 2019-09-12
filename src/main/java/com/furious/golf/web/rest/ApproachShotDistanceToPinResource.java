package com.furious.golf.web.rest;

import com.furious.golf.domain.ApproachShotDistanceToPin;
import com.furious.golf.repository.ApproachShotDistanceToPinRepository;
import com.furious.golf.repository.search.ApproachShotDistanceToPinSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.ApproachShotDistanceToPin}.
 */
@RestController
@RequestMapping("/api")
public class ApproachShotDistanceToPinResource {

    private final Logger log = LoggerFactory.getLogger(ApproachShotDistanceToPinResource.class);

    private static final String ENTITY_NAME = "approachShotDistanceToPin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApproachShotDistanceToPinRepository approachShotDistanceToPinRepository;

    private final ApproachShotDistanceToPinSearchRepository approachShotDistanceToPinSearchRepository;

    public ApproachShotDistanceToPinResource(ApproachShotDistanceToPinRepository approachShotDistanceToPinRepository, ApproachShotDistanceToPinSearchRepository approachShotDistanceToPinSearchRepository) {
        this.approachShotDistanceToPinRepository = approachShotDistanceToPinRepository;
        this.approachShotDistanceToPinSearchRepository = approachShotDistanceToPinSearchRepository;
    }

    /**
     * {@code POST  /approach-shot-distance-to-pins} : Create a new approachShotDistanceToPin.
     *
     * @param approachShotDistanceToPin the approachShotDistanceToPin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new approachShotDistanceToPin, or with status {@code 400 (Bad Request)} if the approachShotDistanceToPin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/approach-shot-distance-to-pins")
    public ResponseEntity<ApproachShotDistanceToPin> createApproachShotDistanceToPin(@RequestBody ApproachShotDistanceToPin approachShotDistanceToPin) throws URISyntaxException {
        log.debug("REST request to save ApproachShotDistanceToPin : {}", approachShotDistanceToPin);
        if (approachShotDistanceToPin.getId() != null) {
            throw new BadRequestAlertException("A new approachShotDistanceToPin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApproachShotDistanceToPin result = approachShotDistanceToPinRepository.save(approachShotDistanceToPin);
        approachShotDistanceToPinSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/approach-shot-distance-to-pins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /approach-shot-distance-to-pins} : Updates an existing approachShotDistanceToPin.
     *
     * @param approachShotDistanceToPin the approachShotDistanceToPin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated approachShotDistanceToPin,
     * or with status {@code 400 (Bad Request)} if the approachShotDistanceToPin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the approachShotDistanceToPin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/approach-shot-distance-to-pins")
    public ResponseEntity<ApproachShotDistanceToPin> updateApproachShotDistanceToPin(@RequestBody ApproachShotDistanceToPin approachShotDistanceToPin) throws URISyntaxException {
        log.debug("REST request to update ApproachShotDistanceToPin : {}", approachShotDistanceToPin);
        if (approachShotDistanceToPin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApproachShotDistanceToPin result = approachShotDistanceToPinRepository.save(approachShotDistanceToPin);
        approachShotDistanceToPinSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, approachShotDistanceToPin.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /approach-shot-distance-to-pins} : get all the approachShotDistanceToPins.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of approachShotDistanceToPins in body.
     */
    @GetMapping("/approach-shot-distance-to-pins")
    public List<ApproachShotDistanceToPin> getAllApproachShotDistanceToPins() {
        log.debug("REST request to get all ApproachShotDistanceToPins");
        return approachShotDistanceToPinRepository.findAll();
    }

    /**
     * {@code GET  /approach-shot-distance-to-pins/:id} : get the "id" approachShotDistanceToPin.
     *
     * @param id the id of the approachShotDistanceToPin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the approachShotDistanceToPin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/approach-shot-distance-to-pins/{id}")
    public ResponseEntity<ApproachShotDistanceToPin> getApproachShotDistanceToPin(@PathVariable Long id) {
        log.debug("REST request to get ApproachShotDistanceToPin : {}", id);
        Optional<ApproachShotDistanceToPin> approachShotDistanceToPin = approachShotDistanceToPinRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(approachShotDistanceToPin);
    }

    /**
     * {@code DELETE  /approach-shot-distance-to-pins/:id} : delete the "id" approachShotDistanceToPin.
     *
     * @param id the id of the approachShotDistanceToPin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/approach-shot-distance-to-pins/{id}")
    public ResponseEntity<Void> deleteApproachShotDistanceToPin(@PathVariable Long id) {
        log.debug("REST request to delete ApproachShotDistanceToPin : {}", id);
        approachShotDistanceToPinRepository.deleteById(id);
        approachShotDistanceToPinSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/approach-shot-distance-to-pins?query=:query} : search for the approachShotDistanceToPin corresponding
     * to the query.
     *
     * @param query the query of the approachShotDistanceToPin search.
     * @return the result of the search.
     */
    @GetMapping("/_search/approach-shot-distance-to-pins")
    public List<ApproachShotDistanceToPin> searchApproachShotDistanceToPins(@RequestParam String query) {
        log.debug("REST request to search ApproachShotDistanceToPins for query {}", query);
        return StreamSupport
            .stream(approachShotDistanceToPinSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
