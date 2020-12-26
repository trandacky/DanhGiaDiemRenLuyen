package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("list2",boCauHoiService.getBoCauHoiTrue());
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
	@RequestMapping(value = "/seach", method = RequestMethod.GET )
	public String index3(Model model, HttpServletRequest request) {
		String seachString;
		seachString = request.getParameter("seach").trim();
		
		String page = "/WEB-INF/jsp/admin/cauhoi.jsp";
		
		model.addAttribute("page", page);
		model.addAttribute("activecauhoi", "active");
		if(seachString=="") return "adminMaster";
		List<CauHoi> listCauHoi = cauHoiService.seach(seachString);
		model.addAttribute("ListCauHoi", listCauHoi);
		return "adminMaster";
	}
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		CauHoiDTO ch = new CauHoiDTO();
		boolean tinhtrang = false;
		String back = request.getHeader("Referer");
		long idbocauhoi = 0;
		ch.setIdCauHoi(Long.parseLong(request.getParameter("idcauhoi").trim()));
		if(request.getParameter("idbocauhoi")!="") idbocauhoi= Long.parseLong(request.getParameter("idbocauhoi"));
		ch.setIdBoCauHoi(idbocauhoi);
		ch.setNoiDungCauHoi(request.getParameter("noidungcauhoi").trim());
		
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		ch.setTinhTrang(tinhtrang);
		cauHoiService.update(ch);
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/doiquyen" }, method = RequestMethod.POST)
	public String index5(Model model, HttpServletRequest request) {
		CauHoiDTO ch = new CauHoiDTO();
		boolean tinhtrang = false;
		String back = request.getHeader("Referer");
		long idbocauhoi = 0;
		ch.setIdCauHoi(Long.parseLong(request.getParameter("idcauhoi").trim()));
		ch.setDiemToiDa(Integer.parseInt(request.getParameter("diemtoida").trim()));
		if(request.getParameter("idbocauhoi")!="") idbocauhoi= Long.parseLong(request.getParameter("idbocauhoi"));
		ch.setIdBoCauHoi(idbocauhoi);
		ch.setNoiDungCauHoi(request.getParameter("noidungcauhoi").trim());
		
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		ch.setTinhTrang(!tinhtrang);
		cauHoiService.update(ch);
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/capnhat/{id}" }, method = RequestMethod.GET)
	public String index6(Model model, HttpServletRequest request, @PathVariable Long id)
	{
		CauHoi cauHoi = cauHoiService.getByID(id);
		String page = "/WEB-INF/jsp/admin/updatecauhoi.jsp";
		model.addAttribute("page", page);
		//combobox
		model.addAttribute("cauhoi", cauHoi);
		//datatable
		model.addAttribute("activecauhoi", "active");
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat/up" }, method = RequestMethod.POST)
	public String index7(Model model, HttpServletRequest request)
	{
		long id = Long.parseLong(request.getParameter("idcauhoi").trim());
		String noidung = (request.getParameter("noidungcauhoi").trim());
		int diemtoida = Integer.parseInt(request.getParameter("diemtoida").trim());
		
		cauHoiService.updatenoidung(id,noidung,diemtoida);
		return "redirect:/quanly/cauhoi";
	}
}
