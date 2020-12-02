package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class BoCauHoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoCauHoi.class);
        BoCauHoi boCauHoi1 = new BoCauHoi();
        boCauHoi1.setId(1L);
        BoCauHoi boCauHoi2 = new BoCauHoi();
        boCauHoi2.setId(boCauHoi1.getId());
        assertThat(boCauHoi1).isEqualTo(boCauHoi2);
        boCauHoi2.setId(2L);
        assertThat(boCauHoi1).isNotEqualTo(boCauHoi2);
        boCauHoi1.setId(null);
        assertThat(boCauHoi1).isNotEqualTo(boCauHoi2);
    }
}
