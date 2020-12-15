package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.ItemCodes;
import com.cardoholic.repository.ItemCodesRepository;

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
 * Integration tests for the {@link ItemCodesResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ItemCodesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    @Autowired
    private ItemCodesRepository itemCodesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restItemCodesMockMvc;

    private ItemCodes itemCodes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemCodes createEntity(EntityManager em) {
        ItemCodes itemCodes = new ItemCodes()
            .code(DEFAULT_CODE);
        return itemCodes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ItemCodes createUpdatedEntity(EntityManager em) {
        ItemCodes itemCodes = new ItemCodes()
            .code(UPDATED_CODE);
        return itemCodes;
    }

    @BeforeEach
    public void initTest() {
        itemCodes = createEntity(em);
    }

    @Test
    @Transactional
    public void createItemCodes() throws Exception {
        int databaseSizeBeforeCreate = itemCodesRepository.findAll().size();
        // Create the ItemCodes
        restItemCodesMockMvc.perform(post("/api/item-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemCodes)))
            .andExpect(status().isCreated());

        // Validate the ItemCodes in the database
        List<ItemCodes> itemCodesList = itemCodesRepository.findAll();
        assertThat(itemCodesList).hasSize(databaseSizeBeforeCreate + 1);
        ItemCodes testItemCodes = itemCodesList.get(itemCodesList.size() - 1);
        assertThat(testItemCodes.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createItemCodesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = itemCodesRepository.findAll().size();

        // Create the ItemCodes with an existing ID
        itemCodes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restItemCodesMockMvc.perform(post("/api/item-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemCodes)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCodes in the database
        List<ItemCodes> itemCodesList = itemCodesRepository.findAll();
        assertThat(itemCodesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllItemCodes() throws Exception {
        // Initialize the database
        itemCodesRepository.saveAndFlush(itemCodes);

        // Get all the itemCodesList
        restItemCodesMockMvc.perform(get("/api/item-codes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itemCodes.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)));
    }
    
    @Test
    @Transactional
    public void getItemCodes() throws Exception {
        // Initialize the database
        itemCodesRepository.saveAndFlush(itemCodes);

        // Get the itemCodes
        restItemCodesMockMvc.perform(get("/api/item-codes/{id}", itemCodes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itemCodes.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingItemCodes() throws Exception {
        // Get the itemCodes
        restItemCodesMockMvc.perform(get("/api/item-codes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCodes() throws Exception {
        // Initialize the database
        itemCodesRepository.saveAndFlush(itemCodes);

        int databaseSizeBeforeUpdate = itemCodesRepository.findAll().size();

        // Update the itemCodes
        ItemCodes updatedItemCodes = itemCodesRepository.findById(itemCodes.getId()).get();
        // Disconnect from session so that the updates on updatedItemCodes are not directly saved in db
        em.detach(updatedItemCodes);
        updatedItemCodes
            .code(UPDATED_CODE);

        restItemCodesMockMvc.perform(put("/api/item-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedItemCodes)))
            .andExpect(status().isOk());

        // Validate the ItemCodes in the database
        List<ItemCodes> itemCodesList = itemCodesRepository.findAll();
        assertThat(itemCodesList).hasSize(databaseSizeBeforeUpdate);
        ItemCodes testItemCodes = itemCodesList.get(itemCodesList.size() - 1);
        assertThat(testItemCodes.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingItemCodes() throws Exception {
        int databaseSizeBeforeUpdate = itemCodesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItemCodesMockMvc.perform(put("/api/item-codes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(itemCodes)))
            .andExpect(status().isBadRequest());

        // Validate the ItemCodes in the database
        List<ItemCodes> itemCodesList = itemCodesRepository.findAll();
        assertThat(itemCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteItemCodes() throws Exception {
        // Initialize the database
        itemCodesRepository.saveAndFlush(itemCodes);

        int databaseSizeBeforeDelete = itemCodesRepository.findAll().size();

        // Delete the itemCodes
        restItemCodesMockMvc.perform(delete("/api/item-codes/{id}", itemCodes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ItemCodes> itemCodesList = itemCodesRepository.findAll();
        assertThat(itemCodesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
