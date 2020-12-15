package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.Users;
import com.cardoholic.repository.UsersRepository;

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
 * Integration tests for the {@link UsersResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsersResourceIT {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_DATE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Integer DEFAULT_DEVICE_TYPE = 1;
    private static final Integer UPDATED_DEVICE_TYPE = 2;

    private static final String DEFAULT_DEVICE_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_DEVICE_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_BALANCE = "AAAAAAAAAA";
    private static final String UPDATED_BALANCE = "BBBBBBBBBB";

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsersMockMvc;

    private Users users;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Users createEntity(EntityManager em) {
        Users users = new Users()
            .email(DEFAULT_EMAIL)
            .password(DEFAULT_PASSWORD)
            .fullName(DEFAULT_FULL_NAME)
            .imagePath(DEFAULT_IMAGE_PATH)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .createdDate(DEFAULT_CREATED_DATE)
            .isActive(DEFAULT_IS_ACTIVE)
            .deviceType(DEFAULT_DEVICE_TYPE)
            .deviceToken(DEFAULT_DEVICE_TOKEN)
            .balance(DEFAULT_BALANCE);
        return users;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Users createUpdatedEntity(EntityManager em) {
        Users users = new Users()
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .fullName(UPDATED_FULL_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .createdDate(UPDATED_CREATED_DATE)
            .isActive(UPDATED_IS_ACTIVE)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceToken(UPDATED_DEVICE_TOKEN)
            .balance(UPDATED_BALANCE);
        return users;
    }

    @BeforeEach
    public void initTest() {
        users = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsers() throws Exception {
        int databaseSizeBeforeCreate = usersRepository.findAll().size();
        // Create the Users
        restUsersMockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(users)))
            .andExpect(status().isCreated());

        // Validate the Users in the database
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).hasSize(databaseSizeBeforeCreate + 1);
        Users testUsers = usersList.get(usersList.size() - 1);
        assertThat(testUsers.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUsers.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUsers.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testUsers.getImagePath()).isEqualTo(DEFAULT_IMAGE_PATH);
        assertThat(testUsers.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUsers.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUsers.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testUsers.getDeviceType()).isEqualTo(DEFAULT_DEVICE_TYPE);
        assertThat(testUsers.getDeviceToken()).isEqualTo(DEFAULT_DEVICE_TOKEN);
        assertThat(testUsers.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    @Transactional
    public void createUsersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usersRepository.findAll().size();

        // Create the Users with an existing ID
        users.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsersMockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(users)))
            .andExpect(status().isBadRequest());

        // Validate the Users in the database
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsers() throws Exception {
        // Initialize the database
        usersRepository.saveAndFlush(users);

        // Get all the usersList
        restUsersMockMvc.perform(get("/api/users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(users.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].imagePath").value(hasItem(DEFAULT_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].deviceType").value(hasItem(DEFAULT_DEVICE_TYPE)))
            .andExpect(jsonPath("$.[*].deviceToken").value(hasItem(DEFAULT_DEVICE_TOKEN)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE)));
    }
    
    @Test
    @Transactional
    public void getUsers() throws Exception {
        // Initialize the database
        usersRepository.saveAndFlush(users);

        // Get the users
        restUsersMockMvc.perform(get("/api/users/{id}", users.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(users.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.imagePath").value(DEFAULT_IMAGE_PATH))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.deviceType").value(DEFAULT_DEVICE_TYPE))
            .andExpect(jsonPath("$.deviceToken").value(DEFAULT_DEVICE_TOKEN))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE));
    }
    @Test
    @Transactional
    public void getNonExistingUsers() throws Exception {
        // Get the users
        restUsersMockMvc.perform(get("/api/users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsers() throws Exception {
        // Initialize the database
        usersRepository.saveAndFlush(users);

        int databaseSizeBeforeUpdate = usersRepository.findAll().size();

        // Update the users
        Users updatedUsers = usersRepository.findById(users.getId()).get();
        // Disconnect from session so that the updates on updatedUsers are not directly saved in db
        em.detach(updatedUsers);
        updatedUsers
            .email(UPDATED_EMAIL)
            .password(UPDATED_PASSWORD)
            .fullName(UPDATED_FULL_NAME)
            .imagePath(UPDATED_IMAGE_PATH)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .createdDate(UPDATED_CREATED_DATE)
            .isActive(UPDATED_IS_ACTIVE)
            .deviceType(UPDATED_DEVICE_TYPE)
            .deviceToken(UPDATED_DEVICE_TOKEN)
            .balance(UPDATED_BALANCE);

        restUsersMockMvc.perform(put("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsers)))
            .andExpect(status().isOk());

        // Validate the Users in the database
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).hasSize(databaseSizeBeforeUpdate);
        Users testUsers = usersList.get(usersList.size() - 1);
        assertThat(testUsers.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUsers.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUsers.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testUsers.getImagePath()).isEqualTo(UPDATED_IMAGE_PATH);
        assertThat(testUsers.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUsers.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUsers.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testUsers.getDeviceType()).isEqualTo(UPDATED_DEVICE_TYPE);
        assertThat(testUsers.getDeviceToken()).isEqualTo(UPDATED_DEVICE_TOKEN);
        assertThat(testUsers.getBalance()).isEqualTo(UPDATED_BALANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingUsers() throws Exception {
        int databaseSizeBeforeUpdate = usersRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsersMockMvc.perform(put("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(users)))
            .andExpect(status().isBadRequest());

        // Validate the Users in the database
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsers() throws Exception {
        // Initialize the database
        usersRepository.saveAndFlush(users);

        int databaseSizeBeforeDelete = usersRepository.findAll().size();

        // Delete the users
        restUsersMockMvc.perform(delete("/api/users/{id}", users.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Users> usersList = usersRepository.findAll();
        assertThat(usersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
