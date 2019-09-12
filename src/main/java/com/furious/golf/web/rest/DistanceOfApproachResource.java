package com.furious.golf.web.rest;

import com.furious.golf.domain.DistanceOfApproach;
import com.furious.golf.repository.DistanceOfApproachRepository;
import com.furious.golf.repository.search.DistanceOfApproachSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.DistanceOfApproach}.
 */
@RestController
@RequestMapping("/api")
public class DistanceOfApproachResource {

    private final Logger log = LoggerFactory.getLogger(DistanceOfApproachResource.class);

    private static final String ENTITY_NAME = "distanceOfApproach";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DistanceOfApproachRepository distanceOfApproachRepository;

    private final DistanceOfApproachSearchRepository distanceOfApproachSearchRepository;

    public DistanceOfApproachResource(DistanceOfApproachRepository distanceOfApproachRepository, DistanceOfApproachSearchRepository distanceOfApproachSearchRepository) {
        this.distanceOfApproachRepository = distanceOfApproachRepository;
        this.distanceOfApproachSearchRepository = distanceOfApproachSearchRepository;
    }

    /**
     * {@code POST  /distance-of-approaches} : Create a new distanceOfApproach.
     *
     * @param distanceOfApproach the distanceOfApproach to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new distanceOfApproach, or with status {@code 400 (Bad Request)} if the distanceOfApproach has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/distance-of-approaches")
    public ResponseEntity<DistanceOfApproach> createDistanceOfApproach(@RequestBody DistanceOfApproach distanceOfApproach) throws URISyntaxException {
        log.debug("REST request to save DistanceOfApproach : {}", distanceOfApproach);
        if (distanceOfApproach.getId() != null) {
            throw new BadRequestAlertException("A new distanceOfApproach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DistanceOfApproach result = distanceOfApproachRepository.save(distanceOfApproach);
        distanceOfApproachSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/distance-of-approaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /distance-of-approaches} : Updates an existing distanceOfApproach.
     *
     * @param distanceOfApproach the distanceOfApproach to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated distanceOfApproach,
     * or with status {@code 400 (Bad Request)} if the distanceOfApproach is not valid,
     * or with status {@code 500 (Internal Server Error)} if the distanceOfApproach couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/distance-of-approaches")
    public ResponseEntity<DistanceOfApproach> updateDistanceOfApproach(@RequestBody DistanceOfApproach distanceOfApproach) throws URISyntaxException {
        log.debug("REST request to update DistanceOfApproach : {}", distanceOfApproach);
        if (distanceOfApproach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DistanceOfApproach result = distanceOfApproachRepository.save(distanceOfApproach);
        distanceOfApproachSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, distanceOfApproach.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /distance-of-approaches} : get all the distanceOfApproaches.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of distanceOfApproaches in body.
     */
    @GetMapping("/distance-of-approaches")
    public List<DistanceOfApproach> getAllDistanceOfApproaches() {
        log.debug("REST request to get all DistanceOfApproaches");
        return distanceOfApproachRepository.findAll();
    }

    /**
     * {@code GET  /distance-of-approaches/:id} : get the "id" distanceOfApproach.
     *
     * @param id the id of the distanceOfApproach to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the distanceOfApproach, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/distance-of-approaches/{id}")
    public ResponseEntity<DistanceOfApproach> getDistanceOfApproach(@PathVariable Long id) {
        log.debug("REST request to get DistanceOfApproach : {}", id);
        Optional<DistanceOfApproach> distanceOfApproach = distanceOfApproachRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(distanceOfApproach);
    }

    /**
     * {@code DELETE  /distance-of-approaches/:id} : delete the "id" distanceOfApproach.
     *
     * @param id the id of the distanceOfApproach to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/distance-of-approaches/{id}")
    public ResponseEntity<Void> deleteDistanceOfApproach(@PathVariable Long id) {
        log.debug("REST request to delete DistanceOfApproach : {}", id);
        distanceOfApproachRepository.deleteById(id);
        distanceOfApproachSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/distance-of-approaches?query=:query} : search for the distanceOfApproach corresponding
     * to the query.
     *
     * @param query the query of the distanceOfApproach search.
     * @return the result of the search.
     */
    @GetMapping("/_search/distance-of-approaches")
    public List<DistanceOfApproach> searchDistanceOfApproaches(@RequestParam String query) {
        log.debug("REST request to search DistanceOfApproaches for query {}", query);
        return StreamSupport
            .stream(distanceOfApproachSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
