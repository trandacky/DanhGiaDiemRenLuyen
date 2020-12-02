package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.PhieuRenLuyenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PhieuRenLuyen} and its DTO {@link PhieuRenLuyenDTO}.
 */
@Mapper(componentModel = "spring", uses = {TongDiemMapper.class, ChiTietPhieuRenLuyenMapper.class, TaiKhoanMapper.class, CauHoiMapper.class})
public interface PhieuRenLuyenMapper extends EntityMapper<PhieuRenLuyenDTO, PhieuRenLuyen> {

    @Mapping(source = "tongDiem.id", target = "tongDiemId")
    @Mapping(source = "chiTietPhieuRenLuyen.id", target = "chiTietPhieuRenLuyenId")
    @Mapping(source = "maSinhVien.id", target = "maSinhVienId")
    @Mapping(source = "idCauHoi.id", target = "idCauHoiId")
    @Mapping(source = "maSinhVien.id", target = "maSinhVienId")
    @Mapping(source = "idCauHoi.id", target = "idCauHoiId")
    PhieuRenLuyenDTO toDto(PhieuRenLuyen phieuRenLuyen);

    @Mapping(source = "tongDiemId", target = "tongDiem")
    @Mapping(source = "chiTietPhieuRenLuyenId", target = "chiTietPhieuRenLuyen")
    @Mapping(source = "maSinhVienId", target = "maSinhVien")
    @Mapping(source = "idCauHoiId", target = "idCauHoi")
    @Mapping(source = "maSinhVienId", target = "maSinhVien")
    @Mapping(source = "idCauHoiId", target = "idCauHoi")
    PhieuRenLuyen toEntity(PhieuRenLuyenDTO phieuRenLuyenDTO);

    default PhieuRenLuyen fromId(Long id) {
        if (id == null) {
            return null;
        }
        PhieuRenLuyen phieuRenLuyen = new PhieuRenLuyen();
        phieuRenLuyen.setId(id);
        return phieuRenLuyen;
    }
}
