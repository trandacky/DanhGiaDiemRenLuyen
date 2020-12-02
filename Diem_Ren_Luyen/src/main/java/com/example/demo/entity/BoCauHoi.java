package com.example.demo.entity;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bo_cau_hoi")
public class BoCauHoi{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoCauHoi;

    @Column(name = "ten_bo_cau_hoi")
    private String tenBoCauHoi;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;

    @OneToMany(mappedBy = "idBoCauHoi",cascade = CascadeType.ALL)
    private Set<CauHoi> cauHois = new HashSet<>();

	public Long getIdBoCauHoi() {
		return idBoCauHoi;
	}

	public void setIdBoCauHoi(Long idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}

	public String getTenBoCauHoi() {
		return tenBoCauHoi;
	}

	public void setTenBoCauHoi(String tenBoCauHoi) {
		this.tenBoCauHoi = tenBoCauHoi;
	}

	public Boolean getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Set<CauHoi> getCauHois() {
		return cauHois;
	}

	/*
	 * public void setCauHois(Set<CauHoi> cauHois) { this.cauHois = cauHois; }
	 */


}
