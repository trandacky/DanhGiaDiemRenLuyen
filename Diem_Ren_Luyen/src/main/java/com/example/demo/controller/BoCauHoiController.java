package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.example.demo.service.dto.TaiKhoanDTO;

@Controller
@RequestMapping(value = "/quanly/bocauhoi" )
public class BoCauHoiController {
	@Autowired
	private BoCauHoiService boCauHoiService;
	@Autowired
	private CauHoiService cauHoiService;
	
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
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		BoCauHoiDTO bch = new BoCauHoiDTO();
		bch.setIdBoCauHoi(Long.parseLong(request.getParameter("idbocauhoi").trim()));
		bch.setTenBoCauHoi(request.getParameter("tenbocauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		bch.setTinhTrang(tinhtrang);
		boCauHoiService.update(bch);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/doiquyen" }, method = RequestMethod.POST)
	public String index5(Model model, HttpServletRequest request) {
		BoCauHoiDTO bch = new BoCauHoiDTO();
		bch.setIdBoCauHoi(Long.parseLong(request.getParameter("idbocauhoi").trim()));
		bch.setTenBoCauHoi(request.getParameter("tenbocauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		bch.setTinhTrang(!tinhtrang);
		boCauHoiService.update(bch);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/capnhat/{id}" }, method = RequestMethod.GET)
	public String index6(Model model, HttpServletRequest request, @PathVariable Long id) {
//		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		//model.addAttribute("ListBoCauHoi", listBoCauHoi);
		Optional<BoCauHoi> boCauHoi = boCauHoiService.getByID(id);
		List<CauHoi> listCauHoi = cauHoiService.getCauHoiTrue();
		List<CauHoi> ListCauHoi = cauHoiService.getCauHoiByIDBoCauHoi(id);
		String page = "/WEB-INF/jsp/admin/updatebocauhoi.jsp";
		model.addAttribute("page", page);
		//combobox
		model.addAttribute("listCauHoi", listCauHoi);
		//datatable
		model.addAttribute("ListCauHoi", ListCauHoi);
		model.addAttribute("activebocauhoi", "active");
		model.addAttribute("bocauhoi", boCauHoi);
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat/up" }, method = RequestMethod.POST)
	public String index8(Model model, HttpServletRequest request) {
	
		String back = request.getHeader("Referer");
			Long idBoCauHoi= Long.parseLong(request.getParameter("idbocauhoi"));
			Long idCauHoi = Long.parseLong(request.getParameter("layidcauhoi"));
		cauHoiService.updatebocauhoi(idCauHoi, idBoCauHoi);
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/quanly/danhgialan3" }, method = RequestMethod.POST)
	public String index7(Model model, HttpServletRequest request) {
		BoCauHoiDTO bch = new BoCauHoiDTO();
		bch.setTenBoCauHoi(request.getParameter("tenbocauhoi").trim());
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		bch.setIdBoCauHoi((long)1);
		bch.setTinhTrang(tinhtrang);
		boCauHoiService.update(bch);

		String page = "/WEB-INF/jsp/admin/quanlydanhgialan3.jsp";
		List<BoCauHoi> listBoCauHoi = boCauHoiService.getAll();
		model.addAttribute("ListPhieuRenLuyen", listBoCauHoi);
		model.addAttribute("page", page);
		model.addAttribute("activequanlydanhgialan3", "active");
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
}
