package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class PhieuRenLuyenTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PhieuRenLuyen.class);
        PhieuRenLuyen phieuRenLuyen1 = new PhieuRenLuyen();
        phieuRenLuyen1.setId(1L);
        PhieuRenLuyen phieuRenLuyen2 = new PhieuRenLuyen();
        phieuRenLuyen2.setId(phieuRenLuyen1.getId());
        assertThat(phieuRenLuyen1).isEqualTo(phieuRenLuyen2);
        phieuRenLuyen2.setId(2L);
        assertThat(phieuRenLuyen1).isNotEqualTo(phieuRenLuyen2);
        phieuRenLuyen1.setId(null);
        assertThat(phieuRenLuyen1).isNotEqualTo(phieuRenLuyen2);
    }
}
