package com.qnu.cnttk40a.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.BoCauHoi} entity.
 */
public class BoCauHoiDTO implements Serializable {
    
    private Long id;

    private String tenBoCauHoi;

    private Boolean tinhTrang;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenBoCauHoi() {
        return tenBoCauHoi;
    }

    public void setTenBoCauHoi(String tenBoCauHoi) {
        this.tenBoCauHoi = tenBoCauHoi;
    }

    public Boolean isTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoCauHoiDTO)) {
            return false;
        }

        return id != null && id.equals(((BoCauHoiDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BoCauHoiDTO{" +
            "id=" + getId() +
            ", tenBoCauHoi='" + getTenBoCauHoi() + "'" +
            ", tinhTrang='" + isTinhTrang() + "'" +
            "}";
    }
}
