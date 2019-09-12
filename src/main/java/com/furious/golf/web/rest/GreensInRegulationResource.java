package com.furious.golf.web.rest;

import com.furious.golf.domain.GreensInRegulation;
import com.furious.golf.repository.GreensInRegulationRepository;
import com.furious.golf.repository.search.GreensInRegulationSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.GreensInRegulation}.
 */
@RestController
@RequestMapping("/api")
public class GreensInRegulationResource {

    private final Logger log = LoggerFactory.getLogger(GreensInRegulationResource.class);

    private static final String ENTITY_NAME = "greensInRegulation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GreensInRegulationRepository greensInRegulationRepository;

    private final GreensInRegulationSearchRepository greensInRegulationSearchRepository;

    public GreensInRegulationResource(GreensInRegulationRepository greensInRegulationRepository, GreensInRegulationSearchRepository greensInRegulationSearchRepository) {
        this.greensInRegulationRepository = greensInRegulationRepository;
        this.greensInRegulationSearchRepository = greensInRegulationSearchRepository;
    }

    /**
     * {@code POST  /greens-in-regulations} : Create a new greensInRegulation.
     *
     * @param greensInRegulation the greensInRegulation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new greensInRegulation, or with status {@code 400 (Bad Request)} if the greensInRegulation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/greens-in-regulations")
    public ResponseEntity<GreensInRegulation> createGreensInRegulation(@RequestBody GreensInRegulation greensInRegulation) throws URISyntaxException {
        log.debug("REST request to save GreensInRegulation : {}", greensInRegulation);
        if (greensInRegulation.getId() != null) {
            throw new BadRequestAlertException("A new greensInRegulation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GreensInRegulation result = greensInRegulationRepository.save(greensInRegulation);
        greensInRegulationSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/greens-in-regulations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /greens-in-regulations} : Updates an existing greensInRegulation.
     *
     * @param greensInRegulation the greensInRegulation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated greensInRegulation,
     * or with status {@code 400 (Bad Request)} if the greensInRegulation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the greensInRegulation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/greens-in-regulations")
    public ResponseEntity<GreensInRegulation> updateGreensInRegulation(@RequestBody GreensInRegulation greensInRegulation) throws URISyntaxException {
        log.debug("REST request to update GreensInRegulation : {}", greensInRegulation);
        if (greensInRegulation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GreensInRegulation result = greensInRegulationRepository.save(greensInRegulation);
        greensInRegulationSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, greensInRegulation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /greens-in-regulations} : get all the greensInRegulations.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of greensInRegulations in body.
     */
    @GetMapping("/greens-in-regulations")
    public List<GreensInRegulation> getAllGreensInRegulations() {
        log.debug("REST request to get all GreensInRegulations");
        return greensInRegulationRepository.findAll();
    }

    /**
     * {@code GET  /greens-in-regulations/:id} : get the "id" greensInRegulation.
     *
     * @param id the id of the greensInRegulation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the greensInRegulation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/greens-in-regulations/{id}")
    public ResponseEntity<GreensInRegulation> getGreensInRegulation(@PathVariable Long id) {
        log.debug("REST request to get GreensInRegulation : {}", id);
        Optional<GreensInRegulation> greensInRegulation = greensInRegulationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(greensInRegulation);
    }

    /**
     * {@code DELETE  /greens-in-regulations/:id} : delete the "id" greensInRegulation.
     *
     * @param id the id of the greensInRegulation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/greens-in-regulations/{id}")
    public ResponseEntity<Void> deleteGreensInRegulation(@PathVariable Long id) {
        log.debug("REST request to delete GreensInRegulation : {}", id);
        greensInRegulationRepository.deleteById(id);
        greensInRegulationSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/greens-in-regulations?query=:query} : search for the greensInRegulation corresponding
     * to the query.
     *
     * @param query the query of the greensInRegulation search.
     * @return the result of the search.
     */
    @GetMapping("/_search/greens-in-regulations")
    public List<GreensInRegulation> searchGreensInRegulations(@RequestParam String query) {
        log.debug("REST request to search GreensInRegulations for query {}", query);
        return StreamSupport
            .stream(greensInRegulationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
