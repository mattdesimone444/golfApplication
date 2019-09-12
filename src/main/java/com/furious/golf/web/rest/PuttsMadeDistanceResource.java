package com.furious.golf.web.rest;

import com.furious.golf.domain.PuttsMadeDistance;
import com.furious.golf.repository.PuttsMadeDistanceRepository;
import com.furious.golf.repository.search.PuttsMadeDistanceSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.PuttsMadeDistance}.
 */
@RestController
@RequestMapping("/api")
public class PuttsMadeDistanceResource {

    private final Logger log = LoggerFactory.getLogger(PuttsMadeDistanceResource.class);

    private static final String ENTITY_NAME = "puttsMadeDistance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PuttsMadeDistanceRepository puttsMadeDistanceRepository;

    private final PuttsMadeDistanceSearchRepository puttsMadeDistanceSearchRepository;

    public PuttsMadeDistanceResource(PuttsMadeDistanceRepository puttsMadeDistanceRepository, PuttsMadeDistanceSearchRepository puttsMadeDistanceSearchRepository) {
        this.puttsMadeDistanceRepository = puttsMadeDistanceRepository;
        this.puttsMadeDistanceSearchRepository = puttsMadeDistanceSearchRepository;
    }

    /**
     * {@code POST  /putts-made-distances} : Create a new puttsMadeDistance.
     *
     * @param puttsMadeDistance the puttsMadeDistance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new puttsMadeDistance, or with status {@code 400 (Bad Request)} if the puttsMadeDistance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/putts-made-distances")
    public ResponseEntity<PuttsMadeDistance> createPuttsMadeDistance(@RequestBody PuttsMadeDistance puttsMadeDistance) throws URISyntaxException {
        log.debug("REST request to save PuttsMadeDistance : {}", puttsMadeDistance);
        if (puttsMadeDistance.getId() != null) {
            throw new BadRequestAlertException("A new puttsMadeDistance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PuttsMadeDistance result = puttsMadeDistanceRepository.save(puttsMadeDistance);
        puttsMadeDistanceSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/putts-made-distances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /putts-made-distances} : Updates an existing puttsMadeDistance.
     *
     * @param puttsMadeDistance the puttsMadeDistance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated puttsMadeDistance,
     * or with status {@code 400 (Bad Request)} if the puttsMadeDistance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the puttsMadeDistance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/putts-made-distances")
    public ResponseEntity<PuttsMadeDistance> updatePuttsMadeDistance(@RequestBody PuttsMadeDistance puttsMadeDistance) throws URISyntaxException {
        log.debug("REST request to update PuttsMadeDistance : {}", puttsMadeDistance);
        if (puttsMadeDistance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PuttsMadeDistance result = puttsMadeDistanceRepository.save(puttsMadeDistance);
        puttsMadeDistanceSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, puttsMadeDistance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /putts-made-distances} : get all the puttsMadeDistances.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of puttsMadeDistances in body.
     */
    @GetMapping("/putts-made-distances")
    public List<PuttsMadeDistance> getAllPuttsMadeDistances() {
        log.debug("REST request to get all PuttsMadeDistances");
        return puttsMadeDistanceRepository.findAll();
    }

    /**
     * {@code GET  /putts-made-distances/:id} : get the "id" puttsMadeDistance.
     *
     * @param id the id of the puttsMadeDistance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the puttsMadeDistance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/putts-made-distances/{id}")
    public ResponseEntity<PuttsMadeDistance> getPuttsMadeDistance(@PathVariable Long id) {
        log.debug("REST request to get PuttsMadeDistance : {}", id);
        Optional<PuttsMadeDistance> puttsMadeDistance = puttsMadeDistanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(puttsMadeDistance);
    }

    /**
     * {@code DELETE  /putts-made-distances/:id} : delete the "id" puttsMadeDistance.
     *
     * @param id the id of the puttsMadeDistance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/putts-made-distances/{id}")
    public ResponseEntity<Void> deletePuttsMadeDistance(@PathVariable Long id) {
        log.debug("REST request to delete PuttsMadeDistance : {}", id);
        puttsMadeDistanceRepository.deleteById(id);
        puttsMadeDistanceSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/putts-made-distances?query=:query} : search for the puttsMadeDistance corresponding
     * to the query.
     *
     * @param query the query of the puttsMadeDistance search.
     * @return the result of the search.
     */
    @GetMapping("/_search/putts-made-distances")
    public List<PuttsMadeDistance> searchPuttsMadeDistances(@RequestParam String query) {
        log.debug("REST request to search PuttsMadeDistances for query {}", query);
        return StreamSupport
            .stream(puttsMadeDistanceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
