package com.example.demo.service.dto;

import javax.persistence.Entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.BoCauHoi} entity.
 */
public class BoCauHoiDTO{
    
    private Long idBoCauHoi;

    private String tenBoCauHoi;

    private Boolean tinhTrang;

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

    
}
