package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A TaiKhoan.
 */
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan{
	 
		public TaiKhoan() {
			super();
		}
    public TaiKhoan(String maSinhVien, String matKhau, int quyen, String ten, LocalDate ngayThangNamSinh,
				Lop idLop) {
			super();
			this.maSinhVien = maSinhVien;
			this.matKhau = matKhau;
			this.quyen = quyen;
			this.ten = ten;
			this.ngayThangNamSinh = ngayThangNamSinh;
			this.idLop = idLop;
		}
	@Id
    private String maSinhVien;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "quyen")
    private int quyen;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_thang_nam_sinh")
    private LocalDate ngayThangNamSinh;

    @OneToMany(mappedBy = "maSinhVien", cascade =  CascadeType.ALL)
    private List<PhieuRenLuyen> phieuRenLuyens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idLop", nullable = true)
    private Lop idLop;

   
    public String getMatKhau() {
        return matKhau;
    }

    public TaiKhoan matKhau(String matKhau) {
        this.matKhau = matKhau;
        return this;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public TaiKhoan quyen(int quyen) {
        this.quyen = quyen;
        return this;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    public String getTen() {
        return ten;
    }

    public TaiKhoan ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgayThangNamSinh() {
        return ngayThangNamSinh;
    }

    public TaiKhoan ngayThangNamSinh(LocalDate ngayThangNamSinh) {
        this.ngayThangNamSinh = ngayThangNamSinh;
        return this;
    }

    public void setNgayThangNamSinh(LocalDate ngayThangNamSinh) {
        this.ngayThangNamSinh = ngayThangNamSinh;
    }

    public List<PhieuRenLuyen> getPhieuRenLuyens() {
        return phieuRenLuyens;
    }


    public void setPhieuRenLuyens(List<PhieuRenLuyen> phieuRenLuyens) {
		this.phieuRenLuyens = phieuRenLuyens;
	}

	public TaiKhoan addPhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyens.add(phieuRenLuyen);
        phieuRenLuyen.setMaSinhVien(this);
        return this;
    }

    public TaiKhoan removePhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyens.remove(phieuRenLuyen);
        phieuRenLuyen.setMaSinhVien(null);
        return this;
    }

  

	

	public String getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
	public Lop getIdLop() {
		return idLop;
	}

	public void setIdLop(Lop idLop) {
		this.idLop = idLop;
	}

   
}
