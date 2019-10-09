package com.furious.golf.web.rest;

import com.furious.golf.domain.Golfer;
import com.furious.golf.repository.GolferRepository;
import com.furious.golf.repository.search.GolferSearchRepository;
import com.furious.golf.web.rest.errors.BadRequestAlertException;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.Iterator;
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

    @GetMapping("/scrapeGolfers")
    public String  scrapeGolfers(){
        Golfer firstByAgeIsNull = golferRepository.findFirstByAgeIsNull();
        System.out.println(firstByAgeIsNull.getName());
        try {
            //Document doc = Jsoup.parse("https://pbiosfiles.shotlink.com/ApplicationFiles/playerbios/index.html?id=" + firstByAgeIsNull.getPgaId());
            Document document = Jsoup.parse( new File( "Player Profile - Byeong Hun An.html" ) , "utf-8" );

            System.out.println(document.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstByAgeIsNull.getName();
    }

    @GetMapping("/loadGolfers")
    public void  loadGolfers(){
        log.debug("loading golfers");

        Gson gson = new Gson();

        try {

            System.out.println("Reading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                new FileReader("players.json"));

            //convert the json string back to object
            Json json = gson.fromJson(br, Json.class);
            List<Player> players = json.getTour().get(0).getPlayer();

            for(Player player:players){
                System.out.println(player.getFullName());
                Golfer golfer = new Golfer();
                golfer.setPgaId(Long.valueOf(player.getId()));
                golfer.setName(player.getFullName());
                golferRepository.save(golfer);
            }

            System.out.println(json.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private class Json {

        @SerializedName("tour")
        @Expose
        private List<Tour> tour = null;

        public List<Tour> getTour() {
            return tour;
        }

        public void setTour(List<Tour> tour) {
            this.tour = tour;
        }

    }

    private class Tour{
        @SerializedName("tour_id")
        @Expose
        private Integer tourId;
        @SerializedName("tour_code")
        @Expose
        private String tourCode;
        @SerializedName("player")
        @Expose
        private List<Player> player = null;

        public Integer getTourId() {
            return tourId;
        }

        public void setTourId(Integer tourId) {
            this.tourId = tourId;
        }

        public String getTourCode() {
            return tourCode;
        }

        public void setTourCode(String tourCode) {
            this.tourCode = tourCode;
        }

        public List<Player> getPlayer() {
            return player;
        }

        public void setPlayer(List<Player> player) {
            this.player = player;
        }
    }

    private class Player {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("hall_of_fame")
        @Expose
        private Integer hallOfFame;
        @SerializedName("member")
        @Expose
        private String member;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("international_player")
        @Expose
        private Integer internationalPlayer;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Integer getHallOfFame() {
            return hallOfFame;
        }

        public void setHallOfFame(Integer hallOfFame) {
            this.hallOfFame = hallOfFame;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getInternationalPlayer() {
            return internationalPlayer;
        }

        public void setInternationalPlayer(Integer internationalPlayer) {
            this.internationalPlayer = internationalPlayer;
        }


    }
}
