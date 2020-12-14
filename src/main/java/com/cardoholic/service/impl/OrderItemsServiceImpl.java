package com.cardoholic.service.impl;

import com.cardoholic.service.OrderItemsService;
import com.cardoholic.domain.OrderItems;
import com.cardoholic.repository.OrderItemsRepository;
import com.cardoholic.service.dto.OrderItemsDTO;
import com.cardoholic.service.mapper.OrderItemsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link OrderItems}.
 */
@Service
@Transactional
public class OrderItemsServiceImpl implements OrderItemsService {

    private final Logger log = LoggerFactory.getLogger(OrderItemsServiceImpl.class);

    private final OrderItemsRepository orderItemsRepository;

    private final OrderItemsMapper orderItemsMapper;

    public OrderItemsServiceImpl(OrderItemsRepository orderItemsRepository, OrderItemsMapper orderItemsMapper) {
        this.orderItemsRepository = orderItemsRepository;
        this.orderItemsMapper = orderItemsMapper;
    }

    @Override
    public OrderItemsDTO save(OrderItemsDTO orderItemsDTO) {
        log.debug("Request to save OrderItems : {}", orderItemsDTO);
        OrderItems orderItems = orderItemsMapper.toEntity(orderItemsDTO);
        orderItems = orderItemsRepository.save(orderItems);
        return orderItemsMapper.toDto(orderItems);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemsDTO> findAll() {
        log.debug("Request to get all OrderItems");
        return orderItemsRepository.findAll().stream()
            .map(orderItemsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OrderItemsDTO> findOne(Long id) {
        log.debug("Request to get OrderItems : {}", id);
        return orderItemsRepository.findById(id)
            .map(orderItemsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderItems : {}", id);
        orderItemsRepository.deleteById(id);
    }
}
