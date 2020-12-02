package com.qnu.cnttk40a.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A CauHoi.
 */
@Entity
@Table(name = "cau_hoi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "cauhoi")
public class CauHoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_cau_hoi")
    private String tenCauHoi;

    @Column(name = "diem_toi_da")
    private Integer diemToiDa;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;

    @OneToMany(mappedBy = "idCauHoi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PhieuRenLuyen> phieuRenLuyens = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "cauHois", allowSetters = true)
    private BoCauHoi idBoCauHoi;

    @ManyToOne
    @JsonIgnoreProperties(value = "cauHois", allowSetters = true)
    private BoCauHoi idBoCauHoi;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenCauHoi() {
        return tenCauHoi;
    }

    public CauHoi tenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
        return this;
    }

    public void setTenCauHoi(String tenCauHoi) {
        this.tenCauHoi = tenCauHoi;
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

    public Set<PhieuRenLuyen> getPhieuRenLuyens() {
        return phieuRenLuyens;
    }

    public CauHoi phieuRenLuyens(Set<PhieuRenLuyen> phieuRenLuyens) {
        this.phieuRenLuyens = phieuRenLuyens;
        return this;
    }

    public CauHoi addPhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyens.add(phieuRenLuyen);
        phieuRenLuyen.setIdCauHoi(this);
        return this;
    }

    public CauHoi removePhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyens.remove(phieuRenLuyen);
        phieuRenLuyen.setIdCauHoi(null);
        return this;
    }

    public void setPhieuRenLuyens(Set<PhieuRenLuyen> phieuRenLuyens) {
        this.phieuRenLuyens = phieuRenLuyens;
    }

    public BoCauHoi getIdBoCauHoi() {
        return idBoCauHoi;
    }

    public CauHoi idBoCauHoi(BoCauHoi boCauHoi) {
        this.idBoCauHoi = boCauHoi;
        return this;
    }

    public void setIdBoCauHoi(BoCauHoi boCauHoi) {
        this.idBoCauHoi = boCauHoi;
    }

    public BoCauHoi getIdBoCauHoi() {
        return idBoCauHoi;
    }

    public CauHoi idBoCauHoi(BoCauHoi boCauHoi) {
        this.idBoCauHoi = boCauHoi;
        return this;
    }

    public void setIdBoCauHoi(BoCauHoi boCauHoi) {
        this.idBoCauHoi = boCauHoi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CauHoi)) {
            return false;
        }
        return id != null && id.equals(((CauHoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CauHoi{" +
            "id=" + getId() +
            ", tenCauHoi='" + getTenCauHoi() + "'" +
            ", diemToiDa=" + getDiemToiDa() +
            ", tinhTrang='" + isTinhTrang() + "'" +
            "}";
    }
}
