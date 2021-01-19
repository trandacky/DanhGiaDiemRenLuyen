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
@RequestMapping(value = "/cbl/danhsachsinhvien")
public class XemDanhSachLopController {
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

		String page = "/WEB-INF/jsp/CBL/danhsachsinhvien.jsp";
			
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanByIDLop(getTaiKhoanDangNhap().getIdLop().getIdLop());
		// lấy lớp
		List<PhieuRenLuyen> listPhieuRenLuyen = listTaiKhoan.get(0).getPhieuRenLuyens();
		for (int i = 1; i < listTaiKhoan.size(); i++) {
			listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());}
		for(int i=0;i<listPhieuRenLuyen.size();i++)
		{
			if(listPhieuRenLuyen.get(i).getDaDuyetLan2())
				{listPhieuRenLuyen.remove(i); i--;}
			
		}
		  model.addAttribute("taikhoan", getTaiKhoanDangNhap());
		  request.getSession().setAttribute("tensinhvien",getTaiKhoanDangNhap().getTen());
		  model.addAttribute("page", page);
		  model.addAttribute("activedanhsachlop", "active");
		return "canBoLop";
	}
	
//	@RequestMapping(value = { "/capnhat/up" }, method = RequestMethod.POST)
//	public String index8(Model model, HttpServletRequest request) {
//	
//		String back = request.getHeader("Referer");
//			Long idLop= Long.parseLong(request.getParameter("idlop"));
//			String idTaiKhoan = request.getParameter("layidtaikhoan");
//		taiKhoanService.updatelop(idTaiKhoan, idLop);
//		return "redirect:"+back;
//	}
}
