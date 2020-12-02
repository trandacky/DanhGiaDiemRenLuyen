package com.example.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.TongDiem} entity.
 */
public class TongDiemDTO implements Serializable {
    
    private Long id;

    private Integer tongDiemlan1;

    private Integer tongDiemlan2;

    private Integer tongDiemlan3;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTongDiemlan1() {
        return tongDiemlan1;
    }

    public void setTongDiemlan1(Integer tongDiemlan1) {
        this.tongDiemlan1 = tongDiemlan1;
    }

    public Integer getTongDiemlan2() {
        return tongDiemlan2;
    }

    public void setTongDiemlan2(Integer tongDiemlan2) {
        this.tongDiemlan2 = tongDiemlan2;
    }

    public Integer getTongDiemlan3() {
        return tongDiemlan3;
    }

    public void setTongDiemlan3(Integer tongDiemlan3) {
        this.tongDiemlan3 = tongDiemlan3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TongDiemDTO)) {
            return false;
        }

        return id != null && id.equals(((TongDiemDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TongDiemDTO{" +
            "id=" + getId() +
            ", tongDiemlan1=" + getTongDiemlan1() +
            ", tongDiemlan2=" + getTongDiemlan2() +
            ", tongDiemlan3=" + getTongDiemlan3() +
            "}";
    }
}
