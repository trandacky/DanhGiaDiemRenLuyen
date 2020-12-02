package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.CauHoi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CauHoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi, Long> {
}
