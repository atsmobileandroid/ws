package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemsMapperTest {

    private OrderItemsMapper orderItemsMapper;

    @BeforeEach
    public void setUp() {
        orderItemsMapper = new OrderItemsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(orderItemsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(orderItemsMapper.fromId(null)).isNull();
    }
}
