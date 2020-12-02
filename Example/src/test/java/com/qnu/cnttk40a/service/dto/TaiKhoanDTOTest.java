package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class TaiKhoanDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiKhoanDTO.class);
        TaiKhoanDTO taiKhoanDTO1 = new TaiKhoanDTO();
        taiKhoanDTO1.setId(1L);
        TaiKhoanDTO taiKhoanDTO2 = new TaiKhoanDTO();
        assertThat(taiKhoanDTO1).isNotEqualTo(taiKhoanDTO2);
        taiKhoanDTO2.setId(taiKhoanDTO1.getId());
        assertThat(taiKhoanDTO1).isEqualTo(taiKhoanDTO2);
        taiKhoanDTO2.setId(2L);
        assertThat(taiKhoanDTO1).isNotEqualTo(taiKhoanDTO2);
        taiKhoanDTO1.setId(null);
        assertThat(taiKhoanDTO1).isNotEqualTo(taiKhoanDTO2);
    }
}
