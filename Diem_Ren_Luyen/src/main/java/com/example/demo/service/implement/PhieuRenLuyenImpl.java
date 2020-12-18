package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PhieuRenLuyen;
import com.example.demo.repository.PhieuRenLuyenRepository;
import com.example.demo.service.PhieuRenLuyenService;
@Service
public class PhieuRenLuyenImpl implements PhieuRenLuyenService{
	private final PhieuRenLuyenRepository phieuRenLuyenRepository;
	public PhieuRenLuyenImpl(PhieuRenLuyenRepository phieuRenLuyenRepository) {
		super();
		this.phieuRenLuyenRepository = phieuRenLuyenRepository;
	}
	@Override
	public List<PhieuRenLuyen> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<PhieuRenLuyen> getByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PhieuRenLuyen setData(PhieuRenLuyen phieuRenLuyen) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<PhieuRenLuyen> update(PhieuRenLuyen phieuRenLuyen) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Object> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

	