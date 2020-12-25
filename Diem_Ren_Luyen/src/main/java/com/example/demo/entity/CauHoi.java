package com.example.demo.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "cau_hoi")
public class CauHoi {
	
    public CauHoi() {
		super();
	}

	public CauHoi(Long idCauHoi, String noiDungCauHoi, Integer diemToiDa, Boolean tinhTrang, BoCauHoi idBoCauHoi) {
		super();
		this.idCauHoi = idCauHoi;
		this.noiDungCauHoi = noiDungCauHoi;
		this.diemToiDa = diemToiDa;
		this.idBoCauHoi = idBoCauHoi;
		this.tinhTrang = tinhTrang;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCauHoi;

    @Column(name = "noi_dung_cau_hoi")
    private String noiDungCauHoi;

    @Column(name = "diem_toi_da")
    private Integer diemToiDa;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;

    @OneToMany(mappedBy = "idCauHoi",cascade = CascadeType.ALL)
    private Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idBoCauHoi", nullable = true)
    private BoCauHoi idBoCauHoi;

   
    public Long getIdCauHoi() {
		return idCauHoi;
	}

	public void setIdCauHoi(Long idCauHoi) {
		this.idCauHoi = idCauHoi;
	}

	public String getNoiDungCauHoi() {
        return noiDungCauHoi;
    }

    public CauHoi noiDungCauHoi(String tenCauHoi) {
        this.noiDungCauHoi = tenCauHoi;
        return this;
    }

    public void setNoiDungCauHoi(String tenCauHoi) {
        this.noiDungCauHoi = tenCauHoi;
    }

    public Integer getDiemToiDa() {
        return diemToiDa;
    }

    public CauHoi diemToiDa(Integer diemToiDa) {
        this.diemToiDa = diemToiDa;
        return this;
    }

    public void setDiemToiDa(Integer diemToiDa) {
        this.diemToiDa = diemToiDa;
    }

    public Boolean isTinhTrang() {
        return tinhTrang;
    }

    public CauHoi tinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
        return this;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Set<ChiTietPhieuRenLuyen> getChiTietPhieuRenLuyens() {
        return chiTietPhieuRenLuyens;
    }

    public CauHoi chiTietPhieuRenLuyens(Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens) {
        this.chiTietPhieuRenLuyens = chiTietPhieuRenLuyens;
        return this;
    }

    public CauHoi addChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyens.add(chiTietPhieuRenLuyen);
        chiTietPhieuRenLuyen.setIdCauHoi(this);
        return this;
    }

    public CauHoi removeChiTietPhieuRenLuyen(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
        this.chiTietPhieuRenLuyens.remove(chiTietPhieuRenLuyen);
        chiTietPhieuRenLuyen.setIdCauHoi(null);
        return this;
    }

    public void setChiTietPhieuRenLuyens(Set<ChiTietPhieuRenLuyen> chiTietPhieuRenLuyens) {
        this.chiTietPhieuRenLuyens = chiTietPhieuRenLuyens;
    }

	public BoCauHoi getIdBoCauHoi() {
		return idBoCauHoi;
	}

	public void setIdBoCauHoi(BoCauHoi idBoCauHoi) {
		this.idBoCauHoi = idBoCauHoi;
	}

	public Boolean getTinhTrang() {
		return tinhTrang;
	}


}
