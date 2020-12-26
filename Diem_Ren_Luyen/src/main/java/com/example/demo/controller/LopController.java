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
import com.example.demo.entity.Lop;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.CauHoiService;
import com.example.demo.service.LopService;
import com.example.demo.service.TaiKhoanService;
import com.example.demo.service.dto.BoCauHoiDTO;
import com.example.demo.service.dto.LopDTO;

@Controller
@RequestMapping(value = "/quanly/lop" )
public class LopController {
	@Autowired
	private LopService lopService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	
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
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		l.setTinhTrang(tinhtrang);
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
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		LopDTO l = new LopDTO();
		l.setId(Long.parseLong(request.getParameter("idlop").trim()));
		l.setTenLop(request.getParameter("tenlop").trim());
		l.setKhoa(request.getParameter("khoa").trim());
		l.setKhoaHoc(Integer.parseInt(request.getParameter("khoahoc").trim()));
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		l.setTinhTrang(tinhtrang);
		lopService.update(l);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/doiquyen" }, method = RequestMethod.POST)
	public String index5(Model model, HttpServletRequest request) {
		LopDTO l = new LopDTO();
		l.setId(Long.parseLong(request.getParameter("idlop").trim()));
		l.setTenLop(request.getParameter("tenlop").trim());
		l.setKhoa(request.getParameter("khoa").trim());
		l.setKhoaHoc(Integer.parseInt(request.getParameter("khoahoc").trim()));
		boolean tinhtrang = false;
		tinhtrang = Boolean.parseBoolean(request.getParameter("tinhtrang"));
		l.setTinhTrang(!tinhtrang);
		lopService.update(l);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/capnhat/{id}" }, method = RequestMethod.GET)
	public String index6(Model model, HttpServletRequest request, @PathVariable Long id) {

		Optional<Lop> lop = lopService.getByID(id);
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanTrue();
		List<TaiKhoan> ListTaiKhoan = taiKhoanService.getTaiKhoanByIDLop(id);
		String page = "/WEB-INF/jsp/admin/updatelop.jsp";
		model.addAttribute("page", page);
		//combobox
		model.addAttribute("listTaiKhoan", listTaiKhoan);
		//datatable
		model.addAttribute("ListTaiKhoan", ListTaiKhoan);
		model.addAttribute("activelop", "active");
		model.addAttribute("lop", lop);
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat/up" }, method = RequestMethod.POST)
	public String index8(Model model, HttpServletRequest request) {
	
		String back = request.getHeader("Referer");
			Long idLop= Long.parseLong(request.getParameter("idlop"));
			Long idTaiKhoan = Long.parseLong(request.getParameter("layidtaikhoan"));
		taiKhoanService.updatelop(idTaiKhoan, idLop);
		return "redirect:"+back;
	}
	
}
