package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.OrderItems;
import com.cardoholic.repository.OrderItemsRepository;
import com.cardoholic.service.OrderItemsService;
import com.cardoholic.service.dto.OrderItemsDTO;
import com.cardoholic.service.mapper.OrderItemsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OrderItemsResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OrderItemsResourceIT {

    private static final String DEFAULT_ITEM_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_PRICE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ITEM_COUNT = 1;
    private static final Integer UPDATED_ITEM_COUNT = 2;

    private static final String DEFAULT_ITEM_CODES = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_CODES = "BBBBBBBBBB";

    private static final String DEFAULT_TO_ID = "AAAAAAAAAA";
    private static final String UPDATED_TO_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SENT_TO_ID = false;
    private static final Boolean UPDATED_IS_SENT_TO_ID = true;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderItemsMockMvc;

    private OrderItems orderItems;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItems createEntity(EntityManager em) {
        OrderItems orderItems = new OrderItems()
            .itemTitle(DEFAULT_ITEM_TITLE)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .itemImagePath(DEFAULT_ITEM_IMAGE_PATH)
            .itemPrice(DEFAULT_ITEM_PRICE)
            .itemCount(DEFAULT_ITEM_COUNT)
            .itemCodes(DEFAULT_ITEM_CODES)
            .toId(DEFAULT_TO_ID)
            .isSentToId(DEFAULT_IS_SENT_TO_ID);
        return orderItems;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderItems createUpdatedEntity(EntityManager em) {
        OrderItems orderItems = new OrderItems()
            .itemTitle(UPDATED_ITEM_TITLE)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemImagePath(UPDATED_ITEM_IMAGE_PATH)
            .itemPrice(UPDATED_ITEM_PRICE)
            .itemCount(UPDATED_ITEM_COUNT)
            .itemCodes(UPDATED_ITEM_CODES)
            .toId(UPDATED_TO_ID)
            .isSentToId(UPDATED_IS_SENT_TO_ID);
        return orderItems;
    }

    @BeforeEach
    public void initTest() {
        orderItems = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderItems() throws Exception {
        int databaseSizeBeforeCreate = orderItemsRepository.findAll().size();
        // Create the OrderItems
        OrderItemsDTO orderItemsDTO = orderItemsMapper.toDto(orderItems);
        restOrderItemsMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemsDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderItems in the database
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        assertThat(orderItemsList).hasSize(databaseSizeBeforeCreate + 1);
        OrderItems testOrderItems = orderItemsList.get(orderItemsList.size() - 1);
        assertThat(testOrderItems.getItemTitle()).isEqualTo(DEFAULT_ITEM_TITLE);
        assertThat(testOrderItems.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testOrderItems.getItemImagePath()).isEqualTo(DEFAULT_ITEM_IMAGE_PATH);
        assertThat(testOrderItems.getItemPrice()).isEqualTo(DEFAULT_ITEM_PRICE);
        assertThat(testOrderItems.getItemCount()).isEqualTo(DEFAULT_ITEM_COUNT);
        assertThat(testOrderItems.getItemCodes()).isEqualTo(DEFAULT_ITEM_CODES);
        assertThat(testOrderItems.getToId()).isEqualTo(DEFAULT_TO_ID);
        assertThat(testOrderItems.isIsSentToId()).isEqualTo(DEFAULT_IS_SENT_TO_ID);
    }

    @Test
    @Transactional
    public void createOrderItemsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderItemsRepository.findAll().size();

        // Create the OrderItems with an existing ID
        orderItems.setId(1L);
        OrderItemsDTO orderItemsDTO = orderItemsMapper.toDto(orderItems);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderItemsMockMvc.perform(post("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItems in the database
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        assertThat(orderItemsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderItems() throws Exception {
        // Initialize the database
        orderItemsRepository.saveAndFlush(orderItems);

        // Get all the orderItemsList
        restOrderItemsMockMvc.perform(get("/api/order-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderItems.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemTitle").value(hasItem(DEFAULT_ITEM_TITLE)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].itemImagePath").value(hasItem(DEFAULT_ITEM_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].itemPrice").value(hasItem(DEFAULT_ITEM_PRICE)))
            .andExpect(jsonPath("$.[*].itemCount").value(hasItem(DEFAULT_ITEM_COUNT)))
            .andExpect(jsonPath("$.[*].itemCodes").value(hasItem(DEFAULT_ITEM_CODES)))
            .andExpect(jsonPath("$.[*].toId").value(hasItem(DEFAULT_TO_ID)))
            .andExpect(jsonPath("$.[*].isSentToId").value(hasItem(DEFAULT_IS_SENT_TO_ID.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getOrderItems() throws Exception {
        // Initialize the database
        orderItemsRepository.saveAndFlush(orderItems);

        // Get the orderItems
        restOrderItemsMockMvc.perform(get("/api/order-items/{id}", orderItems.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderItems.getId().intValue()))
            .andExpect(jsonPath("$.itemTitle").value(DEFAULT_ITEM_TITLE))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.itemImagePath").value(DEFAULT_ITEM_IMAGE_PATH))
            .andExpect(jsonPath("$.itemPrice").value(DEFAULT_ITEM_PRICE))
            .andExpect(jsonPath("$.itemCount").value(DEFAULT_ITEM_COUNT))
            .andExpect(jsonPath("$.itemCodes").value(DEFAULT_ITEM_CODES))
            .andExpect(jsonPath("$.toId").value(DEFAULT_TO_ID))
            .andExpect(jsonPath("$.isSentToId").value(DEFAULT_IS_SENT_TO_ID.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingOrderItems() throws Exception {
        // Get the orderItems
        restOrderItemsMockMvc.perform(get("/api/order-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderItems() throws Exception {
        // Initialize the database
        orderItemsRepository.saveAndFlush(orderItems);

        int databaseSizeBeforeUpdate = orderItemsRepository.findAll().size();

        // Update the orderItems
        OrderItems updatedOrderItems = orderItemsRepository.findById(orderItems.getId()).get();
        // Disconnect from session so that the updates on updatedOrderItems are not directly saved in db
        em.detach(updatedOrderItems);
        updatedOrderItems
            .itemTitle(UPDATED_ITEM_TITLE)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .itemImagePath(UPDATED_ITEM_IMAGE_PATH)
            .itemPrice(UPDATED_ITEM_PRICE)
            .itemCount(UPDATED_ITEM_COUNT)
            .itemCodes(UPDATED_ITEM_CODES)
            .toId(UPDATED_TO_ID)
            .isSentToId(UPDATED_IS_SENT_TO_ID);
        OrderItemsDTO orderItemsDTO = orderItemsMapper.toDto(updatedOrderItems);

        restOrderItemsMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemsDTO)))
            .andExpect(status().isOk());

        // Validate the OrderItems in the database
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        assertThat(orderItemsList).hasSize(databaseSizeBeforeUpdate);
        OrderItems testOrderItems = orderItemsList.get(orderItemsList.size() - 1);
        assertThat(testOrderItems.getItemTitle()).isEqualTo(UPDATED_ITEM_TITLE);
        assertThat(testOrderItems.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testOrderItems.getItemImagePath()).isEqualTo(UPDATED_ITEM_IMAGE_PATH);
        assertThat(testOrderItems.getItemPrice()).isEqualTo(UPDATED_ITEM_PRICE);
        assertThat(testOrderItems.getItemCount()).isEqualTo(UPDATED_ITEM_COUNT);
        assertThat(testOrderItems.getItemCodes()).isEqualTo(UPDATED_ITEM_CODES);
        assertThat(testOrderItems.getToId()).isEqualTo(UPDATED_TO_ID);
        assertThat(testOrderItems.isIsSentToId()).isEqualTo(UPDATED_IS_SENT_TO_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderItems() throws Exception {
        int databaseSizeBeforeUpdate = orderItemsRepository.findAll().size();

        // Create the OrderItems
        OrderItemsDTO orderItemsDTO = orderItemsMapper.toDto(orderItems);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderItemsMockMvc.perform(put("/api/order-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(orderItemsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderItems in the database
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        assertThat(orderItemsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderItems() throws Exception {
        // Initialize the database
        orderItemsRepository.saveAndFlush(orderItems);

        int databaseSizeBeforeDelete = orderItemsRepository.findAll().size();

        // Delete the orderItems
        restOrderItemsMockMvc.perform(delete("/api/order-items/{id}", orderItems.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        assertThat(orderItemsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
