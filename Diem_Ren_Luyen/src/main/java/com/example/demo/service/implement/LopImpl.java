package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Lop;
import com.example.demo.repository.LopRepository;
import com.example.demo.service.LopService;

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
	public Lop setData(Lop lop) {
		// TODO Auto-generated method stub
		return lopRepository.save(lop);
	}

	@Override
	public Optional<Lop> update(Lop lop) {
		// TODO Auto-generated method stub
		return lopRepository.findById(lop.getIdLop()).map(lop2 -> {
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

}
