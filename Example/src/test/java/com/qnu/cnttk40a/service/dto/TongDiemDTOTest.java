package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class TongDiemDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TongDiemDTO.class);
        TongDiemDTO tongDiemDTO1 = new TongDiemDTO();
        tongDiemDTO1.setId(1L);
        TongDiemDTO tongDiemDTO2 = new TongDiemDTO();
        assertThat(tongDiemDTO1).isNotEqualTo(tongDiemDTO2);
        tongDiemDTO2.setId(tongDiemDTO1.getId());
        assertThat(tongDiemDTO1).isEqualTo(tongDiemDTO2);
        tongDiemDTO2.setId(2L);
        assertThat(tongDiemDTO1).isNotEqualTo(tongDiemDTO2);
        tongDiemDTO1.setId(null);
        assertThat(tongDiemDTO1).isNotEqualTo(tongDiemDTO2);
    }
}
