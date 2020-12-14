package com.cardoholic.web.rest;

import com.cardoholic.service.ItemCodesService;
import com.cardoholic.web.rest.errors.BadRequestAlertException;
import com.cardoholic.service.dto.ItemCodesDTO;

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
 * REST controller for managing {@link com.cardoholic.domain.ItemCodes}.
 */
@RestController
@RequestMapping("/api")
public class ItemCodesResource {

    private final Logger log = LoggerFactory.getLogger(ItemCodesResource.class);

    private static final String ENTITY_NAME = "itemCodes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemCodesService itemCodesService;

    public ItemCodesResource(ItemCodesService itemCodesService) {
        this.itemCodesService = itemCodesService;
    }

    /**
     * {@code POST  /item-codes} : Create a new itemCodes.
     *
     * @param itemCodesDTO the itemCodesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemCodesDTO, or with status {@code 400 (Bad Request)} if the itemCodes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-codes")
    public ResponseEntity<ItemCodesDTO> createItemCodes(@RequestBody ItemCodesDTO itemCodesDTO) throws URISyntaxException {
        log.debug("REST request to save ItemCodes : {}", itemCodesDTO);
        if (itemCodesDTO.getId() != null) {
            throw new BadRequestAlertException("A new itemCodes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemCodesDTO result = itemCodesService.save(itemCodesDTO);
        return ResponseEntity.created(new URI("/api/item-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-codes} : Updates an existing itemCodes.
     *
     * @param itemCodesDTO the itemCodesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemCodesDTO,
     * or with status {@code 400 (Bad Request)} if the itemCodesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemCodesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-codes")
    public ResponseEntity<ItemCodesDTO> updateItemCodes(@RequestBody ItemCodesDTO itemCodesDTO) throws URISyntaxException {
        log.debug("REST request to update ItemCodes : {}", itemCodesDTO);
        if (itemCodesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemCodesDTO result = itemCodesService.save(itemCodesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemCodesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-codes} : get all the itemCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemCodes in body.
     */
    @GetMapping("/item-codes")
    public List<ItemCodesDTO> getAllItemCodes() {
        log.debug("REST request to get all ItemCodes");
        return itemCodesService.findAll();
    }

    /**
     * {@code GET  /item-codes/:id} : get the "id" itemCodes.
     *
     * @param id the id of the itemCodesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemCodesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-codes/{id}")
    public ResponseEntity<ItemCodesDTO> getItemCodes(@PathVariable Long id) {
        log.debug("REST request to get ItemCodes : {}", id);
        Optional<ItemCodesDTO> itemCodesDTO = itemCodesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itemCodesDTO);
    }

    /**
     * {@code DELETE  /item-codes/:id} : delete the "id" itemCodes.
     *
     * @param id the id of the itemCodesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-codes/{id}")
    public ResponseEntity<Void> deleteItemCodes(@PathVariable Long id) {
        log.debug("REST request to delete ItemCodes : {}", id);
        itemCodesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
