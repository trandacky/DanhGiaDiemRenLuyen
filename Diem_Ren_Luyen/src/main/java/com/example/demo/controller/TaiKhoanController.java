package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.Lop;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.CauHoiService;
import com.example.demo.service.LopService;
import com.example.demo.service.TaiKhoanService;
import com.example.demo.service.dto.TaiKhoanDTO;
@Controller
@RequestMapping(value = "/quanly/taikhoan")
public class TaiKhoanController {
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private LopService lopService;

	public TaiKhoan getTaiKhoanDangNhap()
	{
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
		    username = principal.toString();
		}
		TaiKhoan taiKhoan=new TaiKhoan();
		taiKhoan = taiKhoanService.getByID(username).get();
		return taiKhoan;
	}
	@RequestMapping(value = { "","/" })
	public String index(Model model, HttpServletRequest request) {
		request.getSession().setAttribute("tenadmin",getTaiKhoanDangNhap());
		String page="/WEB-INF/jsp/admin/taikhoan.jsp";
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getAll();
		model.addAttribute("ListTaiKhoan", listTaiKhoan);		
		List<Lop> listLop = lopService.getLopTrue();
		model.addAttribute("listLop", listLop);
		model.addAttribute("page",page);
		model.addAttribute("activequanlytaikhoan", "active");
		return "adminMaster";
						}
	@RequestMapping(value = { "/them" }, method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		String maSV = request.getParameter("maSinhVien").trim();
		String sql = "SELECT ma_sinh_vien FROM tai_khoan";
		String error = "Mã đã tồn tại";
		TaiKhoan taiKhoan = new TaiKhoan();
		Lop lop = new Lop();
		if( request.getParameter("idlop")!="")
		{
		lop.setIdLop(Long.parseLong(request.getParameter("idlop")));
		taiKhoan.setIdLop(lop);
		}
		taiKhoan.setMaSinhVien(request.getParameter("maSinhVien").trim());
		taiKhoan.setMatKhau(request.getParameter("matKhau").trim());
		taiKhoan.setNgayThangNamSinh(LocalDate.parse(request.getParameter("ngaySinh").trim()));
		taiKhoan.setQuyen(Integer.parseInt(request.getParameter("quyen").trim()));
		taiKhoan.setTen(request.getParameter("tenTaiKhoan"));
		if((maSV.equals(sql)))
		{
			return "" +error;
		}
		taiKhoanService.setData(taiKhoan);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET )
	public String index3(Model model, HttpServletRequest request) {
		String x;
		x = request.getParameter("search").trim();
		
		String page = "/WEB-INF/jsp/admin/taikhoan.jsp";
		
		model.addAttribute("page", page);
		model.addAttribute("activequanlytaikhoan", "active");
		if(x=="") return "adminMaster";		
		List<TaiKhoan> listTaiKhoan = taiKhoanService.search(x);
		model.addAttribute("ListTaiKhoan", listTaiKhoan);
		return "adminMaster";
	}
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String index4(Model model, HttpServletRequest request) {
		TaiKhoan taiKhoan = new TaiKhoan();
		Lop lop = new Lop();
		if( request.getParameter("idlop")!="")
		{
		lop.setIdLop(Long.parseLong(request.getParameter("idlop")));
		taiKhoan.setIdLop(lop);
		}
		taiKhoan.setMaSinhVien(request.getParameter("maSinhVien"));
		taiKhoan.setMatKhau(request.getParameter("matKhau").trim());
		taiKhoan.setNgayThangNamSinh(LocalDate.parse(request.getParameter("ngaySinh").trim()));
		taiKhoan.setQuyen(Integer.parseInt(request.getParameter("quyen").trim()));
		taiKhoan.setTen(request.getParameter("tenTaiKhoan"));
		System.out.print(taiKhoan.getMaSinhVien());
		taiKhoanService.updateTaiKhoan(taiKhoan);
//		String back = request.getHeader("Referer");
		return "redirect:/quanly/taikhoan";
	}
	@RequestMapping(value = { "/doiquyen" }, method = RequestMethod.POST)
	public String index5(Model model, HttpServletRequest request) {
		TaiKhoan taiKhoan = new TaiKhoan();
		Lop lop = new Lop();
		lop.setIdLop(Long.parseLong(request.getParameter("idlop")));
		taiKhoan.setMaSinhVien(request.getParameter("maSinhVien").trim());
		taiKhoan.setMatKhau(request.getParameter("matKhau").trim());
		taiKhoan.setNgayThangNamSinh(LocalDate.parse(request.getParameter("ngaySinh").trim()));
		taiKhoan.setQuyen(Integer.parseInt(request.getParameter("quyen").trim()));
		taiKhoan.setTen(request.getParameter("tenTaiKhoan"));
//		taiKhoan.setIdLop(lop);
		taiKhoanService.update(taiKhoan);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/capnhat/{id}" }, method = RequestMethod.GET)
	public String index6(Model model, HttpServletRequest request, @PathVariable String id) {

		Optional<TaiKhoan> taiKhoan = taiKhoanService.getByID(id);
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanSinhVien();
		List<Lop> listLop= lopService.getAll();
//		List<TaiKhoan> ListTaiKhoan = taiKhoanService.getTaiKhoanByIDLop(id);
		String page = "/WEB-INF/jsp/admin/updatetaikhoan.jsp";
		model.addAttribute("page", page);
		//combobox
//		model.addAttribute("listTaiKhoan", listTaiKhoan);
		//datatable
//		model.addAttribute("ListTaiKhoan", listTaiKhoan);
		model.addAttribute("listLop",listLop);
		model.addAttribute("activequanlytaikhoan", "active");
		model.addAttribute("taiKhoan", taiKhoan);
		return "adminMaster";
	}
	@RequestMapping(value = { "/capnhat/up" }, method = RequestMethod.POST)
	public String index8(Model model, HttpServletRequest request) {
	
		String back = request.getHeader("Referer");
			Long id = (Long.parseLong(request.getParameter("maSinhVien")));
//		Long id = Long.parseLong(request.getParameter("layidtaikhoan"));
//			taiKhoanService.update(id, idLop);
			return "redirect:"+back;
	}
}
