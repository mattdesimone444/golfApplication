package com.furious.golf.web.rest;

import com.furious.golf.domain.DrivingDistance;
import com.furious.golf.repository.DrivingDistanceRepository;
import com.furious.golf.repository.search.DrivingDistanceSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.DrivingDistance}.
 */
@RestController
@RequestMapping("/api")
public class DrivingDistanceResource {

    private final Logger log = LoggerFactory.getLogger(DrivingDistanceResource.class);

    private static final String ENTITY_NAME = "drivingDistance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DrivingDistanceRepository drivingDistanceRepository;

    private final DrivingDistanceSearchRepository drivingDistanceSearchRepository;

    public DrivingDistanceResource(DrivingDistanceRepository drivingDistanceRepository, DrivingDistanceSearchRepository drivingDistanceSearchRepository) {
        this.drivingDistanceRepository = drivingDistanceRepository;
        this.drivingDistanceSearchRepository = drivingDistanceSearchRepository;
    }

    /**
     * {@code POST  /driving-distances} : Create a new drivingDistance.
     *
     * @param drivingDistance the drivingDistance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new drivingDistance, or with status {@code 400 (Bad Request)} if the drivingDistance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/driving-distances")
    public ResponseEntity<DrivingDistance> createDrivingDistance(@RequestBody DrivingDistance drivingDistance) throws URISyntaxException {
        log.debug("REST request to save DrivingDistance : {}", drivingDistance);
        if (drivingDistance.getId() != null) {
            throw new BadRequestAlertException("A new drivingDistance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DrivingDistance result = drivingDistanceRepository.save(drivingDistance);
        drivingDistanceSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/driving-distances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /driving-distances} : Updates an existing drivingDistance.
     *
     * @param drivingDistance the drivingDistance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drivingDistance,
     * or with status {@code 400 (Bad Request)} if the drivingDistance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the drivingDistance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/driving-distances")
    public ResponseEntity<DrivingDistance> updateDrivingDistance(@RequestBody DrivingDistance drivingDistance) throws URISyntaxException {
        log.debug("REST request to update DrivingDistance : {}", drivingDistance);
        if (drivingDistance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DrivingDistance result = drivingDistanceRepository.save(drivingDistance);
        drivingDistanceSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, drivingDistance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /driving-distances} : get all the drivingDistances.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drivingDistances in body.
     */
    @GetMapping("/driving-distances")
    public List<DrivingDistance> getAllDrivingDistances() {
        log.debug("REST request to get all DrivingDistances");
        return drivingDistanceRepository.findAll();
    }

    /**
     * {@code GET  /driving-distances/:id} : get the "id" drivingDistance.
     *
     * @param id the id of the drivingDistance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the drivingDistance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/driving-distances/{id}")
    public ResponseEntity<DrivingDistance> getDrivingDistance(@PathVariable Long id) {
        log.debug("REST request to get DrivingDistance : {}", id);
        Optional<DrivingDistance> drivingDistance = drivingDistanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(drivingDistance);
    }

    /**
     * {@code DELETE  /driving-distances/:id} : delete the "id" drivingDistance.
     *
     * @param id the id of the drivingDistance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/driving-distances/{id}")
    public ResponseEntity<Void> deleteDrivingDistance(@PathVariable Long id) {
        log.debug("REST request to delete DrivingDistance : {}", id);
        drivingDistanceRepository.deleteById(id);
        drivingDistanceSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/driving-distances?query=:query} : search for the drivingDistance corresponding
     * to the query.
     *
     * @param query the query of the drivingDistance search.
     * @return the result of the search.
     */
    @GetMapping("/_search/driving-distances")
    public List<DrivingDistance> searchDrivingDistances(@RequestParam String query) {
        log.debug("REST request to search DrivingDistances for query {}", query);
        return StreamSupport
            .stream(drivingDistanceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
