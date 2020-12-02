package com.example.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.CauHoi} entity.
 */
public class CauHoiDTO implements Serializable {
    
    private Long id;

    private String tenCauHoi;

    private Integer diemToiDa;

    private Boolean tinhTrang;


    private Long idBoCauHoiId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenCauHoi() {
        return tenCauHoi;
    }

    public void setTenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
    }

    public Integer getDiemToiDa() {
        return diemToiDa;
    }

    public void setDiemToiDa(Integer diemToiDa) {
        this.diemToiDa = diemToiDa;
    }

    public Boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Long getIdBoCauHoiId() {
        return idBoCauHoiId;
    }

    public void setIdBoCauHoiId(Long boCauHoiId) {
        this.idBoCauHoiId = boCauHoiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHoiDTO)) {
            return false;
        }

        return id != null && id.equals(((CauHoiDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHoiDTO{" +
            "id=" + getId() +
            ", tenCauHoi='" + getTenCauHoi() + "'" +
            ", diemToiDa=" + getDiemToiDa() +
            ", tinhTrang='" + isTinhTrang() + "'" +
            ", idBoCauHoiId=" + getIdBoCauHoiId() +
            "}";
    }
}
