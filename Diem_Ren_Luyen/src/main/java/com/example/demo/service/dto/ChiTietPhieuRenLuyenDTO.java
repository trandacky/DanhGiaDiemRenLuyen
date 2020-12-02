package com.example.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen} entity.
 */
public class ChiTietPhieuRenLuyenDTO implements Serializable {
    
    private Long id;

    private Integer namHoc;

    private Integer hocKy;

    private Integer diemLan1;

    private Integer diemLan2;

    private Integer diemLan3;

    private Boolean daDuyetLan2;

    private Boolean daDuyetLan3;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(Integer namHoc) {
        this.namHoc = namHoc;
    }

    public Integer getHocKy() {
        return hocKy;
    }

    public void setHocKy(Integer hocKy) {
        this.hocKy = hocKy;
    }

    public Integer getDiemLan1() {
        return diemLan1;
    }

    public void setDiemLan1(Integer diemLan1) {
        this.diemLan1 = diemLan1;
    }

    public Integer getDiemLan2() {
        return diemLan2;
    }

    public void setDiemLan2(Integer diemLan2) {
        this.diemLan2 = diemLan2;
    }

    public Integer getDiemLan3() {
        return diemLan3;
    }

    public void setDiemLan3(Integer diemLan3) {
        this.diemLan3 = diemLan3;
    }

    public Boolean isDaDuyetLan2() {
        return daDuyetLan2;
    }

    public void setDaDuyetLan2(Boolean daDuyetLan2) {
        this.daDuyetLan2 = daDuyetLan2;
    }

    public Boolean isDaDuyetLan3() {
        return daDuyetLan3;
    }

    public void setDaDuyetLan3(Boolean daDuyetLan3) {
        this.daDuyetLan3 = daDuyetLan3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietPhieuRenLuyenDTO)) {
            return false;
        }

        return id != null && id.equals(((ChiTietPhieuRenLuyenDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTietPhieuRenLuyenDTO{" +
            "id=" + getId() +
            ", namHoc=" + getNamHoc() +
            ", hocKy=" + getHocKy() +
            ", diemLan1=" + getDiemLan1() +
            ", diemLan2=" + getDiemLan2() +
            ", diemLan3=" + getDiemLan3() +
            ", daDuyetLan2='" + isDaDuyetLan2() + "'" +
            ", daDuyetLan3='" + isDaDuyetLan3() + "'" +
            "}";
    }
}
