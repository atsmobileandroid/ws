package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.ItemPrices;
import com.cardoholic.repository.ItemPricesRepository;
import com.cardoholic.service.ItemPricesService;
import com.cardoholic.service.dto.ItemPricesDTO;
import com.cardoholic.service.mapper.ItemPricesMapper;

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
 * Integration tests for the {@link ItemPricesResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemPricesResourceIT {

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    @Autowired
    private ItemPricesRepository itemPricesRepository;

    @Autowired
    private ItemPricesMapper itemPricesMapper;

    @Autowired
    private ItemPricesService itemPricesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemPricesMockMvc;

    private ItemPrices itemPrices;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPrices createEntity(EntityManager em) {
        ItemPrices itemPrices = new ItemPrices()
            .price(DEFAULT_PRICE);
        return itemPrices;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemPrices createUpdatedEntity(EntityManager em) {
        ItemPrices itemPrices = new ItemPrices()
            .price(UPDATED_PRICE);
        return itemPrices;
    }

    @BeforeEach
    public void initTest() {
        itemPrices = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemPrices() throws Exception {
        int databaseSizeBeforeCreate = itemPricesRepository.findAll().size();
        // Create the ItemPrices
        ItemPricesDTO itemPricesDTO = itemPricesMapper.toDto(itemPrices);
        restItemPricesMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPricesDTO)))
            .andExpect(status().isCreated());

        // Validate the ItemPrices in the database
        List<ItemPrices> itemPricesList = itemPricesRepository.findAll();
        assertThat(itemPricesList).hasSize(databaseSizeBeforeCreate + 1);
        ItemPrices testItemPrices = itemPricesList.get(itemPricesList.size() - 1);
        assertThat(testItemPrices.getPrice()).isEqualTo(DEFAULT_PRICE);
    }

    @Test
    @Transactional
    public void createItemPricesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemPricesRepository.findAll().size();

        // Create the ItemPrices with an existing ID
        itemPrices.setId(1L);
        ItemPricesDTO itemPricesDTO = itemPricesMapper.toDto(itemPrices);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemPricesMockMvc.perform(post("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPricesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPrices in the database
        List<ItemPrices> itemPricesList = itemPricesRepository.findAll();
        assertThat(itemPricesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllItemPrices() throws Exception {
        // Initialize the database
        itemPricesRepository.saveAndFlush(itemPrices);

        // Get all the itemPricesList
        restItemPricesMockMvc.perform(get("/api/item-prices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemPrices.getId().intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE)));
    }
    
    @Test
    @Transactional
    public void getItemPrices() throws Exception {
        // Initialize the database
        itemPricesRepository.saveAndFlush(itemPrices);

        // Get the itemPrices
        restItemPricesMockMvc.perform(get("/api/item-prices/{id}", itemPrices.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemPrices.getId().intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE));
    }
    @Test
    @Transactional
    public void getNonExistingItemPrices() throws Exception {
        // Get the itemPrices
        restItemPricesMockMvc.perform(get("/api/item-prices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemPrices() throws Exception {
        // Initialize the database
        itemPricesRepository.saveAndFlush(itemPrices);

        int databaseSizeBeforeUpdate = itemPricesRepository.findAll().size();

        // Update the itemPrices
        ItemPrices updatedItemPrices = itemPricesRepository.findById(itemPrices.getId()).get();
        // Disconnect from session so that the updates on updatedItemPrices are not directly saved in db
        em.detach(updatedItemPrices);
        updatedItemPrices
            .price(UPDATED_PRICE);
        ItemPricesDTO itemPricesDTO = itemPricesMapper.toDto(updatedItemPrices);

        restItemPricesMockMvc.perform(put("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPricesDTO)))
            .andExpect(status().isOk());

        // Validate the ItemPrices in the database
        List<ItemPrices> itemPricesList = itemPricesRepository.findAll();
        assertThat(itemPricesList).hasSize(databaseSizeBeforeUpdate);
        ItemPrices testItemPrices = itemPricesList.get(itemPricesList.size() - 1);
        assertThat(testItemPrices.getPrice()).isEqualTo(UPDATED_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemPrices() throws Exception {
        int databaseSizeBeforeUpdate = itemPricesRepository.findAll().size();

        // Create the ItemPrices
        ItemPricesDTO itemPricesDTO = itemPricesMapper.toDto(itemPrices);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemPricesMockMvc.perform(put("/api/item-prices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemPricesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ItemPrices in the database
        List<ItemPrices> itemPricesList = itemPricesRepository.findAll();
        assertThat(itemPricesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemPrices() throws Exception {
        // Initialize the database
        itemPricesRepository.saveAndFlush(itemPrices);

        int databaseSizeBeforeDelete = itemPricesRepository.findAll().size();

        // Delete the itemPrices
        restItemPricesMockMvc.perform(delete("/api/item-prices/{id}", itemPrices.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemPrices> itemPricesList = itemPricesRepository.findAll();
        assertThat(itemPricesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
