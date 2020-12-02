package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PhieuRenLuyen.
 */
@Entity
@Table(name = "phieu_ren_luyen")
public class PhieuRenLuyen{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nam_hoc")
    private int namHoc;

    @Column(name = "hoc_ky")
    private int hocKy;

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

	public int getHocKy() {
		return hocKy;
	}

	public void setHocKy(int hocKy) {
		this.hocKy = hocKy;
	}

	public TongDiem getTongDiem() {
		return tongDiem;
	}

	public void setTongDiem(TongDiem tongDiem) {
		this.tongDiem = tongDiem;
	}

	public ChiTietPhieuRenLuyen getChiTietPhieuRenLuyen() {
		return chiTietPhieuRenLuyen;
	}

	public void setChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
		this.chiTietPhieuRenLuyen = chiTietPhieuRenLuyen;
	}

	public TaiKhoan getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(TaiKhoan maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public CauHoi getIdCauHoi() {
		return idCauHoi;
	}

	public void setIdCauHoi(CauHoi idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public void setNamHoc(int namHoc) {
		this.namHoc = namHoc;
	}

    
}
