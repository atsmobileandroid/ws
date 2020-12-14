package com.cardoholic.service.impl;

import com.cardoholic.service.UserTypeService;
import com.cardoholic.domain.UserType;
import com.cardoholic.repository.UserTypeRepository;
import com.cardoholic.service.dto.UserTypeDTO;
import com.cardoholic.service.mapper.UserTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link UserType}.
 */
@Service
@Transactional
public class UserTypeServiceImpl implements UserTypeService {

    private final Logger log = LoggerFactory.getLogger(UserTypeServiceImpl.class);

    private final UserTypeRepository userTypeRepository;

    private final UserTypeMapper userTypeMapper;

    public UserTypeServiceImpl(UserTypeRepository userTypeRepository, UserTypeMapper userTypeMapper) {
        this.userTypeRepository = userTypeRepository;
        this.userTypeMapper = userTypeMapper;
    }

    @Override
    public UserTypeDTO save(UserTypeDTO userTypeDTO) {
        log.debug("Request to save UserType : {}", userTypeDTO);
        UserType userType = userTypeMapper.toEntity(userTypeDTO);
        userType = userTypeRepository.save(userType);
        return userTypeMapper.toDto(userType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserTypeDTO> findAll() {
        log.debug("Request to get all UserTypes");
        return userTypeRepository.findAll().stream()
            .map(userTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserTypeDTO> findOne(Long id) {
        log.debug("Request to get UserType : {}", id);
        return userTypeRepository.findById(id)
            .map(userTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserType : {}", id);
        userTypeRepository.deleteById(id);
    }
}
