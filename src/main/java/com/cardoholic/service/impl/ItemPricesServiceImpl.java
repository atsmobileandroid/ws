package com.cardoholic.service.impl;

import com.cardoholic.service.ItemPricesService;
import com.cardoholic.domain.ItemPrices;
import com.cardoholic.repository.ItemPricesRepository;
import com.cardoholic.service.dto.ItemPricesDTO;
import com.cardoholic.service.mapper.ItemPricesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ItemPrices}.
 */
@Service
@Transactional
public class ItemPricesServiceImpl implements ItemPricesService {

    private final Logger log = LoggerFactory.getLogger(ItemPricesServiceImpl.class);

    private final ItemPricesRepository itemPricesRepository;

    private final ItemPricesMapper itemPricesMapper;

    public ItemPricesServiceImpl(ItemPricesRepository itemPricesRepository, ItemPricesMapper itemPricesMapper) {
        this.itemPricesRepository = itemPricesRepository;
        this.itemPricesMapper = itemPricesMapper;
    }

    @Override
    public ItemPricesDTO save(ItemPricesDTO itemPricesDTO) {
        log.debug("Request to save ItemPrices : {}", itemPricesDTO);
        ItemPrices itemPrices = itemPricesMapper.toEntity(itemPricesDTO);
        itemPrices = itemPricesRepository.save(itemPrices);
        return itemPricesMapper.toDto(itemPrices);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemPricesDTO> findAll() {
        log.debug("Request to get all ItemPrices");
        return itemPricesRepository.findAll().stream()
            .map(itemPricesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ItemPricesDTO> findOne(Long id) {
        log.debug("Request to get ItemPrices : {}", id);
        return itemPricesRepository.findById(id)
            .map(itemPricesMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItemPrices : {}", id);
        itemPricesRepository.deleteById(id);
    }
}
