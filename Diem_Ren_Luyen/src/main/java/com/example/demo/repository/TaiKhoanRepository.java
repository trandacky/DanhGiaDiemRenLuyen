package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.Lop;
import com.example.demo.entity.TaiKhoan;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long>{
	List<TaiKhoan> findByIdLop(Lop lop);
	List<TaiKhoan> findByQuyen(int quyen);
}
