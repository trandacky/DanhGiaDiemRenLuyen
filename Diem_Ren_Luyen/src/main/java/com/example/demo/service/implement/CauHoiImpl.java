package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.repository.CauHoiRepository;
import com.example.demo.service.CauHoiService;
import com.example.demo.service.dto.CauHoiDTO;

@Service
public class CauHoiImpl implements CauHoiService {
	private final CauHoiRepository cauHoiRepository;

	public CauHoiImpl(CauHoiRepository cauHoiRepository) {
		super();
		this.cauHoiRepository = cauHoiRepository;
	}

	@Override
	public List<CauHoi> getAll() {

		return cauHoiRepository.findAll();
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
		BoCauHoi bocauhoi = new BoCauHoi();
		bocauhoi.setIdBoCauHoi(cauHoi.getIdBoCauHoi());
		CauHoi cauhoi = new CauHoi(cauHoi.getIdCauHoi(),cauHoi.getNoiDungCauHoi(),cauHoi.getDiemToiDa(),cauHoi.getTinhTrang(),bocauhoi);
		return cauHoiRepository.findById(cauHoi.getIdCauHoi()).map(cauhoi2 -> {
			cauhoi2 = cauhoi;
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

	

}
