package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.CauHoiDTO;
@Controller
@RequestMapping(value = "/quanly/cauhoi" )
public class CauHoiController {
	@Autowired
	private CauHoiService cauHoiService;
	@Autowired
	private BoCauHoiService boCauHoiService;
	
	@RequestMapping(value = { "/","" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/cauhoi.jsp";
		List<CauHoi> listCauHoi = cauHoiService.getAll();
		model.addAttribute("list2",boCauHoiService.getAll());
		model.addAttribute("ListCauHoi", listCauHoi);
		model.addAttribute("page",page);
		model.addAttribute("activecauhoi","active");
		return "adminMaster";
	}
	@RequestMapping(value = { "/them" }, method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		CauHoiDTO ch = new CauHoiDTO();
		String back = request.getHeader("Referer");
		boolean tinhtrang = false;
		int diemtoida= 0;
		long idbocauhoi = 0;
		ch.setNoiDungCauHoi(request.getParameter("noidungcauhoi").trim());
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		
		if(request.getParameter("diemtoida")!="") diemtoida=Integer.parseInt(request.getParameter("diemtoida"));
		ch.setDiemToiDa(diemtoida);
		ch.setTinhTrang(tinhtrang);
		if(request.getParameter("idbocauhoi")!="") idbocauhoi= Long.parseLong(request.getParameter("idbocauhoi"));
		ch.setIdBoCauHoi(idbocauhoi);
		cauHoiService.setData(ch);
		
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		CauHoiDTO ch = new CauHoiDTO();
		ch.setIdCauHoi(Long.parseLong(request.getParameter("idcauhoi").trim()));
		ch.setNoiDungCauHoi(request.getParameter("noidungcauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		ch.setTinhTrang(tinhtrang);
		cauHoiService.update(ch);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/doiquyen" }, method = RequestMethod.POST)
	public String index5(Model model, HttpServletRequest request) {
		CauHoiDTO ch = new CauHoiDTO();
		ch.setIdCauHoi(Long.parseLong(request.getParameter("idcauhoi").trim()));
		ch.setNoiDungCauHoi(request.getParameter("noidungcauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		ch.setTinhTrang(!tinhtrang);
		cauHoiService.update(ch);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
}
