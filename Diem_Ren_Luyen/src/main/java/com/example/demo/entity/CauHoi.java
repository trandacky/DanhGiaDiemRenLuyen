package com.example.demo.entity;

import javax.persistence.CascadeType;
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idBoCauHoi", nullable = false)
	private BoCauHoi boCauHoi;
	
	@Column(name = "tenBoCauHoi")
	private String tenBoCauhoi;
	
	@Column(name = "diemToiDa")
	private int diemToiDa;

	public long getIdCauHoi() {
		return idCauHoi;
	}

	public void setIdCauHoi(long idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public BoCauHoi getBoCauHoi() {
		return boCauHoi;
	}

	public void setBoCauHoi(BoCauHoi boCauHoi) {
		this.boCauHoi = boCauHoi;
	}

	public String getTenBoCauhoi() {
		return tenBoCauhoi;
	}

	public void setTenBoCauhoi(String tenBoCauhoi) {
		this.tenBoCauhoi = tenBoCauhoi;
	}

	public int getDiemToiDa() {
		return diemToiDa;
	}

	public void setDiemToiDa(int diemToiDa) {
		this.diemToiDa = diemToiDa;
	}
	
	
}
