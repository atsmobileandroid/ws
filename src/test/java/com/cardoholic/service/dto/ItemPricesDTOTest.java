package com.cardoholic.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class ItemPricesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPricesDTO.class);
        ItemPricesDTO itemPricesDTO1 = new ItemPricesDTO();
        itemPricesDTO1.setId(1L);
        ItemPricesDTO itemPricesDTO2 = new ItemPricesDTO();
        assertThat(itemPricesDTO1).isNotEqualTo(itemPricesDTO2);
        itemPricesDTO2.setId(itemPricesDTO1.getId());
        assertThat(itemPricesDTO1).isEqualTo(itemPricesDTO2);
        itemPricesDTO2.setId(2L);
        assertThat(itemPricesDTO1).isNotEqualTo(itemPricesDTO2);
        itemPricesDTO1.setId(null);
        assertThat(itemPricesDTO1).isNotEqualTo(itemPricesDTO2);
    }
}
