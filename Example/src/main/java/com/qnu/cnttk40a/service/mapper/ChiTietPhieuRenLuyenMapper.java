package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.ChiTietPhieuRenLuyenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiTietPhieuRenLuyen} and its DTO {@link ChiTietPhieuRenLuyenDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChiTietPhieuRenLuyenMapper extends EntityMapper<ChiTietPhieuRenLuyenDTO, ChiTietPhieuRenLuyen> {


    @Mapping(target = "phieuRenLuyen", ignore = true)
    ChiTietPhieuRenLuyen toEntity(ChiTietPhieuRenLuyenDTO chiTietPhieuRenLuyenDTO);

    default ChiTietPhieuRenLuyen fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChiTietPhieuRenLuyen chiTietPhieuRenLuyen = new ChiTietPhieuRenLuyen();
        chiTietPhieuRenLuyen.setId(id);
        return chiTietPhieuRenLuyen;
    }
}
