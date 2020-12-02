package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CauHoiMapperTest {

    private CauHoiMapper cauHoiMapper;

    @BeforeEach
    public void setUp() {
        cauHoiMapper = new CauHoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cauHoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cauHoiMapper.fromId(null)).isNull();
    }
}
