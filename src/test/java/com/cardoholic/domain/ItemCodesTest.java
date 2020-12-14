package com.cardoholic.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class ItemCodesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCodes.class);
        ItemCodes itemCodes1 = new ItemCodes();
        itemCodes1.setId(1L);
        ItemCodes itemCodes2 = new ItemCodes();
        itemCodes2.setId(itemCodes1.getId());
        assertThat(itemCodes1).isEqualTo(itemCodes2);
        itemCodes2.setId(2L);
        assertThat(itemCodes1).isNotEqualTo(itemCodes2);
        itemCodes1.setId(null);
        assertThat(itemCodes1).isNotEqualTo(itemCodes2);
    }
}
