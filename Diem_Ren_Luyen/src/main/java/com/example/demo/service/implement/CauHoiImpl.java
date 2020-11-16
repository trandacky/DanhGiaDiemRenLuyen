package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CauHoi;
import com.example.demo.repository.CauHoiRepository;
import com.example.demo.service.CauHoiService;

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
	public CauHoi setData(CauHoi cauHoi) {

		return cauHoiRepository.save(cauHoi);
	}

	@Override
	public Optional<Object> update(CauHoi cauHoi) {

		return cauHoiRepository.findById(cauHoi.getIdCauHoi()).map(cauhoi -> {
			cauhoi = cauHoi;
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
