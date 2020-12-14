package com.cardoholic.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class ItemCodesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItemCodesDTO.class);
        ItemCodesDTO itemCodesDTO1 = new ItemCodesDTO();
        itemCodesDTO1.setId(1L);
        ItemCodesDTO itemCodesDTO2 = new ItemCodesDTO();
        assertThat(itemCodesDTO1).isNotEqualTo(itemCodesDTO2);
        itemCodesDTO2.setId(itemCodesDTO1.getId());
        assertThat(itemCodesDTO1).isEqualTo(itemCodesDTO2);
        itemCodesDTO2.setId(2L);
        assertThat(itemCodesDTO1).isNotEqualTo(itemCodesDTO2);
        itemCodesDTO1.setId(null);
        assertThat(itemCodesDTO1).isNotEqualTo(itemCodesDTO2);
    }
}
