package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "CauHoi")
public class CauHoi {
	@Id
	@Column(name = "idCauHoi")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCauHoi;

	@ManyToOne
	@JoinColumn(name = "idBoCauHoi", nullable = false)
	private BoCauHoi idBoCauHoi;

	@Column(name = "tenCauHoi")
	private String tenCauHoi;

	@Column(name = "diemToiDa")
	private int diemToiDa;
	@Column(name = "tinhTrang")
	private boolean tinhTrang;
	public long getIdCauHoi() {
		return idCauHoi;
	}
	public void setIdCauHoi(long idCauHoi) {
		this.idCauHoi = idCauHoi;
	}
	public BoCauHoi getIdBoCauHoi() {
		return idBoCauHoi;
	}
	public void setIdBoCauHoi(BoCauHoi idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}
	public String getTenCauHoi() {
		return tenCauHoi;
	}
	public void setTenCauHoi(String tenCauhoi) {
		this.tenCauHoi = tenCauhoi;
	}
	public int getDiemToiDa() {
		return diemToiDa;
	}
	public void setDiemToiDa(int diemToiDa) {
		this.diemToiDa = diemToiDa;
	}
	public boolean isTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	

}
