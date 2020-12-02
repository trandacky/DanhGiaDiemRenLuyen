package com.qnu.cnttk40a.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.qnu.cnttk40a.web.rest.TestUtil;

public class BoCauHoiDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BoCauHoiDTO.class);
        BoCauHoiDTO boCauHoiDTO1 = new BoCauHoiDTO();
        boCauHoiDTO1.setId(1L);
        BoCauHoiDTO boCauHoiDTO2 = new BoCauHoiDTO();
        assertThat(boCauHoiDTO1).isNotEqualTo(boCauHoiDTO2);
        boCauHoiDTO2.setId(boCauHoiDTO1.getId());
        assertThat(boCauHoiDTO1).isEqualTo(boCauHoiDTO2);
        boCauHoiDTO2.setId(2L);
        assertThat(boCauHoiDTO1).isNotEqualTo(boCauHoiDTO2);
        boCauHoiDTO1.setId(null);
        assertThat(boCauHoiDTO1).isNotEqualTo(boCauHoiDTO2);
    }
}
