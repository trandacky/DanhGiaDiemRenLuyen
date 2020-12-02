package com.example.demo.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tong_diem")
public class TongDiem{
	
	@OneToOne(mappedBy = "tongDiem")
    @JsonIgnore
    private PhieuRenLuyen phieuRenLuyen;
	
    @Column(name = "tong_diemlan_1")
    private Integer tongDiemlan1;

    @Column(name = "tong_diemlan_2")
    private Integer tongDiemlan2;

    @Column(name = "tong_diemlan_3")
    private Integer tongDiemlan3;

    

	public Integer getTongDiemlan1() {
        return tongDiemlan1;
    }

    public TongDiem tongDiemlan1(Integer tongDiemlan1) {
        this.tongDiemlan1 = tongDiemlan1;
        return this;
    }

    public void setTongDiemlan1(Integer tongDiemlan1) {
        this.tongDiemlan1 = tongDiemlan1;
    }

    public Integer getTongDiemlan2() {
        return tongDiemlan2;
    }

    public TongDiem tongDiemlan2(Integer tongDiemlan2) {
        this.tongDiemlan2 = tongDiemlan2;
        return this;
    }

    public void setTongDiemlan2(Integer tongDiemlan2) {
        this.tongDiemlan2 = tongDiemlan2;
    }

    public Integer getTongDiemlan3() {
        return tongDiemlan3;
    }

    public TongDiem tongDiemlan3(Integer tongDiemlan3) {
        this.tongDiemlan3 = tongDiemlan3;
        return this;
    }

    public void setTongDiemlan3(Integer tongDiemlan3) {
        this.tongDiemlan3 = tongDiemlan3;
    }

    public PhieuRenLuyen getPhieuRenLuyen() {
        return phieuRenLuyen;
    }

    public TongDiem phieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyen = phieuRenLuyen;
        return this;
    }

    public void setPhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyen = phieuRenLuyen;
    }
}
