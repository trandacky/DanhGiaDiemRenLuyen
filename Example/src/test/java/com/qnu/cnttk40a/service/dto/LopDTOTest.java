package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class LopDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LopDTO.class);
        LopDTO lopDTO1 = new LopDTO();
        lopDTO1.setId(1L);
        LopDTO lopDTO2 = new LopDTO();
        assertThat(lopDTO1).isNotEqualTo(lopDTO2);
        lopDTO2.setId(lopDTO1.getId());
        assertThat(lopDTO1).isEqualTo(lopDTO2);
        lopDTO2.setId(2L);
        assertThat(lopDTO1).isNotEqualTo(lopDTO2);
        lopDTO1.setId(null);
        assertThat(lopDTO1).isNotEqualTo(lopDTO2);
    }
}
