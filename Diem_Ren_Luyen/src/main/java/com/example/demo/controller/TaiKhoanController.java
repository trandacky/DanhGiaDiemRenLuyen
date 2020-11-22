package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.TaiKhoanService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/taikhoan")
public class TaiKhoanController {
	@Autowired
	private TaiKhoanService TaiKhoanService;

	public TaiKhoanController(TaiKhoanService TaiKhoanService) {
		super();
		this.TaiKhoanService = TaiKhoanService;
	}

	@GetMapping("/view")
	public List<TaiKhoan> get() {
		return TaiKhoanService.getAll();
	}

	@GetMapping("/view/{id}")
	public TaiKhoan getid(@PathVariable Long id) {
		return TaiKhoanService.getByID(id);
	}

	@PostMapping("/create")
	public TaiKhoan createTaiKhoan() {
		TaiKhoan taiKhoan = new TaiKhoan();
		taiKhoan.setMaSinhVien(405105);
		taiKhoan.setMatKhau("123");
		taiKhoan.setQuyen("ADMIN");
		taiKhoan.setTen("Trần Đắc Kỳ");
		taiKhoan.setTinhTrang(true);
		// khi có giao diện thì lấy dữ liệu qua đây
		return TaiKhoanService.setData(taiKhoan);

	}

	@PutMapping("/update/{id}")
	public Optional<Object> updateTaiKhoan(@PathVariable Long id) {
		TaiKhoan TaiKhoan = new TaiKhoan();
		
		return TaiKhoanService.update(TaiKhoan);

	}

	@DeleteMapping("/delete/{id}")
	public Optional<Object> deleteTaiKhoan(@PathVariable long id) {

		return TaiKhoanService.delete(id);
	}

}
