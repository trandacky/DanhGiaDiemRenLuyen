package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A TaiKhoan.
 */
@Entity
@Table(name = "tai_khoan")
public class TaiKhoan{
    @Id
    private Long maSinhVien;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "quyen")
    private Integer quyen;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_thang_nam_sinh")
    private LocalDate ngayThangNamSinh;

    @OneToMany(mappedBy = "maSinhVien", cascade =  CascadeType.ALL)
    private Set<PhieuRenLuyen> phieuRenLuyens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "idLop", nullable = false)
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

    public Integer getQuyen() {
        return quyen;
    }

    public TaiKhoan quyen(Integer quyen) {
        this.quyen = quyen;
        return this;
    }

    public void setQuyen(Integer quyen) {
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

    public Set<PhieuRenLuyen> getPhieuRenLuyens() {
        return phieuRenLuyens;
    }

    public TaiKhoan phieuRenLuyens(Set<PhieuRenLuyen> phieuRenLuyens) {
        this.phieuRenLuyens = phieuRenLuyens;
        return this;
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

    public void setPhieuRenLuyens(Set<PhieuRenLuyen> phieuRenLuyens) {
        this.phieuRenLuyens = phieuRenLuyens;
    }

	public Long getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(Long maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public Lop getIdLop() {
		return idLop;
	}

	public void setIdLop(Lop idLop) {
		this.idLop = idLop;
	}

   
}
