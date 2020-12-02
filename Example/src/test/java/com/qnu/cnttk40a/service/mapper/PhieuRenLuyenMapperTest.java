package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PhieuRenLuyenMapperTest {

    private PhieuRenLuyenMapper phieuRenLuyenMapper;

    @BeforeEach
    public void setUp() {
        phieuRenLuyenMapper = new PhieuRenLuyenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(phieuRenLuyenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(phieuRenLuyenMapper.fromId(null)).isNull();
    }
}
