package com.example.demo.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.Lop;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.BoCauHoiRepository;
import com.example.demo.repository.LopRepository;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;
@Service
public class TaiKhoanImpl implements TaiKhoanService{
	@Autowired
	private LopRepository lopRepository;
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
	
	@Override
	public Optional<Object> updatelop(Long idTaiKhoan, Long idLop) {
		Lop lop = new Lop();
		lop.setIdLop(idLop);
		
		return TaiKhoanRepository.findById(idTaiKhoan).map(taikhoan -> {
			taikhoan.setIdLop(lop);
			return TaiKhoanRepository.save(taikhoan);
		});
	}
	
	@Override
	public List<TaiKhoan> getTaiKhoanByIDLop(long idLop) {
		Lop lop= new Lop();
		lop.setIdLop(idLop);
		return TaiKhoanRepository.findByIdLop(lop);
	}
	
	@Override
	public List<TaiKhoan> getTaiKhoanSinhVien() {
		// TODO Auto-generated method stub
		return TaiKhoanRepository.findByQuyen(4);
	}
	
	

	


}
