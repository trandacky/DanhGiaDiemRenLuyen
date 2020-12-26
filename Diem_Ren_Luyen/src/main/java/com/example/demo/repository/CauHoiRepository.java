package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
@Repository
public interface CauHoiRepository extends JpaRepository<CauHoi, Long>{

	List<CauHoi> findAllByOrderByIdCauHoiAsc();

	List<CauHoi> findByIdCauHoiOrNoiDungCauHoiLike(long seachString2, String seachString);

	List<CauHoi> findByIdBoCauHoi(BoCauHoi bocauhoi);

	List<CauHoi> findByTinhTrangTrue();

	// ví dụ 1 cái về việc tùy chỉnh
	/*
	 * @Query(value="SELECT * " +"FROM public.cauhoi where idCauHoi=:id",nativeQuery
	 * = true) Optional<CauHoi> getByID(@Param("id") Long id) ;
	 */
	
}
