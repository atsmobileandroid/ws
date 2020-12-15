package com.cardoholic.web.rest;

import com.cardoholic.domain.ItemPrices;
import com.cardoholic.repository.ItemPricesRepository;
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
 * REST controller for managing {@link com.cardoholic.domain.ItemPrices}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ItemPricesResource {

    private final Logger log = LoggerFactory.getLogger(ItemPricesResource.class);

    private static final String ENTITY_NAME = "itemPrices";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItemPricesRepository itemPricesRepository;

    public ItemPricesResource(ItemPricesRepository itemPricesRepository) {
        this.itemPricesRepository = itemPricesRepository;
    }

    /**
     * {@code POST  /item-prices} : Create a new itemPrices.
     *
     * @param itemPrices the itemPrices to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itemPrices, or with status {@code 400 (Bad Request)} if the itemPrices has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/item-prices")
    public ResponseEntity<ItemPrices> createItemPrices(@RequestBody ItemPrices itemPrices) throws URISyntaxException {
        log.debug("REST request to save ItemPrices : {}", itemPrices);
        if (itemPrices.getId() != null) {
            throw new BadRequestAlertException("A new itemPrices cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItemPrices result = itemPricesRepository.save(itemPrices);
        return ResponseEntity.created(new URI("/api/item-prices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /item-prices} : Updates an existing itemPrices.
     *
     * @param itemPrices the itemPrices to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itemPrices,
     * or with status {@code 400 (Bad Request)} if the itemPrices is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itemPrices couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/item-prices")
    public ResponseEntity<ItemPrices> updateItemPrices(@RequestBody ItemPrices itemPrices) throws URISyntaxException {
        log.debug("REST request to update ItemPrices : {}", itemPrices);
        if (itemPrices.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ItemPrices result = itemPricesRepository.save(itemPrices);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itemPrices.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /item-prices} : get all the itemPrices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itemPrices in body.
     */
    @GetMapping("/item-prices")
    public List<ItemPrices> getAllItemPrices() {
        log.debug("REST request to get all ItemPrices");
        return itemPricesRepository.findAll();
    }

    /**
     * {@code GET  /item-prices/:id} : get the "id" itemPrices.
     *
     * @param id the id of the itemPrices to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itemPrices, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/item-prices/{id}")
    public ResponseEntity<ItemPrices> getItemPrices(@PathVariable Long id) {
        log.debug("REST request to get ItemPrices : {}", id);
        Optional<ItemPrices> itemPrices = itemPricesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(itemPrices);
    }

    /**
     * {@code DELETE  /item-prices/:id} : delete the "id" itemPrices.
     *
     * @param id the id of the itemPrices to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/item-prices/{id}")
    public ResponseEntity<Void> deleteItemPrices(@PathVariable Long id) {
        log.debug("REST request to delete ItemPrices : {}", id);
        itemPricesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
