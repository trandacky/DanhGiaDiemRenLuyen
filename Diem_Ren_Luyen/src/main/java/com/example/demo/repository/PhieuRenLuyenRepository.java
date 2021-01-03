package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhieuRenLuyen;
@Repository
public interface PhieuRenLuyenRepository extends JpaRepository<PhieuRenLuyen, Long>{


	List<PhieuRenLuyen> findAllByOrderByIdPhieuRenLuyenAsc();
	
	
	@Modifying
	@Query("update PhieuRenLuyen u set u.tongDiemLan3 = :tongdiem3 where u.idPhieuRenLuyen = :id")
	void updateTongDiemLan3(@Param(value = "tongdiem3") Integer tongdiem3, @Param(value = "id") Long id);
	

	// ví dụ 1 cái về việc tùy chỉnh
	/*
	 * @Query(value="SELECT * " +"FROM public.cauhoi where idCauHoi=:id",nativeQuery
	 * = true) Optional<CauHoi> getByID(@Param("id") Long id) ;
	 */
	
	
}
