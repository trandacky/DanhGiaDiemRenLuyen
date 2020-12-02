package com.qnu.cnttk40a.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class CauHoiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHoi.class);
        CauHoi cauHoi1 = new CauHoi();
        cauHoi1.setId(1L);
        CauHoi cauHoi2 = new CauHoi();
        cauHoi2.setId(cauHoi1.getId());
        assertThat(cauHoi1).isEqualTo(cauHoi2);
        cauHoi2.setId(2L);
        assertThat(cauHoi1).isNotEqualTo(cauHoi2);
        cauHoi1.setId(null);
        assertThat(cauHoi1).isNotEqualTo(cauHoi2);
    }
}
