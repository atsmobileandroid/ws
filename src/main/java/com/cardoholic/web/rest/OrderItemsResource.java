package com.cardoholic.web.rest;

import com.cardoholic.service.OrderItemsService;
import com.cardoholic.web.rest.errors.BadRequestAlertException;
import com.cardoholic.service.dto.OrderItemsDTO;

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
 * REST controller for managing {@link com.cardoholic.domain.OrderItems}.
 */
@RestController
@RequestMapping("/api")
public class OrderItemsResource {

    private final Logger log = LoggerFactory.getLogger(OrderItemsResource.class);

    private static final String ENTITY_NAME = "orderItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderItemsService orderItemsService;

    public OrderItemsResource(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    /**
     * {@code POST  /order-items} : Create a new orderItems.
     *
     * @param orderItemsDTO the orderItemsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderItemsDTO, or with status {@code 400 (Bad Request)} if the orderItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-items")
    public ResponseEntity<OrderItemsDTO> createOrderItems(@RequestBody OrderItemsDTO orderItemsDTO) throws URISyntaxException {
        log.debug("REST request to save OrderItems : {}", orderItemsDTO);
        if (orderItemsDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderItemsDTO result = orderItemsService.save(orderItemsDTO);
        return ResponseEntity.created(new URI("/api/order-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-items} : Updates an existing orderItems.
     *
     * @param orderItemsDTO the orderItemsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderItemsDTO,
     * or with status {@code 400 (Bad Request)} if the orderItemsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderItemsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-items")
    public ResponseEntity<OrderItemsDTO> updateOrderItems(@RequestBody OrderItemsDTO orderItemsDTO) throws URISyntaxException {
        log.debug("REST request to update OrderItems : {}", orderItemsDTO);
        if (orderItemsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderItemsDTO result = orderItemsService.save(orderItemsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderItemsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-items} : get all the orderItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderItems in body.
     */
    @GetMapping("/order-items")
    public List<OrderItemsDTO> getAllOrderItems() {
        log.debug("REST request to get all OrderItems");
        return orderItemsService.findAll();
    }

    /**
     * {@code GET  /order-items/:id} : get the "id" orderItems.
     *
     * @param id the id of the orderItemsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderItemsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItemsDTO> getOrderItems(@PathVariable Long id) {
        log.debug("REST request to get OrderItems : {}", id);
        Optional<OrderItemsDTO> orderItemsDTO = orderItemsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderItemsDTO);
    }

    /**
     * {@code DELETE  /order-items/:id} : delete the "id" orderItems.
     *
     * @param id the id of the orderItemsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-items/{id}")
    public ResponseEntity<Void> deleteOrderItems(@PathVariable Long id) {
        log.debug("REST request to delete OrderItems : {}", id);
        orderItemsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
