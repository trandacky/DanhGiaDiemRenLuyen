package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.repository.ChiTietPhieuRenLuyenRepository;
import com.example.demo.service.ChiTietPhieuRenLuyenService;
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.ChiTietPhieuRenLuyenDTO;

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
	
	@Override
	public void updateChiTietPhieuRenLuyenSetDiemlan3ForId(Integer diem3, Long id) {
		// TODO Auto-generated method stub
		chiTietPhieuRenLuyenRepository.updateChiTietPhieuRenLuyenSetDiemlan3ForId(diem3, id);
	}
	
	@Override
	public Optional<ChiTietPhieuRenLuyen> updateDiemLan3(Integer diem3, Long id) {
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.findById(id).map(chitietphieurenluyen -> {
			chitietphieurenluyen.setDiemLan3(diem3);
			return chiTietPhieuRenLuyenRepository.save(chitietphieurenluyen);
		});
	
	
		
	}
	
	@Override
	public Optional<ChiTietPhieuRenLuyen> updateDiemLan1Va2(Integer diem1, Long id){
		// TODO Auto-generated method stub
		return chiTietPhieuRenLuyenRepository.findById(id).map(chitietphieurenluyen -> {
			chitietphieurenluyen.setDiemLan1(diem1);
			return chiTietPhieuRenLuyenRepository.save(chitietphieurenluyen);
		});
	}

	@Override
	public Optional<ChiTietPhieuRenLuyen> updateDiemLan2(Integer diem2, Long id) {
		return chiTietPhieuRenLuyenRepository.findById(id).map(chitietphieurenluyen -> {
			chitietphieurenluyen.setDiemLan2(diem2);
			return chiTietPhieuRenLuyenRepository.save(chitietphieurenluyen);
		});
	}

}
