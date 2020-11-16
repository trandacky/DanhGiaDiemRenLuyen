package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.service.CauHoiService;

@RestController
public class CauHoiController {
	private CauHoiService cauHoiService;

	public CauHoiController(CauHoiService cauHoiService) {
		super();
		this.cauHoiService = cauHoiService;
	}

	@GetMapping("/view/cauhoi")
	public List<CauHoi> get() {
		return cauHoiService.getAll();
	}

	@GetMapping("/view/cauhoi/{id}")
	public CauHoi getid(@PathVariable Long id) {
		return cauHoiService.getByID(id);
	}

	@PostMapping("/create/cauhoi")
	public CauHoi createCauHoi() {
		CauHoi cauHoi = new CauHoi();
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setIdBoCauHoi(1);
		cauHoi.setBoCauHoi(boCauHoi);
		cauHoi.setTenBoCauhoi("Điểm gì đó");
		cauHoi.setDiemToiDa(10);
		// khi có giao diện thì lấy dữ liệu qua đây
		return cauHoiService.setData(cauHoi);

	}

	@PutMapping("/update/cauhoi/{id}")
	public Optional<Object> updateCauHoi(@PathVariable Long id) {
		CauHoi cauHoi = new CauHoi();
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setIdBoCauHoi(1);
		cauHoi.setIdCauHoi(id);
		cauHoi.setBoCauHoi(boCauHoi);
		cauHoi.setTenBoCauhoi("Điểm hệ 10");
		cauHoi.setDiemToiDa(10);
		return cauHoiService.update(cauHoi);

	}

	@DeleteMapping("/delete/cauhoi/{id}")
	public Optional<Object> deleteCauHoi(@PathVariable long id) {

		return cauHoiService.delete(id);
	}
}
