package com.cardoholic.web.rest;

import com.cardoholic.domain.Levels;
import com.cardoholic.repository.LevelsRepository;
import com.cardoholic.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cardoholic.domain.Levels}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LevelsResource {

    private final Logger log = LoggerFactory.getLogger(LevelsResource.class);

    private static final String ENTITY_NAME = "levels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LevelsRepository levelsRepository;

    public LevelsResource(LevelsRepository levelsRepository) {
        this.levelsRepository = levelsRepository;
    }

    /**
     * {@code POST  /levels} : Create a new levels.
     *
     * @param levels the levels to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new levels, or with status {@code 400 (Bad Request)} if the levels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/levels")
    public ResponseEntity<Levels> createLevels(@RequestBody Levels levels) throws URISyntaxException {
        log.debug("REST request to save Levels : {}", levels);
        if (levels.getId() != null) {
            throw new BadRequestAlertException("A new levels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Levels result = levelsRepository.save(levels);
        return ResponseEntity.created(new URI("/api/levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /levels} : Updates an existing levels.
     *
     * @param levels the levels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated levels,
     * or with status {@code 400 (Bad Request)} if the levels is not valid,
     * or with status {@code 500 (Internal Server Error)} if the levels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/levels")
    public ResponseEntity<Levels> updateLevels(@RequestBody Levels levels) throws URISyntaxException {
        log.debug("REST request to update Levels : {}", levels);
        if (levels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Levels result = levelsRepository.save(levels);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, levels.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /levels} : get all the levels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of levels in body.
     */
    @GetMapping("/levels")
    public List<Levels> getAllLevels() {
        log.debug("REST request to get all Levels");
        return levelsRepository.findAll();
    }

    /**
     * {@code GET  /levels/:id} : get the "id" levels.
     *
     * @param id the id of the levels to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the levels, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/levels/{id}")
    public ResponseEntity<Levels> getLevels(@PathVariable Long id) {
        log.debug("REST request to get Levels : {}", id);
        Optional<Levels> levels = levelsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(levels);
    }

    /**
     * {@code DELETE  /levels/:id} : delete the "id" levels.
     *
     * @param id the id of the levels to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/levels/{id}")
    public ResponseEntity<Void> deleteLevels(@PathVariable Long id) {
        log.debug("REST request to delete Levels : {}", id);
        levelsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
