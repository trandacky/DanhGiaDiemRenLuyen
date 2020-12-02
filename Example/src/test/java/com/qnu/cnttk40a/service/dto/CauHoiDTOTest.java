package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class CauHoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CauHoiDTO.class);
        CauHoiDTO cauHoiDTO1 = new CauHoiDTO();
        cauHoiDTO1.setId(1L);
        CauHoiDTO cauHoiDTO2 = new CauHoiDTO();
        assertThat(cauHoiDTO1).isNotEqualTo(cauHoiDTO2);
        cauHoiDTO2.setId(cauHoiDTO1.getId());
        assertThat(cauHoiDTO1).isEqualTo(cauHoiDTO2);
        cauHoiDTO2.setId(2L);
        assertThat(cauHoiDTO1).isNotEqualTo(cauHoiDTO2);
        cauHoiDTO1.setId(null);
        assertThat(cauHoiDTO1).isNotEqualTo(cauHoiDTO2);
    }
}
