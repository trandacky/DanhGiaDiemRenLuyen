package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class TongDiemTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TongDiem.class);
        TongDiem tongDiem1 = new TongDiem();
        tongDiem1.setId(1L);
        TongDiem tongDiem2 = new TongDiem();
        tongDiem2.setId(tongDiem1.getId());
        assertThat(tongDiem1).isEqualTo(tongDiem2);
        tongDiem2.setId(2L);
        assertThat(tongDiem1).isNotEqualTo(tongDiem2);
        tongDiem1.setId(null);
        assertThat(tongDiem1).isNotEqualTo(tongDiem2);
    }
}
