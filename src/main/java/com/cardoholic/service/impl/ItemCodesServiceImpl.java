package com.cardoholic.service.impl;

import com.cardoholic.service.ItemCodesService;
import com.cardoholic.domain.ItemCodes;
import com.cardoholic.repository.ItemCodesRepository;
import com.cardoholic.service.dto.ItemCodesDTO;
import com.cardoholic.service.mapper.ItemCodesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ItemCodes}.
 */
@Service
@Transactional
public class ItemCodesServiceImpl implements ItemCodesService {

    private final Logger log = LoggerFactory.getLogger(ItemCodesServiceImpl.class);

    private final ItemCodesRepository itemCodesRepository;

    private final ItemCodesMapper itemCodesMapper;

    public ItemCodesServiceImpl(ItemCodesRepository itemCodesRepository, ItemCodesMapper itemCodesMapper) {
        this.itemCodesRepository = itemCodesRepository;
        this.itemCodesMapper = itemCodesMapper;
    }

    @Override
    public ItemCodesDTO save(ItemCodesDTO itemCodesDTO) {
        log.debug("Request to save ItemCodes : {}", itemCodesDTO);
        ItemCodes itemCodes = itemCodesMapper.toEntity(itemCodesDTO);
        itemCodes = itemCodesRepository.save(itemCodes);
        return itemCodesMapper.toDto(itemCodes);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemCodesDTO> findAll() {
        log.debug("Request to get all ItemCodes");
        return itemCodesRepository.findAll().stream()
            .map(itemCodesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ItemCodesDTO> findOne(Long id) {
        log.debug("Request to get ItemCodes : {}", id);
        return itemCodesRepository.findById(id)
            .map(itemCodesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemCodes : {}", id);
        itemCodesRepository.deleteById(id);
    }
}
