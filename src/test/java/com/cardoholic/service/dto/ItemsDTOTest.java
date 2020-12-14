package com.cardoholic.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class ItemsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemsDTO.class);
        ItemsDTO itemsDTO1 = new ItemsDTO();
        itemsDTO1.setId(1L);
        ItemsDTO itemsDTO2 = new ItemsDTO();
        assertThat(itemsDTO1).isNotEqualTo(itemsDTO2);
        itemsDTO2.setId(itemsDTO1.getId());
        assertThat(itemsDTO1).isEqualTo(itemsDTO2);
        itemsDTO2.setId(2L);
        assertThat(itemsDTO1).isNotEqualTo(itemsDTO2);
        itemsDTO1.setId(null);
        assertThat(itemsDTO1).isNotEqualTo(itemsDTO2);
    }
}
