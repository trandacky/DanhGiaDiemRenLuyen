package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.PhieuRenLuyen;

public interface PhieuRenLuyenService {

	List<PhieuRenLuyen> getAll();
	Optional<PhieuRenLuyen> getByID(long id);
	PhieuRenLuyen setData(PhieuRenLuyen phieuRenLuyen);
	Optional<PhieuRenLuyen> update(PhieuRenLuyen phieuRenLuyen);
	Optional<Object> delete(Long id);
	Optional<PhieuRenLuyen> updateDuyetLan2(long idPhieu);
	Optional<PhieuRenLuyen> updateDuyetLan2True(Long idPhieu);
}
