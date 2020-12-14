package com.cardoholic.service.impl;

import com.cardoholic.service.CountriesService;
import com.cardoholic.domain.Countries;
import com.cardoholic.repository.CountriesRepository;
import com.cardoholic.service.dto.CountriesDTO;
import com.cardoholic.service.mapper.CountriesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Countries}.
 */
@Service
@Transactional
public class CountriesServiceImpl implements CountriesService {

    private final Logger log = LoggerFactory.getLogger(CountriesServiceImpl.class);

    private final CountriesRepository countriesRepository;

    private final CountriesMapper countriesMapper;

    public CountriesServiceImpl(CountriesRepository countriesRepository, CountriesMapper countriesMapper) {
        this.countriesRepository = countriesRepository;
        this.countriesMapper = countriesMapper;
    }

    @Override
    public CountriesDTO save(CountriesDTO countriesDTO) {
        log.debug("Request to save Countries : {}", countriesDTO);
        Countries countries = countriesMapper.toEntity(countriesDTO);
        countries = countriesRepository.save(countries);
        return countriesMapper.toDto(countries);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountriesDTO> findAll() {
        log.debug("Request to get all Countries");
        return countriesRepository.findAll().stream()
            .map(countriesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CountriesDTO> findOne(Long id) {
        log.debug("Request to get Countries : {}", id);
        return countriesRepository.findById(id)
            .map(countriesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Countries : {}", id);
        countriesRepository.deleteById(id);
    }
}
