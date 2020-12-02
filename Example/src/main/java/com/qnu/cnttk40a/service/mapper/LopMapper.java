package com.qnu.cnttk40a.service.mapper;


import com.qnu.cnttk40a.domain.*;
import com.qnu.cnttk40a.service.dto.LopDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lop} and its DTO {@link LopDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LopMapper extends EntityMapper<LopDTO, Lop> {


    @Mapping(target = "taiKhoans", ignore = true)
    @Mapping(target = "removeTaiKhoan", ignore = true)
    Lop toEntity(LopDTO lopDTO);

    default Lop fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lop lop = new Lop();
        lop.setId(id);
        return lop;
    }
}
