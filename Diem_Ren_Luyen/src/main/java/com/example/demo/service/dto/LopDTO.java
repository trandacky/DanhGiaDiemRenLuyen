package com.example.demo.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.Lop} entity.
 */
public class LopDTO implements Serializable {
    
    private Long id;

    private String tenLop;

    private String khoa;

    private Integer khoaHoc;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public Integer getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(Integer khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LopDTO)) {
            return false;
        }

        return id != null && id.equals(((LopDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LopDTO{" +
            "id=" + getId() +
            ", tenLop='" + getTenLop() + "'" +
            ", khoa='" + getKhoa() + "'" +
            ", khoaHoc=" + getKhoaHoc() +
            "}";
    }
}
