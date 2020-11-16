package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoCauHoi;
@Repository
public interface BoCauHoiRepository extends JpaRepository<BoCauHoi, Long>{

}
