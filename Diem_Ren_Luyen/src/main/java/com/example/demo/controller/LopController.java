package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.dto.BoCauHoiDTO;

@Controller
@RequestMapping(value = "/quanlylop" )
public class LopController {
	@Autowired
	private LopService lopService;
	@RequestMapping(value = {"","/"} )
	public String index(Model model) {
		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");

		return "adminMaster";
	}

	@RequestMapping(value = { "/them" }, method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		BoCauHoiDTO bch = new BoCauHoiDTO();
		bch.setTenBoCauHoi(request.getParameter("tenbocauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		bch.setTinhTrang(tinhtrang);
		boCauHoiService.setData(bch);

		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = "/seach", method = RequestMethod.GET )
	public String index3(Model model, HttpServletRequest request) {
		String seachString;
		seachString = request.getParameter("seach").trim();
		
		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");
		if(seachString=="") return "adminMaster";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.seach(seachString);
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		BoCauHoiDTO bch = new BoCauHoiDTO();
		bch.setTenBoCauHoi(request.getParameter("tenbocauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		bch.setIdBoCauHoi((long)1);
		bch.setTinhTrang(tinhtrang);
		boCauHoiService.update(bch);

		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
}
