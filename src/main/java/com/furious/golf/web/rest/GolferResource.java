package com.furious.golf.web.rest;

import com.furious.golf.domain.Golfer;
import com.furious.golf.repository.GolferRepository;
import com.furious.golf.repository.search.GolferSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.Golfer}.
 */
@RestController
@RequestMapping("/api")
public class GolferResource {

    private final Logger log = LoggerFactory.getLogger(GolferResource.class);

    private static final String ENTITY_NAME = "golfer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GolferRepository golferRepository;

    private final GolferSearchRepository golferSearchRepository;

    public GolferResource(GolferRepository golferRepository, GolferSearchRepository golferSearchRepository) {
        this.golferRepository = golferRepository;
        this.golferSearchRepository = golferSearchRepository;
    }

    /**
     * {@code POST  /golfers} : Create a new golfer.
     *
     * @param golfer the golfer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new golfer, or with status {@code 400 (Bad Request)} if the golfer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/golfers")
    public ResponseEntity<Golfer> createGolfer(@RequestBody Golfer golfer) throws URISyntaxException {
        log.debug("REST request to save Golfer : {}", golfer);
        if (golfer.getId() != null) {
            throw new BadRequestAlertException("A new golfer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Golfer result = golferRepository.save(golfer);
        golferSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/golfers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /golfers} : Updates an existing golfer.
     *
     * @param golfer the golfer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated golfer,
     * or with status {@code 400 (Bad Request)} if the golfer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the golfer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/golfers")
    public ResponseEntity<Golfer> updateGolfer(@RequestBody Golfer golfer) throws URISyntaxException {
        log.debug("REST request to update Golfer : {}", golfer);
        if (golfer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Golfer result = golferRepository.save(golfer);
        golferSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, golfer.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /golfers} : get all the golfers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of golfers in body.
     */
    @GetMapping("/golfers")
    public List<Golfer> getAllGolfers() {
        log.debug("REST request to get all Golfers");
        return golferRepository.findAll();
    }

    /**
     * {@code GET  /golfers/:id} : get the "id" golfer.
     *
     * @param id the id of the golfer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the golfer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/golfers/{id}")
    public ResponseEntity<Golfer> getGolfer(@PathVariable Long id) {
        log.debug("REST request to get Golfer : {}", id);
        Optional<Golfer> golfer = golferRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(golfer);
    }

    /**
     * {@code DELETE  /golfers/:id} : delete the "id" golfer.
     *
     * @param id the id of the golfer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/golfers/{id}")
    public ResponseEntity<Void> deleteGolfer(@PathVariable Long id) {
        log.debug("REST request to delete Golfer : {}", id);
        golferRepository.deleteById(id);
        golferSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/golfers?query=:query} : search for the golfer corresponding
     * to the query.
     *
     * @param query the query of the golfer search.
     * @return the result of the search.
     */
    @GetMapping("/_search/golfers")
    public List<Golfer> searchGolfers(@RequestParam String query) {
        log.debug("REST request to search Golfers for query {}", query);
        return StreamSupport
            .stream(golferSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
