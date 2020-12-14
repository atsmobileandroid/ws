package com.cardoholic.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class LevelsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LevelsDTO.class);
        LevelsDTO levelsDTO1 = new LevelsDTO();
        levelsDTO1.setId(1L);
        LevelsDTO levelsDTO2 = new LevelsDTO();
        assertThat(levelsDTO1).isNotEqualTo(levelsDTO2);
        levelsDTO2.setId(levelsDTO1.getId());
        assertThat(levelsDTO1).isEqualTo(levelsDTO2);
        levelsDTO2.setId(2L);
        assertThat(levelsDTO1).isNotEqualTo(levelsDTO2);
        levelsDTO1.setId(null);
        assertThat(levelsDTO1).isNotEqualTo(levelsDTO2);
    }
}
