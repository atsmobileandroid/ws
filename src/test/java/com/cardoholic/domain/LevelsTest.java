package com.cardoholic.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.cardoholic.web.rest.TestUtil;

public class LevelsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Levels.class);
        Levels levels1 = new Levels();
        levels1.setId(1L);
        Levels levels2 = new Levels();
        levels2.setId(levels1.getId());
        assertThat(levels1).isEqualTo(levels2);
        levels2.setId(2L);
        assertThat(levels1).isNotEqualTo(levels2);
        levels1.setId(null);
        assertThat(levels1).isNotEqualTo(levels2);
    }
}
