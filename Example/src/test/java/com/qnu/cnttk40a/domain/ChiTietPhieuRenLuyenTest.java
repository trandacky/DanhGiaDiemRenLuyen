package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class ChiTietPhieuRenLuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietPhieuRenLuyen.class);
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen1 = new ChiTietPhieuRenLuyen();
        chiTietPhieuRenLuyen1.setId(1L);
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen2 = new ChiTietPhieuRenLuyen();
        chiTietPhieuRenLuyen2.setId(chiTietPhieuRenLuyen1.getId());
        assertThat(chiTietPhieuRenLuyen1).isEqualTo(chiTietPhieuRenLuyen2);
        chiTietPhieuRenLuyen2.setId(2L);
        assertThat(chiTietPhieuRenLuyen1).isNotEqualTo(chiTietPhieuRenLuyen2);
        chiTietPhieuRenLuyen1.setId(null);
        assertThat(chiTietPhieuRenLuyen1).isNotEqualTo(chiTietPhieuRenLuyen2);
    }
}
