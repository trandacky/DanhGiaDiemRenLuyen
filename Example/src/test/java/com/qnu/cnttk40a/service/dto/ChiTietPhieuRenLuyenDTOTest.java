package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class ChiTietPhieuRenLuyenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTietPhieuRenLuyenDTO.class);
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO1 = new ChiTietPhieuRenLuyenDTO();
        chiTietPhieuRenLuyenDTO1.setId(1L);
        ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO2 = new ChiTietPhieuRenLuyenDTO();
        assertThat(chiTietPhieuRenLuyenDTO1).isNotEqualTo(chiTietPhieuRenLuyenDTO2);
        chiTietPhieuRenLuyenDTO2.setId(chiTietPhieuRenLuyenDTO1.getId());
        assertThat(chiTietPhieuRenLuyenDTO1).isEqualTo(chiTietPhieuRenLuyenDTO2);
        chiTietPhieuRenLuyenDTO2.setId(2L);
        assertThat(chiTietPhieuRenLuyenDTO1).isNotEqualTo(chiTietPhieuRenLuyenDTO2);
        chiTietPhieuRenLuyenDTO1.setId(null);
        assertThat(chiTietPhieuRenLuyenDTO1).isNotEqualTo(chiTietPhieuRenLuyenDTO2);
    }
}
