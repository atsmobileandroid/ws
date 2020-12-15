package com.cardoholic.web.rest;

import com.cardoholic.CardoholicApp;
import com.cardoholic.domain.Countries;
import com.cardoholic.repository.CountriesRepository;

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
 * Integration tests for the {@link CountriesResource} REST controller.
 */
@SpringBootTest(classes = CardoholicApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CountriesResourceIT {

    private static final String DEFAULT_COUNTRY_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_FLAG_IMAGE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_FLAG_IMAGE_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_NAME_EN = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_NAME_EN = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENCY_NAME_AR = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY_NAME_AR = "BBBBBBBBBB";

    private static final String DEFAULT_EXCHANGE_RATE = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGE_RATE = "BBBBBBBBBB";

    @Autowired
    private CountriesRepository countriesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCountriesMockMvc;

    private Countries countries;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Countries createEntity(EntityManager em) {
        Countries countries = new Countries()
            .countryNameEn(DEFAULT_COUNTRY_NAME_EN)
            .countryNameAr(DEFAULT_COUNTRY_NAME_AR)
            .flagImagePath(DEFAULT_FLAG_IMAGE_PATH)
            .currencyNameEn(DEFAULT_CURRENCY_NAME_EN)
            .currencyNameAr(DEFAULT_CURRENCY_NAME_AR)
            .exchangeRate(DEFAULT_EXCHANGE_RATE);
        return countries;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Countries createUpdatedEntity(EntityManager em) {
        Countries countries = new Countries()
            .countryNameEn(UPDATED_COUNTRY_NAME_EN)
            .countryNameAr(UPDATED_COUNTRY_NAME_AR)
            .flagImagePath(UPDATED_FLAG_IMAGE_PATH)
            .currencyNameEn(UPDATED_CURRENCY_NAME_EN)
            .currencyNameAr(UPDATED_CURRENCY_NAME_AR)
            .exchangeRate(UPDATED_EXCHANGE_RATE);
        return countries;
    }

    @BeforeEach
    public void initTest() {
        countries = createEntity(em);
    }

    @Test
    @Transactional
    public void createCountries() throws Exception {
        int databaseSizeBeforeCreate = countriesRepository.findAll().size();
        // Create the Countries
        restCountriesMockMvc.perform(post("/api/countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countries)))
            .andExpect(status().isCreated());

        // Validate the Countries in the database
        List<Countries> countriesList = countriesRepository.findAll();
        assertThat(countriesList).hasSize(databaseSizeBeforeCreate + 1);
        Countries testCountries = countriesList.get(countriesList.size() - 1);
        assertThat(testCountries.getCountryNameEn()).isEqualTo(DEFAULT_COUNTRY_NAME_EN);
        assertThat(testCountries.getCountryNameAr()).isEqualTo(DEFAULT_COUNTRY_NAME_AR);
        assertThat(testCountries.getFlagImagePath()).isEqualTo(DEFAULT_FLAG_IMAGE_PATH);
        assertThat(testCountries.getCurrencyNameEn()).isEqualTo(DEFAULT_CURRENCY_NAME_EN);
        assertThat(testCountries.getCurrencyNameAr()).isEqualTo(DEFAULT_CURRENCY_NAME_AR);
        assertThat(testCountries.getExchangeRate()).isEqualTo(DEFAULT_EXCHANGE_RATE);
    }

    @Test
    @Transactional
    public void createCountriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = countriesRepository.findAll().size();

        // Create the Countries with an existing ID
        countries.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountriesMockMvc.perform(post("/api/countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countries)))
            .andExpect(status().isBadRequest());

        // Validate the Countries in the database
        List<Countries> countriesList = countriesRepository.findAll();
        assertThat(countriesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCountries() throws Exception {
        // Initialize the database
        countriesRepository.saveAndFlush(countries);

        // Get all the countriesList
        restCountriesMockMvc.perform(get("/api/countries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countries.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryNameEn").value(hasItem(DEFAULT_COUNTRY_NAME_EN)))
            .andExpect(jsonPath("$.[*].countryNameAr").value(hasItem(DEFAULT_COUNTRY_NAME_AR)))
            .andExpect(jsonPath("$.[*].flagImagePath").value(hasItem(DEFAULT_FLAG_IMAGE_PATH)))
            .andExpect(jsonPath("$.[*].currencyNameEn").value(hasItem(DEFAULT_CURRENCY_NAME_EN)))
            .andExpect(jsonPath("$.[*].currencyNameAr").value(hasItem(DEFAULT_CURRENCY_NAME_AR)))
            .andExpect(jsonPath("$.[*].exchangeRate").value(hasItem(DEFAULT_EXCHANGE_RATE)));
    }
    
    @Test
    @Transactional
    public void getCountries() throws Exception {
        // Initialize the database
        countriesRepository.saveAndFlush(countries);

        // Get the countries
        restCountriesMockMvc.perform(get("/api/countries/{id}", countries.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(countries.getId().intValue()))
            .andExpect(jsonPath("$.countryNameEn").value(DEFAULT_COUNTRY_NAME_EN))
            .andExpect(jsonPath("$.countryNameAr").value(DEFAULT_COUNTRY_NAME_AR))
            .andExpect(jsonPath("$.flagImagePath").value(DEFAULT_FLAG_IMAGE_PATH))
            .andExpect(jsonPath("$.currencyNameEn").value(DEFAULT_CURRENCY_NAME_EN))
            .andExpect(jsonPath("$.currencyNameAr").value(DEFAULT_CURRENCY_NAME_AR))
            .andExpect(jsonPath("$.exchangeRate").value(DEFAULT_EXCHANGE_RATE));
    }
    @Test
    @Transactional
    public void getNonExistingCountries() throws Exception {
        // Get the countries
        restCountriesMockMvc.perform(get("/api/countries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCountries() throws Exception {
        // Initialize the database
        countriesRepository.saveAndFlush(countries);

        int databaseSizeBeforeUpdate = countriesRepository.findAll().size();

        // Update the countries
        Countries updatedCountries = countriesRepository.findById(countries.getId()).get();
        // Disconnect from session so that the updates on updatedCountries are not directly saved in db
        em.detach(updatedCountries);
        updatedCountries
            .countryNameEn(UPDATED_COUNTRY_NAME_EN)
            .countryNameAr(UPDATED_COUNTRY_NAME_AR)
            .flagImagePath(UPDATED_FLAG_IMAGE_PATH)
            .currencyNameEn(UPDATED_CURRENCY_NAME_EN)
            .currencyNameAr(UPDATED_CURRENCY_NAME_AR)
            .exchangeRate(UPDATED_EXCHANGE_RATE);

        restCountriesMockMvc.perform(put("/api/countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCountries)))
            .andExpect(status().isOk());

        // Validate the Countries in the database
        List<Countries> countriesList = countriesRepository.findAll();
        assertThat(countriesList).hasSize(databaseSizeBeforeUpdate);
        Countries testCountries = countriesList.get(countriesList.size() - 1);
        assertThat(testCountries.getCountryNameEn()).isEqualTo(UPDATED_COUNTRY_NAME_EN);
        assertThat(testCountries.getCountryNameAr()).isEqualTo(UPDATED_COUNTRY_NAME_AR);
        assertThat(testCountries.getFlagImagePath()).isEqualTo(UPDATED_FLAG_IMAGE_PATH);
        assertThat(testCountries.getCurrencyNameEn()).isEqualTo(UPDATED_CURRENCY_NAME_EN);
        assertThat(testCountries.getCurrencyNameAr()).isEqualTo(UPDATED_CURRENCY_NAME_AR);
        assertThat(testCountries.getExchangeRate()).isEqualTo(UPDATED_EXCHANGE_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingCountries() throws Exception {
        int databaseSizeBeforeUpdate = countriesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountriesMockMvc.perform(put("/api/countries")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(countries)))
            .andExpect(status().isBadRequest());

        // Validate the Countries in the database
        List<Countries> countriesList = countriesRepository.findAll();
        assertThat(countriesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCountries() throws Exception {
        // Initialize the database
        countriesRepository.saveAndFlush(countries);

        int databaseSizeBeforeDelete = countriesRepository.findAll().size();

        // Delete the countries
        restCountriesMockMvc.perform(delete("/api/countries/{id}", countries.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Countries> countriesList = countriesRepository.findAll();
        assertThat(countriesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
