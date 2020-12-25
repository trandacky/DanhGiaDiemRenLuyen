package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.Lop;
import com.example.demo.repository.LopRepository;
import com.example.demo.service.LopService;
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.LopDTO;


@Service
public class LopImpl implements LopService {
	private final LopRepository lopRepository;

	public LopImpl(LopRepository lopRepository) {
		super();
		this.lopRepository = lopRepository;
	}

	@Override
	public List<Lop> getAll() {
		// TODO Auto-generated method stub
		return lopRepository.findAll();
	}

	@Override
	public Lop setData(LopDTO lopDTO) {
		// TODO Auto-generated method stub
		Lop lop = new Lop(lopDTO.getId(),lopDTO.getTenLop(),lopDTO.getKhoa(),lopDTO.getKhoaHoc());
		return lopRepository.save(lop);
	}

	@Override
	public Optional<Lop> update(LopDTO lopDTO) {
		// TODO Auto-generated method stub
		Lop lop = new Lop(lopDTO.getId(),lopDTO.getTenLop(),lopDTO.getKhoa(),lopDTO.getKhoaHoc());
		return lopRepository.findById(lopDTO.getId()).map(lop2 -> {
			lop2 = lop;
			return lopRepository.save(lop2);
		});
	}
	
	@Override
	public Optional<Object> delete(Long id) {
		return lopRepository.findById(id).map(lop2 -> {
			lopRepository.delete(lop2);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public Optional<Lop> getByID(long id) {
		// TODO Auto-generated method stub
		return lopRepository.findById(id);
	}
	
	@Override
	public List<Lop> seach(String seachString) {
		// TODO Auto-generated method stub
		
		long seachString2=-17777777;
		if(isNumeric( seachString)) seachString2= Long.parseLong(seachString); 
		seachString = "%"+seachString+"%";
		List<Lop> list;
		try {
			list = lopRepository.findByIdLopOrTenLopLike(seachString2, seachString);
		} catch (Exception e) {
			list = null;
		}
		return list;
	}
	
	public static boolean isNumeric(String str) { 
		 for (char c : str.toCharArray())
		    {
		        if (!Character.isDigit(c)) return false;
		    }
		    return true; 
		}

}
