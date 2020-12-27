package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.repository.BoCauHoiRepository;
import com.example.demo.repository.CauHoiRepository;
import com.example.demo.service.CauHoiService;
import com.example.demo.service.dto.CauHoiDTO;

@Service
public class CauHoiImpl implements CauHoiService {
	@Autowired
	private BoCauHoiRepository boCauHoiRepository;
	private final CauHoiRepository cauHoiRepository;

	public CauHoiImpl(CauHoiRepository cauHoiRepository) {
		super();
		this.cauHoiRepository = cauHoiRepository;
	}

	@Override
	public List<CauHoi> getAll() {

		return cauHoiRepository.findAllByOrderByIdCauHoiAsc();
	}
	@Override
	public List<CauHoi> getCauHoiByIDBoCauHoi(long idBoCauHoi) {
		BoCauHoi bocauhoi= new BoCauHoi();
		bocauhoi.setIdBoCauHoi(idBoCauHoi);
		return cauHoiRepository.findByIdBoCauHoi(bocauhoi);
	}
	@Override
	public List<CauHoi> getCauHoiTrue() {
		// TODO Auto-generated method stub
		return cauHoiRepository.findByTinhTrangTrue();
	}

	@Override
	public CauHoi setData(CauHoiDTO cauHoi) {
		CauHoi cauhoi;
		if(cauHoi.getIdBoCauHoi()!=0)
		{
		BoCauHoi bocauhoi = new BoCauHoi();
		bocauhoi.setIdBoCauHoi(cauHoi.getIdBoCauHoi());
		
		cauhoi = new CauHoi(cauHoi.getIdCauHoi(),cauHoi.getNoiDungCauHoi(),cauHoi.getDiemToiDa(),cauHoi.getTinhTrang(),bocauhoi);
		}
		else
			cauhoi = new CauHoi(cauHoi.getIdCauHoi(),cauHoi.getNoiDungCauHoi(),cauHoi.getDiemToiDa(),cauHoi.getTinhTrang(),null);
		return cauHoiRepository.save(cauhoi);
	}

	@Override
	public Optional<Object> update(CauHoiDTO cauHoi) {
		CauHoi cauhoi;
		if(cauHoi.getIdBoCauHoi()!=0)
		{
		BoCauHoi bocauhoi = new BoCauHoi();
		bocauhoi.setIdBoCauHoi(cauHoi.getIdBoCauHoi());
		
		cauhoi = new CauHoi(cauHoi.getIdCauHoi(),cauHoi.getNoiDungCauHoi(),cauHoi.getDiemToiDa(),cauHoi.getTinhTrang(),bocauhoi);
		}
		else
			cauhoi = new CauHoi(cauHoi.getIdCauHoi(),cauHoi.getNoiDungCauHoi(),cauHoi.getDiemToiDa(),cauHoi.getTinhTrang(),null);
		return cauHoiRepository.findById(cauhoi.getIdCauHoi()).map(cauhoi2 -> {
			cauhoi2 = cauhoi;
			return cauHoiRepository.save(cauhoi2);
		});
	}
	@Override
	public Optional<Object> updatebocauhoi(Long idCauHoi, Long idBoCauHoi) {
		BoCauHoi bocauhoi = new BoCauHoi();
		bocauhoi.setIdBoCauHoi(idBoCauHoi);
		
		return cauHoiRepository.findById(idCauHoi).map(cauhoi -> {
			cauhoi.setIdBoCauHoi(bocauhoi);
			cauhoi.setTinhTrang(false);
			return cauHoiRepository.save(cauhoi);
		});
	}

	@Override
	public Optional<Object> delete(Long id) {
		return cauHoiRepository.findById(id).map(cauhoi -> {
			cauHoiRepository.delete(cauhoi);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public CauHoi getByID(long id) {
		// TODO Auto-generated method stub
		return cauHoiRepository.getOne(id);
	}
	@Override
	public Optional<CauHoi> updatenoidung(long id, String noidung, int diemtoida) {
		return cauHoiRepository.findById(id).map(cauhoi -> {
			cauhoi.setNoiDungCauHoi(noidung);
			cauhoi.setDiemToiDa(diemtoida);
			return cauHoiRepository.save(cauhoi);
		});
		
	}

	@Override
	public List<CauHoi> seach(String seachString) {
		// TODO Auto-generated method stub
		
		long seachString2=-17777777;
		if(isNumeric( seachString)) seachString2= Long.parseLong(seachString); 
		seachString = "%"+seachString+"%";
		List<CauHoi> list;
		try {
			list = cauHoiRepository.findByIdCauHoiOrNoiDungCauHoiLike(seachString2, seachString);
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
