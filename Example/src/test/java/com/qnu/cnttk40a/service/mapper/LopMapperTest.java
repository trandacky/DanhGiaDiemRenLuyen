package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LopMapperTest {

    private LopMapper lopMapper;

    @BeforeEach
    public void setUp() {
        lopMapper = new LopMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lopMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lopMapper.fromId(null)).isNull();
    }
}
