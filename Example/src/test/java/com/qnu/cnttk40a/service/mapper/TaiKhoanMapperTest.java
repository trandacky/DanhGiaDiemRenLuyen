package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TaiKhoanMapperTest {

    private TaiKhoanMapper taiKhoanMapper;

    @BeforeEach
    public void setUp() {
        taiKhoanMapper = new TaiKhoanMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(taiKhoanMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(taiKhoanMapper.fromId(null)).isNull();
    }
}
