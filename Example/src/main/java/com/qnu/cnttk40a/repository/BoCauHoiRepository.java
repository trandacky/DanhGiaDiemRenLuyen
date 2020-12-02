package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.BoCauHoi;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BoCauHoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoCauHoiRepository extends JpaRepository<BoCauHoi, Long> {
}
