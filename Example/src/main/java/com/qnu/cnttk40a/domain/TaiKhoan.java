package com.qnu.cnttk40a.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.qnu.cnttk40a.domain.enumeration.Quyen;

/**
 * A TaiKhoan.
 */
@Entity
@Table(name = "tai_khoan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "taikhoan")
public class TaiKhoan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "mat_khau")
    private String matKhau;

    @Enumerated(EnumType.STRING)
    @Column(name = "quyen")
    private Quyen quyen;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_thang_nam_sinh")
    private LocalDate ngayThangNamSinh;

    @OneToMany(mappedBy = "maSinhVien")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PhieuRenLuyen> phieuRenLuyens = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "taiKhoans", allowSetters = true)
    private Lop idLop;

    @ManyToOne
    @JsonIgnoreProperties(value = "taiKhoans", allowSetters = true)
    private Lop idLop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Quyen getQuyen() {
        return quyen;
    }

    public TaiKhoan quyen(Quyen quyen) {
        this.quyen = quyen;
        return this;
    }

    public void setQuyen(Quyen quyen) {
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

    public Lop getIdLop() {
        return idLop;
    }

    public TaiKhoan idLop(Lop lop) {
        this.idLop = lop;
        return this;
    }

    public void setIdLop(Lop lop) {
        this.idLop = lop;
    }

    public Lop getIdLop() {
        return idLop;
    }

    public TaiKhoan idLop(Lop lop) {
        this.idLop = lop;
        return this;
    }

    public void setIdLop(Lop lop) {
        this.idLop = lop;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiKhoan)) {
            return false;
        }
        return id != null && id.equals(((TaiKhoan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiKhoan{" +
            "id=" + getId() +
            ", matKhau='" + getMatKhau() + "'" +
            ", quyen='" + getQuyen() + "'" +
            ", ten='" + getTen() + "'" +
            ", ngayThangNamSinh='" + getNgayThangNamSinh() + "'" +
            "}";
    }
}
