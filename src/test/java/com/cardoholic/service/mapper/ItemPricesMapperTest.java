package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemPricesMapperTest {

    private ItemPricesMapper itemPricesMapper;

    @BeforeEach
    public void setUp() {
        itemPricesMapper = new ItemPricesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(itemPricesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(itemPricesMapper.fromId(null)).isNull();
    }
}
