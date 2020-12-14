package com.cardoholic.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class OrderItemsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderItemsDTO.class);
        OrderItemsDTO orderItemsDTO1 = new OrderItemsDTO();
        orderItemsDTO1.setId(1L);
        OrderItemsDTO orderItemsDTO2 = new OrderItemsDTO();
        assertThat(orderItemsDTO1).isNotEqualTo(orderItemsDTO2);
        orderItemsDTO2.setId(orderItemsDTO1.getId());
        assertThat(orderItemsDTO1).isEqualTo(orderItemsDTO2);
        orderItemsDTO2.setId(2L);
        assertThat(orderItemsDTO1).isNotEqualTo(orderItemsDTO2);
        orderItemsDTO1.setId(null);
        assertThat(orderItemsDTO1).isNotEqualTo(orderItemsDTO2);
    }
}
