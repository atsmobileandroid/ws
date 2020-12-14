package com.cardoholic.web.rest;

import com.cardoholic.service.UserTypeService;
import com.cardoholic.web.rest.errors.BadRequestAlertException;
import com.cardoholic.service.dto.UserTypeDTO;

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
 * REST controller for managing {@link com.cardoholic.domain.UserType}.
 */
@RestController
@RequestMapping("/api")
public class UserTypeResource {

    private final Logger log = LoggerFactory.getLogger(UserTypeResource.class);

    private static final String ENTITY_NAME = "userType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserTypeService userTypeService;

    public UserTypeResource(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    /**
     * {@code POST  /user-types} : Create a new userType.
     *
     * @param userTypeDTO the userTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userTypeDTO, or with status {@code 400 (Bad Request)} if the userType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-types")
    public ResponseEntity<UserTypeDTO> createUserType(@RequestBody UserTypeDTO userTypeDTO) throws URISyntaxException {
        log.debug("REST request to save UserType : {}", userTypeDTO);
        if (userTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new userType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserTypeDTO result = userTypeService.save(userTypeDTO);
        return ResponseEntity.created(new URI("/api/user-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-types} : Updates an existing userType.
     *
     * @param userTypeDTO the userTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userTypeDTO,
     * or with status {@code 400 (Bad Request)} if the userTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-types")
    public ResponseEntity<UserTypeDTO> updateUserType(@RequestBody UserTypeDTO userTypeDTO) throws URISyntaxException {
        log.debug("REST request to update UserType : {}", userTypeDTO);
        if (userTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserTypeDTO result = userTypeService.save(userTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-types} : get all the userTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userTypes in body.
     */
    @GetMapping("/user-types")
    public List<UserTypeDTO> getAllUserTypes() {
        log.debug("REST request to get all UserTypes");
        return userTypeService.findAll();
    }

    /**
     * {@code GET  /user-types/:id} : get the "id" userType.
     *
     * @param id the id of the userTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-types/{id}")
    public ResponseEntity<UserTypeDTO> getUserType(@PathVariable Long id) {
        log.debug("REST request to get UserType : {}", id);
        Optional<UserTypeDTO> userTypeDTO = userTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userTypeDTO);
    }

    /**
     * {@code DELETE  /user-types/:id} : delete the "id" userType.
     *
     * @param id the id of the userTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-types/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Long id) {
        log.debug("REST request to delete UserType : {}", id);
        userTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
