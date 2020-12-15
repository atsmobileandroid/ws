package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.Levels;
import com.cardoholic.repository.LevelsRepository;

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
 * Integration tests for the {@link LevelsResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LevelsResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    @Autowired
    private LevelsRepository levelsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLevelsMockMvc;

    private Levels levels;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Levels createEntity(EntityManager em) {
        Levels levels = new Levels()
            .title(DEFAULT_TITLE)
            .color(DEFAULT_COLOR);
        return levels;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Levels createUpdatedEntity(EntityManager em) {
        Levels levels = new Levels()
            .title(UPDATED_TITLE)
            .color(UPDATED_COLOR);
        return levels;
    }

    @BeforeEach
    public void initTest() {
        levels = createEntity(em);
    }

    @Test
    @Transactional
    public void createLevels() throws Exception {
        int databaseSizeBeforeCreate = levelsRepository.findAll().size();
        // Create the Levels
        restLevelsMockMvc.perform(post("/api/levels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(levels)))
            .andExpect(status().isCreated());

        // Validate the Levels in the database
        List<Levels> levelsList = levelsRepository.findAll();
        assertThat(levelsList).hasSize(databaseSizeBeforeCreate + 1);
        Levels testLevels = levelsList.get(levelsList.size() - 1);
        assertThat(testLevels.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testLevels.getColor()).isEqualTo(DEFAULT_COLOR);
    }

    @Test
    @Transactional
    public void createLevelsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = levelsRepository.findAll().size();

        // Create the Levels with an existing ID
        levels.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLevelsMockMvc.perform(post("/api/levels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(levels)))
            .andExpect(status().isBadRequest());

        // Validate the Levels in the database
        List<Levels> levelsList = levelsRepository.findAll();
        assertThat(levelsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLevels() throws Exception {
        // Initialize the database
        levelsRepository.saveAndFlush(levels);

        // Get all the levelsList
        restLevelsMockMvc.perform(get("/api/levels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(levels.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)));
    }
    
    @Test
    @Transactional
    public void getLevels() throws Exception {
        // Initialize the database
        levelsRepository.saveAndFlush(levels);

        // Get the levels
        restLevelsMockMvc.perform(get("/api/levels/{id}", levels.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(levels.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR));
    }
    @Test
    @Transactional
    public void getNonExistingLevels() throws Exception {
        // Get the levels
        restLevelsMockMvc.perform(get("/api/levels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLevels() throws Exception {
        // Initialize the database
        levelsRepository.saveAndFlush(levels);

        int databaseSizeBeforeUpdate = levelsRepository.findAll().size();

        // Update the levels
        Levels updatedLevels = levelsRepository.findById(levels.getId()).get();
        // Disconnect from session so that the updates on updatedLevels are not directly saved in db
        em.detach(updatedLevels);
        updatedLevels
            .title(UPDATED_TITLE)
            .color(UPDATED_COLOR);

        restLevelsMockMvc.perform(put("/api/levels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLevels)))
            .andExpect(status().isOk());

        // Validate the Levels in the database
        List<Levels> levelsList = levelsRepository.findAll();
        assertThat(levelsList).hasSize(databaseSizeBeforeUpdate);
        Levels testLevels = levelsList.get(levelsList.size() - 1);
        assertThat(testLevels.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testLevels.getColor()).isEqualTo(UPDATED_COLOR);
    }

    @Test
    @Transactional
    public void updateNonExistingLevels() throws Exception {
        int databaseSizeBeforeUpdate = levelsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLevelsMockMvc.perform(put("/api/levels")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(levels)))
            .andExpect(status().isBadRequest());

        // Validate the Levels in the database
        List<Levels> levelsList = levelsRepository.findAll();
        assertThat(levelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLevels() throws Exception {
        // Initialize the database
        levelsRepository.saveAndFlush(levels);

        int databaseSizeBeforeDelete = levelsRepository.findAll().size();

        // Delete the levels
        restLevelsMockMvc.perform(delete("/api/levels/{id}", levels.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Levels> levelsList = levelsRepository.findAll();
        assertThat(levelsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
