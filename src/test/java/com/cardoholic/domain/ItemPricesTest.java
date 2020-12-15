package com.cardoholic.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class ItemPricesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemPrices.class);
        ItemPrices itemPrices1 = new ItemPrices();
        itemPrices1.setId(1L);
        ItemPrices itemPrices2 = new ItemPrices();
        itemPrices2.setId(itemPrices1.getId());
        assertThat(itemPrices1).isEqualTo(itemPrices2);
        itemPrices2.setId(2L);
        assertThat(itemPrices1).isNotEqualTo(itemPrices2);
        itemPrices1.setId(null);
        assertThat(itemPrices1).isNotEqualTo(itemPrices2);
    }
}
