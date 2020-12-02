package com.qnu.cnttk40a.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.PhieuRenLuyen} entity.
 */
public class PhieuRenLuyenDTO implements Serializable {
    
    private Long id;

    private Integer namHoc;

    private Long hocKy;


    private Long tongDiemId;

    private Long chiTietPhieuRenLuyenId;

    private Long maSinhVienId;

    private Long idCauHoiId;

    private Long maSinhVienId;

    private Long idCauHoiId;
    
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

    public Long getHocKy() {
        return hocKy;
    }

    public void setHocKy(Long hocKy) {
        this.hocKy = hocKy;
    }

    public Long getTongDiemId() {
        return tongDiemId;
    }

    public void setTongDiemId(Long tongDiemId) {
        this.tongDiemId = tongDiemId;
    }

    public Long getChiTietPhieuRenLuyenId() {
        return chiTietPhieuRenLuyenId;
    }

    public void setChiTietPhieuRenLuyenId(Long chiTietPhieuRenLuyenId) {
        this.chiTietPhieuRenLuyenId = chiTietPhieuRenLuyenId;
    }

    public Long getMaSinhVienId() {
        return maSinhVienId;
    }

    public void setMaSinhVienId(Long taiKhoanId) {
        this.maSinhVienId = taiKhoanId;
    }

    public Long getIdCauHoiId() {
        return idCauHoiId;
    }

    public void setIdCauHoiId(Long cauHoiId) {
        this.idCauHoiId = cauHoiId;
    }

    public Long getMaSinhVienId() {
        return maSinhVienId;
    }

    public void setMaSinhVienId(Long taiKhoanId) {
        this.maSinhVienId = taiKhoanId;
    }

    public Long getIdCauHoiId() {
        return idCauHoiId;
    }

    public void setIdCauHoiId(Long cauHoiId) {
        this.idCauHoiId = cauHoiId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhieuRenLuyenDTO)) {
            return false;
        }

        return id != null && id.equals(((PhieuRenLuyenDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhieuRenLuyenDTO{" +
            "id=" + getId() +
            ", namHoc=" + getNamHoc() +
            ", hocKy=" + getHocKy() +
            ", tongDiemId=" + getTongDiemId() +
            ", chiTietPhieuRenLuyenId=" + getChiTietPhieuRenLuyenId() +
            ", maSinhVienId=" + getMaSinhVienId() +
            ", idCauHoiId=" + getIdCauHoiId() +
            ", maSinhVienId=" + getMaSinhVienId() +
            ", idCauHoiId=" + getIdCauHoiId() +
            "}";
    }
}
