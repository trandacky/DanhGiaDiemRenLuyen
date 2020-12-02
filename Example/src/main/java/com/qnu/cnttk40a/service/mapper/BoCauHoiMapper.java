package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.BoCauHoiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BoCauHoi} and its DTO {@link BoCauHoiDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BoCauHoiMapper extends EntityMapper<BoCauHoiDTO, BoCauHoi> {


    @Mapping(target = "cauHois", ignore = true)
    @Mapping(target = "removeCauHoi", ignore = true)
    BoCauHoi toEntity(BoCauHoiDTO boCauHoiDTO);

    default BoCauHoi fromId(Long id) {
        if (id == null) {
            return null;
        }
        BoCauHoi boCauHoi = new BoCauHoi();
        boCauHoi.setId(id);
        return boCauHoi;
    }
}
