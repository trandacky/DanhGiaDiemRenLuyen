package com.qnu.cnttk40a.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Lop.
 */
@Entity
@Table(name = "lop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "lop")
public class Lop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_lop")
    private String tenLop;

    @Column(name = "khoa")
    private String khoa;

    @Column(name = "khoa_hoc")
    private Integer khoaHoc;

    @OneToMany(mappedBy = "idLop")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TaiKhoan> taiKhoans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lop)) {
            return false;
        }
        return id != null && id.equals(((Lop) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lop{" +
            "id=" + getId() +
            ", tenLop='" + getTenLop() + "'" +
            ", khoa='" + getKhoa() + "'" +
            ", khoaHoc=" + getKhoaHoc() +
            "}";
    }
}
