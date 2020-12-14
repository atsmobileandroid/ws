package com.cardoholic.web.rest;

import com.cardoholic.service.UsersService;
import com.cardoholic.web.rest.errors.BadRequestAlertException;
import com.cardoholic.service.dto.UsersDTO;

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
 * REST controller for managing {@link com.cardoholic.domain.Users}.
 */
@RestController
@RequestMapping("/api")
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    private static final String ENTITY_NAME = "users";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsersService usersService;

    public UsersResource(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * {@code POST  /users} : Create a new users.
     *
     * @param usersDTO the usersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usersDTO, or with status {@code 400 (Bad Request)} if the users has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/users")
    public ResponseEntity<UsersDTO> createUsers(@RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to save Users : {}", usersDTO);
        if (usersDTO.getId() != null) {
            throw new BadRequestAlertException("A new users cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsersDTO result = usersService.save(usersDTO);
        return ResponseEntity.created(new URI("/api/users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /users} : Updates an existing users.
     *
     * @param usersDTO the usersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usersDTO,
     * or with status {@code 400 (Bad Request)} if the usersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/users")
    public ResponseEntity<UsersDTO> updateUsers(@RequestBody UsersDTO usersDTO) throws URISyntaxException {
        log.debug("REST request to update Users : {}", usersDTO);
        if (usersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsersDTO result = usersService.save(usersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /users} : get all the users.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
     */
    @GetMapping("/users")
    public List<UsersDTO> getAllUsers() {
        log.debug("REST request to get all Users");
        return usersService.findAll();
    }

    /**
     * {@code GET  /users/:id} : get the "id" users.
     *
     * @param id the id of the usersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable Long id) {
        log.debug("REST request to get Users : {}", id);
        Optional<UsersDTO> usersDTO = usersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usersDTO);
    }

    /**
     * {@code DELETE  /users/:id} : delete the "id" users.
     *
     * @param id the id of the usersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        log.debug("REST request to delete Users : {}", id);
        usersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
