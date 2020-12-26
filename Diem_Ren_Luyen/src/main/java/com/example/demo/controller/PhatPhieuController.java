package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.CauHoiService;

@Controller
@RequestMapping(value = "/quanly/phatphieu" )
public class PhatPhieuController {
	@Autowired
	private CauHoiService cauHoiService;
	@Autowired
	private BoCauHoiService boCauHoiService;
	
	@RequestMapping(value = {"","/"} )
	public String index(Model model) {
		String page = "/WEB-INF/jsp/admin/phatphieu.jsp";
		List<BoCauHoi> listBoCauHoi= boCauHoiService.getBoCauHoiTrue();
		
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activephatphieu", "active");

		return "adminMaster";
	}
	@RequestMapping(value = "/send" , method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
	
}
