package com.cardoholic.web.rest;

import com.cardoholic.service.LevelsService;
import com.cardoholic.web.rest.errors.BadRequestAlertException;
import com.cardoholic.service.dto.LevelsDTO;

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

/**
 * REST controller for managing {@link com.cardoholic.domain.Levels}.
 */
@RestController
@RequestMapping("/api")
public class LevelsResource {

    private final Logger log = LoggerFactory.getLogger(LevelsResource.class);

    private static final String ENTITY_NAME = "levels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LevelsService levelsService;

    public LevelsResource(LevelsService levelsService) {
        this.levelsService = levelsService;
    }

    /**
     * {@code POST  /levels} : Create a new levels.
     *
     * @param levelsDTO the levelsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new levelsDTO, or with status {@code 400 (Bad Request)} if the levels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/levels")
    public ResponseEntity<LevelsDTO> createLevels(@RequestBody LevelsDTO levelsDTO) throws URISyntaxException {
        log.debug("REST request to save Levels : {}", levelsDTO);
        if (levelsDTO.getId() != null) {
            throw new BadRequestAlertException("A new levels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LevelsDTO result = levelsService.save(levelsDTO);
        return ResponseEntity.created(new URI("/api/levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /levels} : Updates an existing levels.
     *
     * @param levelsDTO the levelsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated levelsDTO,
     * or with status {@code 400 (Bad Request)} if the levelsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the levelsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/levels")
    public ResponseEntity<LevelsDTO> updateLevels(@RequestBody LevelsDTO levelsDTO) throws URISyntaxException {
        log.debug("REST request to update Levels : {}", levelsDTO);
        if (levelsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LevelsDTO result = levelsService.save(levelsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, levelsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /levels} : get all the levels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of levels in body.
     */
    @GetMapping("/levels")
    public List<LevelsDTO> getAllLevels() {
        log.debug("REST request to get all Levels");
        return levelsService.findAll();
    }

    /**
     * {@code GET  /levels/:id} : get the "id" levels.
     *
     * @param id the id of the levelsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the levelsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/levels/{id}")
    public ResponseEntity<LevelsDTO> getLevels(@PathVariable Long id) {
        log.debug("REST request to get Levels : {}", id);
        Optional<LevelsDTO> levelsDTO = levelsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelsDTO);
    }

    /**
     * {@code DELETE  /levels/:id} : delete the "id" levels.
     *
     * @param id the id of the levelsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/levels/{id}")
    public ResponseEntity<Void> deleteLevels(@PathVariable Long id) {
        log.debug("REST request to delete Levels : {}", id);
        levelsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
