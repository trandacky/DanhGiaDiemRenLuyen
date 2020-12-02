package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A PhieuRenLuyen.
 */
@Entity
@Table(name = "phieu_ren_luyen")
public class PhieuRenLuyen{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhieuRenLuyen;

    @Column(name = "nam_hoc")
    private Integer namHoc;

    @Column(name = "hoc_ky")
    private Integer hocKy;

    @Column(name = "tong_diem_lan_1")
    private Integer tongDiemLan1;

    @Column(name = "tong_diem_lan_2")
    private Integer tongDiemLan2;

    @Column(name = "tong_diem_lan_3")
    private Integer tongDiemLan3;

    @Column(name = "da_duyet_lan_2")
    private Boolean daDuyetLan2;

    @Column(name = "da_duyet_lan_3")
    private Boolean daDuyetLan3;

    @OneToMany(mappedBy = "idPhieuRenLuyen",cascade = CascadeType.ALL)
    private Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "maSinhVien", nullable = false)
    private TaiKhoan maSinhVien;

   
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

    public Integer getHocKy() {
        return hocKy;
    }

    public PhieuRenLuyen hocKy(Integer hocKy) {
        this.hocKy = hocKy;
        return this;
    }

    public void setHocKy(Integer hocKy) {
        this.hocKy = hocKy;
    }

    public Integer getTongDiemLan1() {
        return tongDiemLan1;
    }

    public PhieuRenLuyen tongDiemLan1(Integer tongDiemLan1) {
        this.tongDiemLan1 = tongDiemLan1;
        return this;
    }

    public void setTongDiemLan1(Integer tongDiemLan1) {
        this.tongDiemLan1 = tongDiemLan1;
    }

    public Integer getTongDiemLan2() {
        return tongDiemLan2;
    }

    public PhieuRenLuyen tongDiemLan2(Integer tongDiemLan2) {
        this.tongDiemLan2 = tongDiemLan2;
        return this;
    }

    public void setTongDiemLan2(Integer tongDiemLan2) {
        this.tongDiemLan2 = tongDiemLan2;
    }

    public Integer getTongDiemLan3() {
        return tongDiemLan3;
    }

    public PhieuRenLuyen tongDiemLan3(Integer tongDiemLan3) {
        this.tongDiemLan3 = tongDiemLan3;
        return this;
    }

    public void setTongDiemLan3(Integer tongDiemLan3) {
        this.tongDiemLan3 = tongDiemLan3;
    }

    public Boolean isDaDuyetLan2() {
        return daDuyetLan2;
    }

    public PhieuRenLuyen daDuyetLan2(Boolean daDuyetLan2) {
        this.daDuyetLan2 = daDuyetLan2;
        return this;
    }

    public void setDaDuyetLan2(Boolean daDuyetLan2) {
        this.daDuyetLan2 = daDuyetLan2;
    }

    public Boolean isDaDuyetLan3() {
        return daDuyetLan3;
    }

    public PhieuRenLuyen daDuyetLan3(Boolean daDuyetLan3) {
        this.daDuyetLan3 = daDuyetLan3;
        return this;
    }

    public void setDaDuyetLan3(Boolean daDuyetLan3) {
        this.daDuyetLan3 = daDuyetLan3;
    }

    public Set<ChiTietPhieuRenLuyen> getChiTietPhieuRenLuyens() {
        return chiTietPhieuRenLuyens;
    }

    public PhieuRenLuyen chiTietPhieuRenLuyens(Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens) {
        this.chiTietPhieuRenLuyens = chiTietPhieuRenLuyens;
        return this;
    }

    public PhieuRenLuyen addChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyens.add(chiTietPhieuRenLuyen);
        chiTietPhieuRenLuyen.setIdPhieuRenLuyen(this);
        return this;
    }

    public PhieuRenLuyen removeChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyens.remove(chiTietPhieuRenLuyen);
        chiTietPhieuRenLuyen.setIdPhieuRenLuyen(null);
        return this;
    }

    public void setChiTietPhieuRenLuyens(Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens) {
        this.chiTietPhieuRenLuyens = chiTietPhieuRenLuyens;
    }

	public Long getIdPhieuRenLuyen() {
		return idPhieuRenLuyen;
	}

	public void setIdPhieuRenLuyen(Long idPhieuRenLuyen) {
		this.idPhieuRenLuyen = idPhieuRenLuyen;
	}

	public TaiKhoan getMaSinhVien() {
		return maSinhVien;
	}
//
	public void setMaSinhVien(TaiKhoan maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public Boolean getDaDuyetLan2() {
		return daDuyetLan2;
	}

	public Boolean getDaDuyetLan3() {
		return daDuyetLan3;
	}

   
}
