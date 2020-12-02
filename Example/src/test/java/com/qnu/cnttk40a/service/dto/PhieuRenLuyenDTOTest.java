package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class PhieuRenLuyenDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhieuRenLuyenDTO.class);
        PhieuRenLuyenDTO phieuRenLuyenDTO1 = new PhieuRenLuyenDTO();
        phieuRenLuyenDTO1.setId(1L);
        PhieuRenLuyenDTO phieuRenLuyenDTO2 = new PhieuRenLuyenDTO();
        assertThat(phieuRenLuyenDTO1).isNotEqualTo(phieuRenLuyenDTO2);
        phieuRenLuyenDTO2.setId(phieuRenLuyenDTO1.getId());
        assertThat(phieuRenLuyenDTO1).isEqualTo(phieuRenLuyenDTO2);
        phieuRenLuyenDTO2.setId(2L);
        assertThat(phieuRenLuyenDTO1).isNotEqualTo(phieuRenLuyenDTO2);
        phieuRenLuyenDTO1.setId(null);
        assertThat(phieuRenLuyenDTO1).isNotEqualTo(phieuRenLuyenDTO2);
    }
}
