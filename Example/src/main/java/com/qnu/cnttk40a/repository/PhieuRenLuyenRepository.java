package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.PhieuRenLuyen;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PhieuRenLuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhieuRenLuyenRepository extends JpaRepository<PhieuRenLuyen, Long> {
}
