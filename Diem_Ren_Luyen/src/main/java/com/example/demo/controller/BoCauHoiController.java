package com.example.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.BoCauHoiService;
@Controller
public class BoCauHoiController {
	public final BoCauHoiService boCauHoiService;
	public BoCauHoiController(BoCauHoiService boCauHoiService) {
		super();
		this.boCauHoiService=boCauHoiService;
	}
	
	@RequestMapping(value = { "/quanlybocauhoi" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page",page);
		model.addAttribute("activebocauhoi","active");
		
		return "adminMaster";
	}
	
}
