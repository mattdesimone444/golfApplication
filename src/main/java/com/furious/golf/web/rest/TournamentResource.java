package com.furious.golf.web.rest;

import com.furious.golf.domain.Tournament;
import com.furious.golf.repository.TournamentRepository;
import com.furious.golf.repository.search.TournamentSearchRepository;
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
 * REST controller for managing {@link com.furious.golf.domain.Tournament}.
 */
@RestController
@RequestMapping("/api")
public class TournamentResource {

    private final Logger log = LoggerFactory.getLogger(TournamentResource.class);

    private static final String ENTITY_NAME = "tournament";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TournamentRepository tournamentRepository;

    private final TournamentSearchRepository tournamentSearchRepository;

    public TournamentResource(TournamentRepository tournamentRepository, TournamentSearchRepository tournamentSearchRepository) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentSearchRepository = tournamentSearchRepository;
    }

    /**
     * {@code POST  /tournaments} : Create a new tournament.
     *
     * @param tournament the tournament to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tournament, or with status {@code 400 (Bad Request)} if the tournament has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tournaments")
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) throws URISyntaxException {
        log.debug("REST request to save Tournament : {}", tournament);
        if (tournament.getId() != null) {
            throw new BadRequestAlertException("A new tournament cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tournament result = tournamentRepository.save(tournament);
        tournamentSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tournaments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tournaments} : Updates an existing tournament.
     *
     * @param tournament the tournament to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tournament,
     * or with status {@code 400 (Bad Request)} if the tournament is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tournament couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tournaments")
    public ResponseEntity<Tournament> updateTournament(@RequestBody Tournament tournament) throws URISyntaxException {
        log.debug("REST request to update Tournament : {}", tournament);
        if (tournament.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tournament result = tournamentRepository.save(tournament);
        tournamentSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tournament.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tournaments} : get all the tournaments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tournaments in body.
     */
    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments() {
        log.debug("REST request to get all Tournaments");
        return tournamentRepository.findAll();
    }

    /**
     * {@code GET  /tournaments/:id} : get the "id" tournament.
     *
     * @param id the id of the tournament to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tournament, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tournaments/{id}")
    public ResponseEntity<Tournament> getTournament(@PathVariable Long id) {
        log.debug("REST request to get Tournament : {}", id);
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tournament);
    }

    /**
     * {@code DELETE  /tournaments/:id} : delete the "id" tournament.
     *
     * @param id the id of the tournament to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tournaments/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        log.debug("REST request to delete Tournament : {}", id);
        tournamentRepository.deleteById(id);
        tournamentSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/tournaments?query=:query} : search for the tournament corresponding
     * to the query.
     *
     * @param query the query of the tournament search.
     * @return the result of the search.
     */
    @GetMapping("/_search/tournaments")
    public List<Tournament> searchTournaments(@RequestParam String query) {
        log.debug("REST request to search Tournaments for query {}", query);
        return StreamSupport
            .stream(tournamentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
