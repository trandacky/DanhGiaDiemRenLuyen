package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.repository.BoCauHoiRepository;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.dto.BoCauHoiDTO;

@Service
public class BoCauHoiImpl implements BoCauHoiService {
	private final BoCauHoiRepository boCauHoiRepository;

	public BoCauHoiImpl(BoCauHoiRepository boCauHoiRepository) {
		super();
		this.boCauHoiRepository = boCauHoiRepository;
	}

	@Override
	public List<BoCauHoi> getAll() {
		// TODO Auto-generated method stub
		return boCauHoiRepository.findAll();
	}

	@Override
	public BoCauHoi setData(BoCauHoiDTO boCauHoiDTO) {
		// TODO Auto-generated method stub
		BoCauHoi boCauHoi = new BoCauHoi(boCauHoiDTO.getIdBoCauHoi(),boCauHoiDTO.getTenBoCauHoi(),boCauHoiDTO.getTinhTrang());
		return boCauHoiRepository.save(boCauHoi);
	}

	@Override
	public Optional<BoCauHoi> update(BoCauHoiDTO boCauHoiDTO) {
		// TODO Auto-generated method stub
		BoCauHoi boCauHoi = new BoCauHoi(boCauHoiDTO.getIdBoCauHoi(),boCauHoiDTO.getTenBoCauHoi(),boCauHoiDTO.getTinhTrang());
		return boCauHoiRepository.findById(boCauHoi.getIdBoCauHoi()).map(bocauhoi -> {
			bocauhoi = boCauHoi;
			return boCauHoiRepository.save(bocauhoi);
		});
	}

	@Override
	public Optional<Object> delete(Long id) {
		return boCauHoiRepository.findById(id).map(bocauhoi -> {
			boCauHoiRepository.delete(bocauhoi);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public Optional<BoCauHoi> getByID(long id) {
		// TODO Auto-generated method stub
		return boCauHoiRepository.findById(id); 
	}

	@Override
	public List<BoCauHoi> seach(String seachString) {
		// TODO Auto-generated method stub
		
		long seachString2=-17777777;
		if(isNumeric( seachString)) seachString2= Long.parseLong(seachString); 
		seachString = "%"+seachString+"%";
		List<BoCauHoi> list;
		try {
			list = boCauHoiRepository.findByIdBoCauHoiOrTenBoCauHoiLike(seachString2, seachString);
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
