package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TaiKhoan;

import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;
@Service
public class TaiKhoanImpl implements TaiKhoanService{
	private final TaiKhoanRepository TaiKhoanRepository;

	public TaiKhoanImpl(TaiKhoanRepository TaiKhoanRepository) {
		super();
		this.TaiKhoanRepository = TaiKhoanRepository;
	}

	@Override
	public List<TaiKhoan> getAll() {

		return TaiKhoanRepository.findAll();
	}

	@Override
	public TaiKhoan setData(TaiKhoan TaiKhoan) {

		return TaiKhoanRepository.save(TaiKhoan);
	}

	@Override
	public Optional<Object> update(TaiKhoan TaiKhoan) {

		return TaiKhoanRepository.findById(TaiKhoan.getMaSinhVien()).map(taiKhoan -> {
			taiKhoan = TaiKhoan;
			return TaiKhoanRepository.save(taiKhoan);
		});
	}

	@Override
	public Optional<Object> delete(Long id) {
		return TaiKhoanRepository.findById(id).map(TaiKhoan -> {
			TaiKhoanRepository.delete(TaiKhoan);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public TaiKhoan getByID(long id) {
		// TODO Auto-generated method stub
		return TaiKhoanRepository.getOne(id);
	}

	


}
