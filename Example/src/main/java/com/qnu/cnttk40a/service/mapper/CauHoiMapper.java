package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.CauHoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CauHoi} and its DTO {@link CauHoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {BoCauHoiMapper.class})
public interface CauHoiMapper extends EntityMapper<CauHoiDTO, CauHoi> {

    @Mapping(source = "idBoCauHoi.id", target = "idBoCauHoiId")
    @Mapping(source = "idBoCauHoi.id", target = "idBoCauHoiId")
    CauHoiDTO toDto(CauHoi cauHoi);

    @Mapping(target = "phieuRenLuyens", ignore = true)
    @Mapping(target = "removePhieuRenLuyen", ignore = true)
    @Mapping(source = "idBoCauHoiId", target = "idBoCauHoi")
    @Mapping(source = "idBoCauHoiId", target = "idBoCauHoi")
    CauHoi toEntity(CauHoiDTO cauHoiDTO);

    default CauHoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        CauHoi cauHoi = new CauHoi();
        cauHoi.setId(id);
        return cauHoi;
    }
}
