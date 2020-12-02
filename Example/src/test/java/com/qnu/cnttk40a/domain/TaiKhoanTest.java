package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class TaiKhoanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaiKhoan.class);
        TaiKhoan taiKhoan1 = new TaiKhoan();
        taiKhoan1.setId(1L);
        TaiKhoan taiKhoan2 = new TaiKhoan();
        taiKhoan2.setId(taiKhoan1.getId());
        assertThat(taiKhoan1).isEqualTo(taiKhoan2);
        taiKhoan2.setId(2L);
        assertThat(taiKhoan1).isNotEqualTo(taiKhoan2);
        taiKhoan1.setId(null);
        assertThat(taiKhoan1).isNotEqualTo(taiKhoan2);
    }
}
