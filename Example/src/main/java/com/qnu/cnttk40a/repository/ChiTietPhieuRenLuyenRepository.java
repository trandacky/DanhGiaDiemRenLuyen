package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.ChiTietPhieuRenLuyen;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ChiTietPhieuRenLuyen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTietPhieuRenLuyenRepository extends JpaRepository<ChiTietPhieuRenLuyen, Long> {
}
