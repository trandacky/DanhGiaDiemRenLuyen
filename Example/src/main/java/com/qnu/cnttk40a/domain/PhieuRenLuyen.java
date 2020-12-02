package com.qnu.cnttk40a.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A PhieuRenLuyen.
 */
@Entity
@Table(name = "phieu_ren_luyen")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "phieurenluyen")
public class PhieuRenLuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nam_hoc")
    private Integer namHoc;

    @Column(name = "hoc_ky")
    private Long hocKy;

    @OneToOne
    @JoinColumn(unique = true)
    private TongDiem tongDiem;

    @OneToOne
    @JoinColumn(unique = true)
    private ChiTietPhieuRenLuyen chiTietPhieuRenLuyen;

    @ManyToOne
    @JsonIgnoreProperties(value = "phieuRenLuyens", allowSetters = true)
    private TaiKhoan maSinhVien;

    @ManyToOne
    @JsonIgnoreProperties(value = "phieuRenLuyens", allowSetters = true)
    private CauHoi idCauHoi;

    @ManyToOne
    @JsonIgnoreProperties(value = "phieuRenLuyens", allowSetters = true)
    private TaiKhoan maSinhVien;

    @ManyToOne
    @JsonIgnoreProperties(value = "phieuRenLuyens", allowSetters = true)
    private CauHoi idCauHoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNamHoc() {
        return namHoc;
    }

    public PhieuRenLuyen namHoc(Integer namHoc) {
        this.namHoc = namHoc;
        return this;
    }

    public void setNamHoc(Integer namHoc) {
        this.namHoc = namHoc;
    }

    public Long getHocKy() {
        return hocKy;
    }

    public PhieuRenLuyen hocKy(Long hocKy) {
        this.hocKy = hocKy;
        return this;
    }

    public void setHocKy(Long hocKy) {
        this.hocKy = hocKy;
    }

    public TongDiem getTongDiem() {
        return tongDiem;
    }

    public PhieuRenLuyen tongDiem(TongDiem tongDiem) {
        this.tongDiem = tongDiem;
        return this;
    }

    public void setTongDiem(TongDiem tongDiem) {
        this.tongDiem = tongDiem;
    }

    public ChiTietPhieuRenLuyen getChiTietPhieuRenLuyen() {
        return chiTietPhieuRenLuyen;
    }

    public PhieuRenLuyen chiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyen = chiTietPhieuRenLuyen;
        return this;
    }

    public void setChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyen = chiTietPhieuRenLuyen;
    }

    public TaiKhoan getMaSinhVien() {
        return maSinhVien;
    }

    public PhieuRenLuyen maSinhVien(TaiKhoan taiKhoan) {
        this.maSinhVien = taiKhoan;
        return this;
    }

    public void setMaSinhVien(TaiKhoan taiKhoan) {
        this.maSinhVien = taiKhoan;
    }

    public CauHoi getIdCauHoi() {
        return idCauHoi;
    }

    public PhieuRenLuyen idCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
        return this;
    }

    public void setIdCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
    }

    public TaiKhoan getMaSinhVien() {
        return maSinhVien;
    }

    public PhieuRenLuyen maSinhVien(TaiKhoan taiKhoan) {
        this.maSinhVien = taiKhoan;
        return this;
    }

    public void setMaSinhVien(TaiKhoan taiKhoan) {
        this.maSinhVien = taiKhoan;
    }

    public CauHoi getIdCauHoi() {
        return idCauHoi;
    }

    public PhieuRenLuyen idCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
        return this;
    }

    public void setIdCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhieuRenLuyen)) {
            return false;
        }
        return id != null && id.equals(((PhieuRenLuyen) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhieuRenLuyen{" +
            "id=" + getId() +
            ", namHoc=" + getNamHoc() +
            ", hocKy=" + getHocKy() +
            "}";
    }
}
