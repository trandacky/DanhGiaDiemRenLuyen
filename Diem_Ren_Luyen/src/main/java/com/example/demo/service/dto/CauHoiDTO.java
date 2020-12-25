package com.example.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.CauHoi} entity.
 */
public class CauHoiDTO{
    
    private Long idCauHoi;

    private String noiDungCauHoi;

    private Integer diemToiDa;

    private Boolean tinhTrang;


    private Long idBoCauHoi;


	public Long getIdCauHoi() {
		return idCauHoi;
	}


	public void setIdCauHoi(Long idCauHoi) {
		this.idCauHoi = idCauHoi;
	}




	public String getNoiDungCauHoi() {
		return noiDungCauHoi;
	}


	public void setNoiDungCauHoi(String noiDungCauHoi) {
		this.noiDungCauHoi = noiDungCauHoi;
	}


	public Integer getDiemToiDa() {
		return diemToiDa;
	}


	public void setDiemToiDa(Integer diemToiDa) {
		this.diemToiDa = diemToiDa;
	}


	public Boolean getTinhTrang() {
		return tinhTrang;
	}


	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}


	public Long getIdBoCauHoi() {
		return idBoCauHoi;
	}


	public void setIdBoCauHoi(Long idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}


	
    
}
