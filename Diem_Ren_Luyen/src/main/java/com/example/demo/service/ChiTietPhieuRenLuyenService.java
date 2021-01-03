package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;

public interface ChiTietPhieuRenLuyenService {
	List<ChiTietPhieuRenLuyen> getAll();
	Optional<ChiTietPhieuRenLuyen> getByID(long id);
	ChiTietPhieuRenLuyen setData(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen);
	Optional<ChiTietPhieuRenLuyen> update(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen);
	Optional<Object> delete(Long id);
	void updateDiemLan3(Integer diem3, CauHoi id);
}
