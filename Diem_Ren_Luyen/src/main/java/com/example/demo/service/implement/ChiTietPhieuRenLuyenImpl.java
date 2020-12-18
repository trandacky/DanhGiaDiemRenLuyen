package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.repository.ChiTietPhieuRenLuyenRepository;
import com.example.demo.service.ChiTietPhieuRenLuyenService;

@Service
public class ChiTietPhieuRenLuyenImpl implements ChiTietPhieuRenLuyenService {
	private final ChiTietPhieuRenLuyenRepository chiTietPhieuRenLuyenRepository;

	public ChiTietPhieuRenLuyenImpl(ChiTietPhieuRenLuyenRepository chiTietPhieuRenLuyenRepository) {
		super();
		this.chiTietPhieuRenLuyenRepository = chiTietPhieuRenLuyenRepository;
	}

	@Override
	public List<ChiTietPhieuRenLuyen> getAll() {
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.findAll();
	}

	@Override
	public ChiTietPhieuRenLuyen setData(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.save(chiTietPhieuRenLuyen);
	}

	@Override
	public Optional<ChiTietPhieuRenLuyen> update(ChiTietPhieuRenLuyen chiTietPhieuRenLuyen) {
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.findById(chiTietPhieuRenLuyen.getIdChiTietPhieuRenLuyen()).map(chitietphieurenluyen -> {
			chitietphieurenluyen = chiTietPhieuRenLuyen;
			return chiTietPhieuRenLuyenRepository.save(chitietphieurenluyen);
		});
	}

	@Override
	public Optional<Object> delete(Long id) {
		return chiTietPhieuRenLuyenRepository.findById(id).map(chitietphieurenluyen -> {
			chiTietPhieuRenLuyenRepository.delete(chitietphieurenluyen);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public Optional<ChiTietPhieuRenLuyen> getByID(long id) {
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.findById(id);
	}

}
