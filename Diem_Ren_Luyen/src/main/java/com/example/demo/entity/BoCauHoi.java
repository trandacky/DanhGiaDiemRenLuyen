package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "BoCauHoi")
public class BoCauHoi {
	@Id
	@Column(name = "idBoCauHoi")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idBoCauHoi;

	@Column(name = "tenBoCauHoi")
	private String tenBoCauHoi;
	
	@Column(name = "tinhTrang")
	private boolean tinhTrang;
	
	@OneToMany(mappedBy = "idBoCauHoi", cascade = CascadeType.ALL)
	private List<CauHoi> cauHoi;

	public long getIdBoCauHoi() {
		return idBoCauHoi;
	}

	public void setIdBoCauHoi(long idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}

	public String getTenBoCauHoi() {
		return tenBoCauHoi;
	}

	public void setTenBoCauHoi(String tenBoCauHoi) {
		this.tenBoCauHoi = tenBoCauHoi;
	}

	/*
	 *để tránh dính đệ quy khi get CauHoi cần comment lại
	 * public List<CauHoi> getCauHoi() { return cauHoi; }
	 */

	public void setCauHoi(List<CauHoi> cauHoi) {
		this.cauHoi = cauHoi;
	}

	public boolean isTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}



}
