package com.qnu.cnttk40a.repository;

import com.qnu.cnttk40a.domain.Lop;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Lop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LopRepository extends JpaRepository<Lop, Long> {
}
