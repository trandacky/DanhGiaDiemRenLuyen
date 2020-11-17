package com.example.demo.entity;

import java.util.List;

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
	private String tenBoCauhoi;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "boCauHoi", cascade = CascadeType.ALL)
	private List<CauHoi> cauHoi;

	public long getIdBoCauHoi() {
		return idBoCauHoi;
	}

	public void setIdBoCauHoi(long idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}

	public String getTenBoCauhoi() {
		return tenBoCauhoi;
	}

	public void setTenBoCauhoi(String tenBoCauhoi) {
		this.tenBoCauhoi = tenBoCauhoi;
	}

	/*
	 *để tránh dính đệ quy khi get CauHoi cần comment lại
	 * public List<CauHoi> getCauHoi() { return cauHoi; }
	 */

	public void setCauHoi(List<CauHoi> cauHoi) {
		this.cauHoi = cauHoi;
	}

}
