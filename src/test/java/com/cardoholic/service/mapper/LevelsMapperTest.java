package com.cardoholic.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LevelsMapperTest {

    private LevelsMapper levelsMapper;

    @BeforeEach
    public void setUp() {
        levelsMapper = new LevelsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(levelsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(levelsMapper.fromId(null)).isNull();
    }
}
