package com.example.demo.controller.SinhVien;

import java.time.Year;
import java.util.ArrayList;
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

import com.example.demo.entity.BoCauHoi;
import com.example.demo.entity.CauHoi;
import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.entity.Lop;
import com.example.demo.entity.PhieuRenLuyen;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.ChiTietPhieuRenLuyenService;
import com.example.demo.service.LopService;
import com.example.demo.service.PhieuRenLuyenService;
import com.example.demo.service.TaiKhoanService;
import com.example.demo.service.dto.ChiTietPhieuRenLuyenDTO;
import com.example.demo.service.dto.LopDTO;

@Controller
@RequestMapping(value = "/sv/danhgiarenluyen")
public class SVController {
	@Autowired
	private BoCauHoiService boCauHoiService;
	@Autowired
	private LopService lopService;
	@Autowired
	private TaiKhoanService taiKhoanService;
	@Autowired
	private ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService;
	@Autowired
	private PhieuRenLuyenService phieuRenLuyenService;

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
	
	@RequestMapping(value = { "", "/" })
	public String index99(Model model, HttpServletRequest request) {

		String page = "/WEB-INF/jsp/SV/chitietphieu.jsp";
		  List<PhieuRenLuyen> phieuRenLuyen = phieuRenLuyenService.getPhieuRenLuyen(getTaiKhoanDangNhap().getMaSinhVien().toString());
		  PhieuRenLuyen phieuRenLuyen2 = phieuRenLuyen.get(0);
		  
		  model.addAttribute("taikhoan", getTaiKhoanDangNhap());
		  
		  List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen= phieuRenLuyen2.getChiTietPhieuRenLuyens();
		  request.getSession().setAttribute("listChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		  request.getSession().setAttribute("tensinhvien",getTaiKhoanDangNhap().getTen());
		  model.addAttribute("page", page);
		  //model.addAttribute("activeduyetlan3", "active");
		return "SVMaster";
	}

	
	
	@RequestMapping(value = { "/capnhat" }, method = RequestMethod.POST)
	public String index100(Model model, HttpServletRequest request) {
		
			int tong=0;
			for(int i=1; i<Integer.parseInt(request.getParameter("sttcauhoi").toString()); i++) {
				tong=tong+Integer.parseInt(request.getParameter("diemlan1"+i).toString());
				chiTietPhieuRenLuyenService.updateDiemLan1Va2(Integer.parseInt(request.getParameter("diemlan1"+i).toString()), Integer.parseInt(request.getParameter("diemlan2"+i).toString()), Long.parseLong(request.getParameter("idchitietphieurenluyen"+i).toString()));
			}
			phieuRenLuyenService.updateTongDiemLan1Va2(tong, tong, Long.parseLong(request.getParameter("idphieu")));
			String back = request.getHeader("Referer");
			return "redirect:"+back;
	    
	}
	
	
	
	
}
