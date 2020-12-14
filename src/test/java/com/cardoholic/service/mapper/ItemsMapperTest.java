package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemsMapperTest {

    private ItemsMapper itemsMapper;

    @BeforeEach
    public void setUp() {
        itemsMapper = new ItemsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemsMapper.fromId(null)).isNull();
    }
}
