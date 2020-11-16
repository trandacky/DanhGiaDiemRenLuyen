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
import com.example.demo.service.BoCauHoiService;
@RestController
public class BoCauHoiController {
	private BoCauHoiService boCauHoiService;

	public BoCauHoiController(BoCauHoiService boCauHoiService) {
		super();
		this.boCauHoiService = boCauHoiService;
	}

	@GetMapping("/view/bocauhoi")
	public List<BoCauHoi> get() {
		return boCauHoiService.getAll();
	}

	@GetMapping("/view/bocauhoi/{id}")
	public Optional<BoCauHoi> getid(@PathVariable Long id) {
		return boCauHoiService.getByID(id);
	}

	@PostMapping("/create/bocauhoi")
	public BoCauHoi createBoCauHoi() {
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setTenBoCauhoi("Hoạt động xã hội");
		// khi có giao diện thì lấy dữ liệu qua đây
		return boCauHoiService.setData(boCauHoi);

	}

	@PutMapping("/update/bocauhoi/{id}")
	public Optional<BoCauHoi> updateBoCauHoi(@PathVariable Long id) {
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setTenBoCauhoi("Rèn luyện đoàn viên");
		return boCauHoiService.update(boCauHoi);

	}

	@DeleteMapping("/delete/bocauhoi/{id}")
	public Optional<Object> deleteBoCauHoi(@PathVariable long id) {

		return boCauHoiService.delete(id);
	}

}
