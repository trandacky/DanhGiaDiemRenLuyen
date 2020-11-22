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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.service.CauHoiService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cauhoi")
public class CauHoiController {
	@Autowired
	private CauHoiService cauHoiService;

	public CauHoiController(CauHoiService cauHoiService) {
		super();
		this.cauHoiService = cauHoiService;
	}

	@GetMapping("/view")
	public List<CauHoi> get() {
		return cauHoiService.getAll();
	}

	@GetMapping("/view/{id}")
	public CauHoi getid(@PathVariable Long id) {
		return cauHoiService.getByID(id);
	}
	
	@PostMapping("/create")
	public CauHoi createCauHoi(@RequestBody CauHoi cauHoi) {
		
		// khi có giao diện thì lấy dữ liệu qua đây
		return cauHoiService.setData(cauHoi);

	}

	@PutMapping("/update")
	public Optional<Object> updateCauHoi(@RequestBody CauHoi cauHoi) {
		
		return cauHoiService.update(cauHoi);

	}

	@DeleteMapping("/delete/{id}")
	public Optional<Object> deleteCauHoi(@PathVariable long id) {

		return cauHoiService.delete(id);
	}
}
