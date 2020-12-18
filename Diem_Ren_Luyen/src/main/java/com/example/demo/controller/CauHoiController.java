package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.CauHoiService;
@Controller
public class CauHoiController {
	private final CauHoiService cauHoiService;
	public CauHoiController(CauHoiService cauHoiService) {
		super();
		this.cauHoiService=cauHoiService;
	}
	
	
	@RequestMapping(value = { "/quanlycauhoi" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/cauhoi.jsp";
		model.addAttribute("page",page);
		model.addAttribute("activecauhoi","active");
		
		return "adminMaster";
	}
}
