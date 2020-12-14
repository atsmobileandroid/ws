package com.cardoholic.service.impl;

import com.cardoholic.service.BasketService;
import com.cardoholic.domain.Basket;
import com.cardoholic.repository.BasketRepository;
import com.cardoholic.service.dto.BasketDTO;
import com.cardoholic.service.mapper.BasketMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Basket}.
 */
@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final Logger log = LoggerFactory.getLogger(BasketServiceImpl.class);

    private final BasketRepository basketRepository;

    private final BasketMapper basketMapper;

    public BasketServiceImpl(BasketRepository basketRepository, BasketMapper basketMapper) {
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketDTO save(BasketDTO basketDTO) {
        log.debug("Request to save Basket : {}", basketDTO);
        Basket basket = basketMapper.toEntity(basketDTO);
        basket = basketRepository.save(basket);
        return basketMapper.toDto(basket);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasketDTO> findAll() {
        log.debug("Request to get all Baskets");
        return basketRepository.findAll().stream()
            .map(basketMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BasketDTO> findOne(Long id) {
        log.debug("Request to get Basket : {}", id);
        return basketRepository.findById(id)
            .map(basketMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Basket : {}", id);
        basketRepository.deleteById(id);
    }
}
