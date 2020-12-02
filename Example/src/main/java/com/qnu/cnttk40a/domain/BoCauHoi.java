package com.qnu.cnttk40a.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A BoCauHoi.
 */
@Entity
@Table(name = "bo_cau_hoi")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "bocauhoi")
public class BoCauHoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_bo_cau_hoi")
    private String tenBoCauHoi;

    @Column(name = "tinh_trang")
    private Boolean tinhTrang;

    @OneToMany(mappedBy = "idBoCauHoi")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<CauHoi> cauHois = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenBoCauHoi() {
        return tenBoCauHoi;
    }

    public BoCauHoi tenBoCauHoi(String tenBoCauHoi) {
        this.tenBoCauHoi = tenBoCauHoi;
        return this;
    }

    public void setTenBoCauHoi(String tenBoCauHoi) {
        this.tenBoCauHoi = tenBoCauHoi;
    }

    public Boolean isTinhTrang() {
        return tinhTrang;
    }

    public BoCauHoi tinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
        return this;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Set<CauHoi> getCauHois() {
        return cauHois;
    }

    public BoCauHoi cauHois(Set<CauHoi> cauHois) {
        this.cauHois = cauHois;
        return this;
    }

    public BoCauHoi addCauHoi(CauHoi cauHoi) {
        this.cauHois.add(cauHoi);
        cauHoi.setIdBoCauHoi(this);
        return this;
    }

    public BoCauHoi removeCauHoi(CauHoi cauHoi) {
        this.cauHois.remove(cauHoi);
        cauHoi.setIdBoCauHoi(null);
        return this;
    }

    public void setCauHois(Set<CauHoi> cauHois) {
        this.cauHois = cauHois;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoCauHoi)) {
            return false;
        }
        return id != null && id.equals(((BoCauHoi) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BoCauHoi{" +
            "id=" + getId() +
            ", tenBoCauHoi='" + getTenBoCauHoi() + "'" +
            ", tinhTrang='" + isTinhTrang() + "'" +
            "}";
    }
}
