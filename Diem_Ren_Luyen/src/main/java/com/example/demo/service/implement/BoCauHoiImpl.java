package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.repository.BoCauHoiRepository;
import com.example.demo.service.BoCauHoiService;

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
	public BoCauHoi setData(BoCauHoi boCauHoi) {
		// TODO Auto-generated method stub
		return boCauHoiRepository.save(boCauHoi);
	}

	@Override
	public Optional<BoCauHoi> update(BoCauHoi boCauHoi) {
		// TODO Auto-generated method stub
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

}
