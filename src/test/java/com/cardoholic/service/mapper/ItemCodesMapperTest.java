package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemCodesMapperTest {

    private ItemCodesMapper itemCodesMapper;

    @BeforeEach
    public void setUp() {
        itemCodesMapper = new ItemCodesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemCodesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemCodesMapper.fromId(null)).isNull();
    }
}
