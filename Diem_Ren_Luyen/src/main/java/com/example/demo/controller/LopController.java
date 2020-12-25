package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Lop;
import com.example.demo.service.LopService;
import com.example.demo.service.dto.LopDTO;

@Controller
@RequestMapping(value = "/quanly/lop" )
public class LopController {
	@Autowired
	private LopService lopService;
	@RequestMapping(value = {"","/"} )
	public String index(Model model) {
		String page = "/WEB-INF/jsp/admin/lop.jsp";
		List<Lop> listLop = lopService.getAll();
		model.addAttribute("ListLop", listLop);
		model.addAttribute("page", page);
		model.addAttribute("activelop", "active");

		return "adminMaster";
	}

	@RequestMapping(value = { "/them" }, method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		LopDTO l = new LopDTO();
		l.setTenLop(request.getParameter("tenlop").trim());
		l.setKhoa(request.getParameter("khoa").trim());
		l.setKhoaHoc(Integer.parseInt(request.getParameter("khoahoc").trim()));	
		lopService.setData(l);

		String page = "/WEB-INF/jsp/admin/lop.jsp";
		List<Lop> listLop = lopService.getAll();
		model.addAttribute("ListLop", listLop);
		model.addAttribute("page", page);
		model.addAttribute("activelop", "active");
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = "/seach", method = RequestMethod.GET )
	public String index3(Model model, HttpServletRequest request) {
		String seachString;
		seachString = request.getParameter("seach").trim();
		
		String page = "/WEB-INF/jsp/admin/lop.jsp";
		
		model.addAttribute("page", page);
		model.addAttribute("activelop", "active");
		if(seachString=="") return "adminMaster";
		List<Lop> listLop = lopService.seach(seachString);
		model.addAttribute("ListLop", listLop);
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		LopDTO l = new LopDTO();
		l.setTenLop(request.getParameter("tenlop").trim());
		
		l.setId((long)1);
		
		lopService.update(l);

		String page = "/WEB-INF/jsp/admin/lop.jsp";
		List<Lop> listLop = lopService.getAll();
		model.addAttribute("ListLop", listLop);
		model.addAttribute("page", page);
		model.addAttribute("activelop", "active");
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
}
