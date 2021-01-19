package com.example.demo.controller.CanBoLop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.entity.PhieuRenLuyen;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.BoCauHoiService;
import com.example.demo.service.ChiTietPhieuRenLuyenService;
import com.example.demo.service.LopService;
import com.example.demo.service.PhieuRenLuyenService;
import com.example.demo.service.TaiKhoanService;

@Controller
@RequestMapping(value = "/cbl/canbolopdanhgiarenluyen")
public class CanBoLopTuDanhGiaController {
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

		String page = "/WEB-INF/jsp/CBL/canbolopdanhgiarenluyen.jsp";
			
		  List<PhieuRenLuyen> phieuRenLuyen=getTaiKhoanDangNhap().getPhieuRenLuyens();
		  
		  PhieuRenLuyen phieuRenLuyen2 = new PhieuRenLuyen();
		  for(int i=0;i<phieuRenLuyen.size();i++)
		  {
			  
			  if(!phieuRenLuyen.get(i).getDaDuyetLan2()) {phieuRenLuyen2=phieuRenLuyen.get(i); break;}
			  
		  }
		  
		  model.addAttribute("taikhoan", getTaiKhoanDangNhap());

		  List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen= phieuRenLuyen2.getChiTietPhieuRenLuyens();
		  request.getSession().setAttribute("listChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		  request.getSession().setAttribute("phieurenluyen", phieuRenLuyen2);
		  request.getSession().setAttribute("tensinhvien",getTaiKhoanDangNhap().getTen());
		  model.addAttribute("page", page);
		  model.addAttribute("activedanhgiarenluyen", "active");
		return "canBoLop";
	}
	

	@RequestMapping(value = { "/capnhat" }, method = RequestMethod.POST)
	public String index100(Model model, HttpServletRequest request) {
		
			int tong=0;
			for(int i=1; i<Integer.parseInt(request.getParameter("sttcauhoi").toString()); i++) {
				tong=tong+Integer.parseInt(request.getParameter("diemlan1"+i).toString());
				chiTietPhieuRenLuyenService.updateDiemLan1Va2(Integer.parseInt(request.getParameter("diemlan1"+i).toString()), Long.parseLong(request.getParameter("idchitietphieurenluyen"+i).toString()));
			}
			phieuRenLuyenService.updateTongDiemLan1(tong, Long.parseLong(request.getParameter("idphieu")));
			String back = request.getHeader("Referer");
			return "redirect:"+back;
	    
	}
}
