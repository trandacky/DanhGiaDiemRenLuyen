package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoCauHoiMapperTest {

    private BoCauHoiMapper boCauHoiMapper;

    @BeforeEach
    public void setUp() {
        boCauHoiMapper = new BoCauHoiMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(boCauHoiMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(boCauHoiMapper.fromId(null)).isNull();
    }
}
