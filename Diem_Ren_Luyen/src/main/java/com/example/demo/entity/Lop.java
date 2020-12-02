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
    private int khoaHoc;

    @OneToMany(mappedBy = "idLop")
    private Set<TaiKhoan> taiKhoans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return idLop;
    }

    public void setId(Long id) {
        this.idLop = id;
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

    public Set<TaiKhoan> getTaiKhoans() {
        return taiKhoans;
    }

    public Lop taiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
        return this;
    }    

    public void setTaiKhoans(Set<TaiKhoan> taiKhoans) {
        this.taiKhoans = taiKhoans;
    }

}
