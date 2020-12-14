package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTypeMapperTest {

    private UserTypeMapper userTypeMapper;

    @BeforeEach
    public void setUp() {
        userTypeMapper = new UserTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userTypeMapper.fromId(null)).isNull();
    }
}
