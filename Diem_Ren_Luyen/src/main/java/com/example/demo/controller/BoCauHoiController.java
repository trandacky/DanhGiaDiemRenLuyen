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

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.BoCauHoiService;
@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/bocauhoi")
public class BoCauHoiController {
	@Autowired
	private BoCauHoiService boCauHoiService;

	public BoCauHoiController(BoCauHoiService boCauHoiService) {
		super();
		this.boCauHoiService = boCauHoiService;
	}

	@GetMapping("/view")
	public List<BoCauHoi> get() {
		return boCauHoiService.getAll();
	}

	@GetMapping("/view/{id}")
	public Optional<BoCauHoi> getid(@PathVariable Long id) {
		return boCauHoiService.getByID(id);
	}

	@PostMapping("/create")
	public BoCauHoi createBoCauHoi() {
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setTenBoCauhoi("Hoạt động xã hội");
		// khi có giao diện thì lấy dữ liệu qua đây
		return boCauHoiService.setData(boCauHoi);

	}

	@PutMapping("/update/{id}")
	public Optional<BoCauHoi> updateBoCauHoi(@PathVariable Long id) {
		BoCauHoi boCauHoi = new BoCauHoi();
		boCauHoi.setIdBoCauHoi(id);
		boCauHoi.setTenBoCauhoi("Rèn luyện đoàn viên");
		return boCauHoiService.update(boCauHoi);

	}

	@DeleteMapping("/delete/{id}")
	public Optional<Object> deleteBoCauHoi(@PathVariable long id) {

		return boCauHoiService.delete(id);
	}

}
