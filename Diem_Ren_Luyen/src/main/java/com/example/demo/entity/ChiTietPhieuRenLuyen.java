package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * A ChiTietPhieuRenLuyen.
 */
@Entity
@Table(name = "chi_tiet_phieu_ren_luyen")
public class ChiTietPhieuRenLuyen {
	@OneToOne(mappedBy = "chiTietPhieuRenLuyen")
	@JsonIgnore
	private PhieuRenLuyen phieuRenLuyen;

	@Column(name = "nam_hoc")
	private Integer namHoc;

	@Column(name = "hoc_ky")
	private Integer hocKy;

	@Column(name = "diem_lan_1")
	private Integer diemLan1;

	@Column(name = "diem_lan_2")
	private Integer diemLan2;

	@Column(name = "diem_lan_3")
	private Integer diemLan3;

	@Column(name = "da_duyet_lan_2")
	private Boolean daDuyetLan2;

	@Column(name = "da_duyet_lan_3")
	private Boolean daDuyetLan3;

	public Integer getNamHoc() {
		return namHoc;
	}

	public ChiTietPhieuRenLuyen namHoc(Integer namHoc) {
		this.namHoc = namHoc;
		return this;
	}

	public void setNamHoc(Integer namHoc) {
		this.namHoc = namHoc;
	}

	public Integer getHocKy() {
		return hocKy;
	}

	public ChiTietPhieuRenLuyen hocKy(Integer hocKy) {
		this.hocKy = hocKy;
		return this;
	}

	public void setHocKy(Integer hocKy) {
		this.hocKy = hocKy;
	}

	public Integer getDiemLan1() {
		return diemLan1;
	}

	public ChiTietPhieuRenLuyen diemLan1(Integer diemLan1) {
		this.diemLan1 = diemLan1;
		return this;
	}

	public void setDiemLan1(Integer diemLan1) {
		this.diemLan1 = diemLan1;
	}

	public Integer getDiemLan2() {
		return diemLan2;
	}

	public ChiTietPhieuRenLuyen diemLan2(Integer diemLan2) {
		this.diemLan2 = diemLan2;
		return this;
	}

	public void setDiemLan2(Integer diemLan2) {
		this.diemLan2 = diemLan2;
	}

	public Integer getDiemLan3() {
		return diemLan3;
	}

	public ChiTietPhieuRenLuyen diemLan3(Integer diemLan3) {
		this.diemLan3 = diemLan3;
		return this;
	}

	public void setDiemLan3(Integer diemLan3) {
		this.diemLan3 = diemLan3;
	}

	public Boolean isDaDuyetLan2() {
		return daDuyetLan2;
	}

	public ChiTietPhieuRenLuyen daDuyetLan2(Boolean daDuyetLan2) {
		this.daDuyetLan2 = daDuyetLan2;
		return this;
	}

	public void setDaDuyetLan2(Boolean daDuyetLan2) {
		this.daDuyetLan2 = daDuyetLan2;
	}

	public Boolean isDaDuyetLan3() {
		return daDuyetLan3;
	}

	public ChiTietPhieuRenLuyen daDuyetLan3(Boolean daDuyetLan3) {
		this.daDuyetLan3 = daDuyetLan3;
		return this;
	}

	public void setDaDuyetLan3(Boolean daDuyetLan3) {
		this.daDuyetLan3 = daDuyetLan3;
	}

	public PhieuRenLuyen getPhieuRenLuyen() {
		return phieuRenLuyen;
	}

	public ChiTietPhieuRenLuyen phieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
		this.phieuRenLuyen = phieuRenLuyen;
		return this;
	}

	public void setPhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
		this.phieuRenLuyen = phieuRenLuyen;
	}

}
