package com.qnu.cnttk40a.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChiTietPhieuRenLuyenMapperTest {

    private ChiTietPhieuRenLuyenMapper chiTietPhieuRenLuyenMapper;

    @BeforeEach
    public void setUp() {
        chiTietPhieuRenLuyenMapper = new ChiTietPhieuRenLuyenMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(chiTietPhieuRenLuyenMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chiTietPhieuRenLuyenMapper.fromId(null)).isNull();
    }
}
