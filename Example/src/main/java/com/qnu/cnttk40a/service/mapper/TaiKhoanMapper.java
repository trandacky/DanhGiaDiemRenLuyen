package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.TaiKhoanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaiKhoan} and its DTO {@link TaiKhoanDTO}.
 */
@Mapper(componentModel = "spring", uses = {LopMapper.class})
public interface TaiKhoanMapper extends EntityMapper<TaiKhoanDTO, TaiKhoan> {

    @Mapping(source = "idLop.id", target = "idLopId")
    @Mapping(source = "idLop.id", target = "idLopId")
    TaiKhoanDTO toDto(TaiKhoan taiKhoan);

    @Mapping(target = "phieuRenLuyens", ignore = true)
    @Mapping(target = "removePhieuRenLuyen", ignore = true)
    @Mapping(source = "idLopId", target = "idLop")
    @Mapping(source = "idLopId", target = "idLop")
    TaiKhoan toEntity(TaiKhoanDTO taiKhoanDTO);

    default TaiKhoan fromId(Long id) {
        if (id == null) {
            return null;
        }
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(id);
        return taiKhoan;
    }
}
