package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.service.dto.ChiTietPhieuRenLuyenDTO;

public interface ChiTietPhieuRenLuyenService {
	List<ChiTietPhieuRenLuyen> getAll();
	Optional<ChiTietPhieuRenLuyen> getByID(long id);
	ChiTietPhieuRenLuyen setData(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen);
	Optional<ChiTietPhieuRenLuyen> update(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen);
	Optional<Object> delete(Long id);
	void updateChiTietPhieuRenLuyenSetDiemlan3ForId(Integer diem3, Long id);
	Optional<ChiTietPhieuRenLuyen> updateDiemLan3(Integer diem3, Long id);
	Optional<ChiTietPhieuRenLuyen> updateDiemLan1Va2(Integer diem1, Long id);
}
