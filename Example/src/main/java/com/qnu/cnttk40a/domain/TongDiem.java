package com.qnu.cnttk40a.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A TongDiem.
 */
@Entity
@Table(name = "tong_diem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "tongdiem")
public class TongDiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tong_diemlan_1")
    private Integer tongDiemlan1;

    @Column(name = "tong_diemlan_2")
    private Integer tongDiemlan2;

    @Column(name = "tong_diemlan_3")
    private Integer tongDiemlan3;

    @OneToOne(mappedBy = "tongDiem")
    @JsonIgnore
    private PhieuRenLuyen phieuRenLuyen;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTongDiemlan1() {
        return tongDiemlan1;
    }

    public TongDiem tongDiemlan1(Integer tongDiemlan1) {
        this.tongDiemlan1 = tongDiemlan1;
        return this;
    }

    public void setTongDiemlan1(Integer tongDiemlan1) {
        this.tongDiemlan1 = tongDiemlan1;
    }

    public Integer getTongDiemlan2() {
        return tongDiemlan2;
    }

    public TongDiem tongDiemlan2(Integer tongDiemlan2) {
        this.tongDiemlan2 = tongDiemlan2;
        return this;
    }

    public void setTongDiemlan2(Integer tongDiemlan2) {
        this.tongDiemlan2 = tongDiemlan2;
    }

    public Integer getTongDiemlan3() {
        return tongDiemlan3;
    }

    public TongDiem tongDiemlan3(Integer tongDiemlan3) {
        this.tongDiemlan3 = tongDiemlan3;
        return this;
    }

    public void setTongDiemlan3(Integer tongDiemlan3) {
        this.tongDiemlan3 = tongDiemlan3;
    }

    public PhieuRenLuyen getPhieuRenLuyen() {
        return phieuRenLuyen;
    }

    public TongDiem phieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyen = phieuRenLuyen;
        return this;
    }

    public void setPhieuRenLuyen(PhieuRenLuyen phieuRenLuyen) {
        this.phieuRenLuyen = phieuRenLuyen;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TongDiem)) {
            return false;
        }
        return id != null && id.equals(((TongDiem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TongDiem{" +
            "id=" + getId() +
            ", tongDiemlan1=" + getTongDiemlan1() +
            ", tongDiemlan2=" + getTongDiemlan2() +
            ", tongDiemlan3=" + getTongDiemlan3() +
            "}";
    }
}
