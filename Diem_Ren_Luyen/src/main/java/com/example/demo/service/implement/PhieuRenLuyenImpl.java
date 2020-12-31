package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
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
		return phieuRenLuyenRepository.findAllByOrderByIdPhieuRenLuyenAsc();
	}
	@Override
	public Optional<PhieuRenLuyen> getByID(long id) {
		// TODO Auto-generated method stub
		return phieuRenLuyenRepository.findById(id);
	}
	@Override
	public PhieuRenLuyen setData(PhieuRenLuyen phieuRenLuyen) {
		// TODO Auto-generated method stub
		return phieuRenLuyenRepository.save(phieuRenLuyen);
	}
	@Override
	public Optional<PhieuRenLuyen> update(PhieuRenLuyen phieuRenLuyen) {
		return phieuRenLuyenRepository.findById(phieuRenLuyen.getIdPhieuRenLuyen()).map(phieurenluyen -> {
			phieurenluyen = phieuRenLuyen;
			return phieuRenLuyenRepository.save(phieurenluyen);
		});
	}
	@Override
	public Optional<PhieuRenLuyen> updateDuyetLan2(long IdPhieu) {
		return phieuRenLuyenRepository.findById(IdPhieu).map(phieurenluyen -> {
			
			phieurenluyen.setDaDuyetLan2(!phieurenluyen.getDaDuyetLan2());
			return phieuRenLuyenRepository.save(phieurenluyen);
		});
	}
	@Override
	public Optional<PhieuRenLuyen> updateDuyetLan2True(Long idPhieu) {
		
		return phieuRenLuyenRepository.findById(idPhieu).map(phieurenluyen -> {
			
			phieurenluyen.setDaDuyetLan2(true);
			return phieuRenLuyenRepository.save(phieurenluyen);
		});
		
	}
	@Override
	public Optional<Object> delete(Long id) {
		return phieuRenLuyenRepository.findById(id).map(phieurenluyen -> {
			phieuRenLuyenRepository.delete(phieurenluyen);
			return ResponseEntity.ok().build();
		});
	}
	
}

	