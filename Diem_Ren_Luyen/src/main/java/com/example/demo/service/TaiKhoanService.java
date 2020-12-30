package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.CauHoi;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.dto.TaiKhoanDTO;

import java.util.List;

@Repository
public interface TaiKhoanService {

	TaiKhoan setData(TaiKhoan taiKhoan);

	Optional<Object> delete(String id);

	Optional<TaiKhoan> getByID(String id);

	List<TaiKhoan> getAll();
	Optional<TaiKhoan> update(TaiKhoanDTO taiKhoanDTO);
	Optional<Object> update(TaiKhoan TaiKhoan);
	

	List<TaiKhoan> getTaiKhoanByIDLop(long idLop);
	List<TaiKhoan> getTaiKhoanSinhVien();

	List<TaiKhoan> search(String searchString);

	Optional<Object> updatelop(String idTaiKhoan, Long idLop);

}
