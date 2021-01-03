package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;
@Repository
public interface ChiTietPhieuRenLuyenRepository extends JpaRepository<ChiTietPhieuRenLuyen, Long>{

	@Modifying
	@Query("update ChiTietPhieuRenLuyen u set u.diemLan3 = :diem3 where u.idCauHoi = :id")
	void updateDiemLan3(@Param(value = "diem3") Integer diem3, @Param(value = "id") CauHoi id);
	
	
	
	
}
