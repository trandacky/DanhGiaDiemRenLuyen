package com.example.demo.service.dto;

import java.time.LocalDate;

import com.example.demo.entity.Lop;

import java.io.Serializable;

/**
 * A DTO for the {@link com.qnu.cnttk40a.domain.TaiKhoan} entity.
 */
public class TaiKhoanDTO implements Serializable {
    
    private String id;

    private String matKhau;

    private int quyen;

    private String ten;

    private LocalDate ngayThangNamSinh;


    private Lop idLopId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getQuyen() {
        return quyen;
    }

    public void setQuyen(int quyen) {
        this.quyen = quyen;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public LocalDate getNgayThangNamSinh() {
        return ngayThangNamSinh;
    }

    public void setNgayThangNamSinh(LocalDate ngayThangNamSinh) {
        this.ngayThangNamSinh = ngayThangNamSinh;
    }



    public Lop getIdLop() {
		return idLopId;
	}

	public void setIdLop(Lop lop) {
		this.idLopId = lop;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaiKhoanDTO)) {
            return false;
        }

        return id != null && id.equals(((TaiKhoanDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaiKhoanDTO{" +
            "id=" + getId() +
            ", matKhau='" + getMatKhau() + "'" +
            ", quyen='" + getQuyen() + "'" +
            ", ten='" + getTen() + "'" +
            ", ngayThangNamSinh='" + getNgayThangNamSinh() + "'" +
            ", idLopId=" + getIdLop() +
            "}";
    }
}
