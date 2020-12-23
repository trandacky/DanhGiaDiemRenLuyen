package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.dto.BoCauHoiDTO;
@Repository
public interface BoCauHoiRepository extends JpaRepository<BoCauHoi, Long>{
	List<BoCauHoi> findByIdBoCauHoiOrTenBoCauHoiLike(long seachString2, String seachString);


	List<BoCauHoi> findAllByOrderByIdBoCauHoiAsc();
	@Modifying
	@Query("update BoCauHoi u set u.tenBoCauHoi = :ten where u.idBoCauHoi = :id")
	void updateTenBoCauHoi(@Param(value = "id") long id, @Param(value = "ten") String ten);

}
