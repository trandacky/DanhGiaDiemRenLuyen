package com.example.demo.entity;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A Lop.
 */
@Entity
@Table(name = "lop")
public class Lop{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLop;

    @Column(name = "ten_lop")
    private String tenLop;

    @Column(name = "khoa")
    private String khoa;

    @Column(name = "khoa_hoc")
    private Integer khoaHoc;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;
    
    @OneToMany(mappedBy = "idLop", cascade = CascadeType.ALL)
    private Set<TaiKhoan> taiKhoans = new HashSet<>();

    public Lop(Long idLop, String tenLop, String khoa, Integer khoaHoc, Boolean tinhTrang) {
		super();
		this.idLop = idLop;
		this.tenLop = tenLop;
		this.khoa = khoa;
		this.khoaHoc = khoaHoc;
		this.tinhTrang = tinhTrang;
	}
    
    public Lop() {
		super();
	}

    public String getTenLop() {
        return tenLop;
    }

    public Lop tenLop(String tenLop) {
        this.tenLop = tenLop;
        return this;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getKhoa() {
        return khoa;
    }

    public Lop khoa(String khoa) {
        this.khoa = khoa;
        return this;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public Integer getKhoaHoc() {
        return khoaHoc;
    }

    public Lop khoaHoc(Integer khoaHoc) {
        this.khoaHoc = khoaHoc;
        return this;
    }

    public void setKhoaHoc(Integer khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    
    public Boolean getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(Boolean tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

    public Set<TaiKhoan> getTaiKhoans() {
        return taiKhoans;
    }

    public Lop taiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
        return this;
    }

    public Lop addTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoans.add(taiKhoan);
        taiKhoan.setIdLop(this);
        return this;
    }

    public Lop removeTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoans.remove(taiKhoan);
        taiKhoan.setIdLop(null);
        return this;
    }

    public void setTaiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
    }

	public Long getIdLop() {
		return idLop;
	}

	public void setIdLop(Long idLop) {
		this.idLop = idLop;
	}
    
    
}
