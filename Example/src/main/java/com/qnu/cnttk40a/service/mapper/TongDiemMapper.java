package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.TongDiemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TongDiem} and its DTO {@link TongDiemDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TongDiemMapper extends EntityMapper<TongDiemDTO, TongDiem> {


    @Mapping(target = "phieuRenLuyen", ignore = true)
    TongDiem toEntity(TongDiemDTO tongDiemDTO);

    default TongDiem fromId(Long id) {
        if (id == null) {
            return null;
        }
        TongDiem tongDiem = new TongDiem();
        tongDiem.setId(id);
        return tongDiem;
    }
}
