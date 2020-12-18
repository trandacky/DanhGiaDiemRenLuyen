package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.BoCauHoi;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.dto.BoCauHoiDTO;

@Controller
public class BoCauHoiController {
	public final BoCauHoiService boCauHoiService;

	public BoCauHoiController(BoCauHoiService boCauHoiService) {
		super();
		this.boCauHoiService = boCauHoiService;
	}

	@RequestMapping(value = { "/quanlybocauhoi" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");

		return "adminMaster";
	}

	@RequestMapping(value = { "/quanlybocauhoi.them" }, method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		BoCauHoi bch = new BoCauHoi();
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

		return "redirect:" + "quanlybocauhoi";
	}
	@RequestMapping(value = { "/seachbocauhoi" }, method = RequestMethod.GET)
	public String index3(Model model, HttpServletRequest request) {
		String seachString;
		seachString = request.getParameter("seach").trim();
		
		String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.seach(seachString);
		model.addAttribute("ListBoCauHoi", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activebocauhoi", "active");

		String back = request.getHeader("Referer");
		return "adminMaster";
	}
}
