package com.cardoholic.service.impl;

import com.cardoholic.service.LevelsService;
import com.cardoholic.domain.Levels;
import com.cardoholic.repository.LevelsRepository;
import com.cardoholic.service.dto.LevelsDTO;
import com.cardoholic.service.mapper.LevelsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Levels}.
 */
@Service
@Transactional
public class LevelsServiceImpl implements LevelsService {

    private final Logger log = LoggerFactory.getLogger(LevelsServiceImpl.class);

    private final LevelsRepository levelsRepository;

    private final LevelsMapper levelsMapper;

    public LevelsServiceImpl(LevelsRepository levelsRepository, LevelsMapper levelsMapper) {
        this.levelsRepository = levelsRepository;
        this.levelsMapper = levelsMapper;
    }

    @Override
    public LevelsDTO save(LevelsDTO levelsDTO) {
        log.debug("Request to save Levels : {}", levelsDTO);
        Levels levels = levelsMapper.toEntity(levelsDTO);
        levels = levelsRepository.save(levels);
        return levelsMapper.toDto(levels);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LevelsDTO> findAll() {
        log.debug("Request to get all Levels");
        return levelsRepository.findAll().stream()
            .map(levelsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<LevelsDTO> findOne(Long id) {
        log.debug("Request to get Levels : {}", id);
        return levelsRepository.findById(id)
            .map(levelsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Levels : {}", id);
        levelsRepository.deleteById(id);
    }
}
