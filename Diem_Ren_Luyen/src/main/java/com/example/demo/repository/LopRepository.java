package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entity.Lop;
@Repository
public interface LopRepository extends JpaRepository<Lop, Long>{
	List<Lop> findByIdLopOrTenLopLike(long seachString2, String seachString);
	
}
