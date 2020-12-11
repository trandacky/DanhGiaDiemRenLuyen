package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhieuRenLuyen;
@Repository
public interface PhieuRenLuyenRepository extends JpaRepository<PhieuRenLuyen, Long>{

	// ví dụ 1 cái về việc tùy chỉnh
	/*
	 * @Query(value="SELECT * " +"FROM public.cauhoi where idCauHoi=:id",nativeQuery
	 * = true) Optional<CauHoi> getByID(@Param("id") Long id) ;
	 */
	
	
}