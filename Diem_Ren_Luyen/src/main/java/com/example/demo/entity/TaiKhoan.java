package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "TaiKhoan")
public class TaiKhoan {
	@Id
	@Column(name = "maSinhVien")
	private long maSinhVien;

	@Column(name = "matKhau")
	private String matKhau;

	@Column(name = "quyen")
	private String quyen;
	
	@Column(name="ten")
	private String ten;
	
	@Column(name="lop")
	private Date lop;
	
	@Column(name = "tinhTrang")
	private boolean tinhTrang;

	public long getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(long maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getQuyen() {
		return quyen;
	}

	public void setQuyen(String quyen) {
		this.quyen = quyen;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Date getLop() {
		return lop;
	}

	public void setLop(Date lop) {
		this.lop = lop;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}
