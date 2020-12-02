package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TongDiemMapperTest {

    private TongDiemMapper tongDiemMapper;

    @BeforeEach
    public void setUp() {
        tongDiemMapper = new TongDiemMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tongDiemMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tongDiemMapper.fromId(null)).isNull();
    }
}
