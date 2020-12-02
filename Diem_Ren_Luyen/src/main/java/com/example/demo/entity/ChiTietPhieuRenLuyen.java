package com.example.demo.entity;


import javax.persistence.*;

/**
 * A ChiTietPhieuRenLuyen.
 */
@Entity
@Table(name = "chi_tiet_phieu_ren_luyen")
public class ChiTietPhieuRenLuyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChiTietPhieuRenLuyen;

    @Column(name = "diem_lan_1")
    private Integer diemLan1;

    @Column(name = "diem_lan_2")
    private Integer diemLan2;

    @Column(name = "diem_lan_3")
    private Integer diemLan3;

    @ManyToOne
    @JoinColumn(name = "idCauHoi", nullable = false)
    private CauHoi idCauHoi;

    @ManyToOne
    @JoinColumn(name = "idPhieuRenLuyen", nullable = false)
    private PhieuRenLuyen idPhieuRenLuyen;

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




    public CauHoi getIdCauHoi() {
        return idCauHoi;
    }

    public ChiTietPhieuRenLuyen idCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
        return this;
    }

    public void setIdCauHoi(CauHoi cauHoi) {
        this.idCauHoi = cauHoi;
    }

	public Long getIdChiTietPhieuRenLuyen() {
		return idChiTietPhieuRenLuyen;
	}

	public void setIdChiTietPhieuRenLuyen(Long idChiTietPhieuRenLuyen) {
		this.idChiTietPhieuRenLuyen = idChiTietPhieuRenLuyen;
	}

	public PhieuRenLuyen getIdPhieuRenLuyen() {
		return idPhieuRenLuyen;
	}

	public void setIdPhieuRenLuyen(PhieuRenLuyen idPhieuRenLuyen) {
		this.idPhieuRenLuyen = idPhieuRenLuyen;
	}

    
}
