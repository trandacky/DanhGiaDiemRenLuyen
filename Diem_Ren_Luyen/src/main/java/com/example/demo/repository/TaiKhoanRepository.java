package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.Lop;
import com.example.demo.entity.TaiKhoan;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String>{
	List<TaiKhoan> findByIdLop(Lop lop);
	List<TaiKhoan> findByQuyenOrQuyen(int quyen,int quyen2);
	List<TaiKhoan> findByMaSinhVienOrTenLike(String x, String y);
	
}
