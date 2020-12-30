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
import com.example.demo.service.dto.TaiKhoanDTO;
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
//		TaiKhoan taiKhoan = new TaiKhoan(TaiKhoan.getm(),TaiKhoanDTO.getMatKhau(),TaiKhoanDTO.getQuyen(),TaiKhoanDTO.getTen(),TaiKhoanDTO.getNgayThangNamSinh(),TaiKhoanDTO.getIdLop());
		return TaiKhoanRepository.save(TaiKhoan);
		
	}

	@Override
	public Optional<TaiKhoan> update(TaiKhoanDTO TaiKhoanDTO) {

		TaiKhoan taiKhoan = new TaiKhoan(TaiKhoanDTO.getId(),TaiKhoanDTO.getMatKhau(),TaiKhoanDTO.getQuyen(), TaiKhoanDTO.getTen(),TaiKhoanDTO.getNgayThangNamSinh(),TaiKhoanDTO.getIdLop());
		return TaiKhoanRepository.findById(taiKhoan.getMaSinhVien()).map(taikhoan -> {
			taikhoan = taiKhoan;
			return TaiKhoanRepository.save(taikhoan);
		});
	}

	@Override
	public Optional<Object> delete(String id) {
		return TaiKhoanRepository.findById(id).map(TaiKhoan -> {
			TaiKhoanRepository.delete(TaiKhoan);
			return ResponseEntity.ok().build();
		});
	}

	@Override
	public Optional<TaiKhoan> getByID(String id) {
		// TODO Auto-generated method stub
		return TaiKhoanRepository.findById(id);
	}
	
	@Override
	public Optional<Object> updatelop(String idTaiKhoan, Long idLop) {
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


	@Override
	public List<TaiKhoan> search(String x) {
		x = "%"+ x +"%";
		List<TaiKhoan> list;
			list = TaiKhoanRepository.findByMaSinhVienOrTenLike( x,x);
		return list;
	}
	
	public static boolean isNumeric(String str) { 
		 for (char c : str.toCharArray())
		    {
		        if (!Character.isDigit(c)) return false;
		    }
		    return true; 
		}


	@Override
	public Optional<Object> update(TaiKhoan TaiKhoan) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	


}
