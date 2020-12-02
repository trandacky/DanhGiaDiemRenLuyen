package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.TaiKhoan;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TaiKhoan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
}
