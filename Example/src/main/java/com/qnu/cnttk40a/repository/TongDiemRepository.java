package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.TongDiem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TongDiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TongDiemRepository extends JpaRepository<TongDiem, Long> {
}
