package com.example.demo.controller.GiangVienHuongDan;

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

@Controller
@RequestMapping(value = "/gvhd/duyetlan2")
public class GVHDController {
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
	public String index(Model model, HttpServletRequest request) {
		String page = "/WEB-INF/jsp/GVHD/duyetlan2.jsp";
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanByIDLop(getTaiKhoanDangNhap().getIdLop().getIdLop());
		// lấy lớp của gvhd
		List<PhieuRenLuyen> listPhieuRenLuyen = listTaiKhoan.get(0).getPhieuRenLuyens();
		for (int i = 1; i < listTaiKhoan.size(); i++) {
			listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());
		}
	    request.getSession().setAttribute("tengiangvien",getTaiKhoanDangNhap().getTen());
		model.addAttribute("selecthocky", 1);
		model.addAttribute("namhocselect", Year.now().getValue());
		request.getSession().setAttribute("listPhieuRenLuyen", listPhieuRenLuyen);
		model.addAttribute("page", page);

		return "GVHDMaster";
	}

	@RequestMapping(value = "/loc", method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		int namhoc = Integer.parseInt(request.getParameter("namhoc"));
		int hocky = Integer.parseInt(request.getParameter("hocky"));
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanByIDLop((long) 1);// lấy lớp của gvhd
		List<PhieuRenLuyen> listPhieuRenLuyen = listTaiKhoan.get(0).getPhieuRenLuyens();
		
		for (int i = 1; i < listTaiKhoan.size(); i++) {
			listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());
		}
		for (int j = 0; j < listPhieuRenLuyen.size(); j++) {
			if (listPhieuRenLuyen.get(j).getHocKy() != hocky || listPhieuRenLuyen.get(j).getNamHoc() != namhoc) {
				listPhieuRenLuyen.remove(j);
				j--;
			}
		}
		model.addAttribute("listPhieuRenLuyen", listPhieuRenLuyen);
		model.addAttribute("namhocselect", namhoc);
		model.addAttribute("selecthocky", hocky);
		String page = "/WEB-INF/jsp/GVHD/duyetlan2.jsp";
		model.addAttribute("page", page);
		return "GVHDMaster";
	}
	@RequestMapping(value = { "/duyetmot" }, method = RequestMethod.POST)
	public String index6(Model model, HttpServletRequest request) {
		Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
		phieuRenLuyenService.updateDuyetLan2(idPhieu);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	@RequestMapping(value = { "/duyetphieu" }, method = RequestMethod.POST)
	public String index19(Model model, HttpServletRequest request) {
		Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
		phieuRenLuyenService.updateDuyetLan2True(idPhieu);
		return "redirect:/gvhd/duyetlan2";
	}
	@RequestMapping(value = { "/duyettatca" }, method = RequestMethod.POST)
	public String index3(Model model, HttpServletRequest request) {
		
		@SuppressWarnings("unchecked")
		List<PhieuRenLuyen> listPhieu=  (List<PhieuRenLuyen>) request.getSession().getAttribute("listPhieuRenLuyen");
		for(int i=0;i<listPhieu.size();i++)
		{
			PhieuRenLuyen phieuRenLuyen = listPhieu.get(i);
			phieuRenLuyen.setDaDuyetLan2(true);
			phieuRenLuyenService.update(phieuRenLuyen);
		}
		
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request, @PathVariable Long id) {
		String page = "/WEB-INF/jsp/GVHD/chitietphieu.jsp";
		  Optional<PhieuRenLuyen> phieuRenLuyen = phieuRenLuyenService.getByID(id);
		  TaiKhoan taiKhoan= phieuRenLuyen.get().getMaSinhVien();
		  model.addAttribute("taikhoan", taiKhoan);
		  
		  List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen= phieuRenLuyen.get().getChiTietPhieuRenLuyens();
		  request.getSession().setAttribute("listChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		  model.addAttribute("page", page);
		return "GVHDMaster";
	}
	
	//chức năng seach để làm sau vì chưa có lớp bảo mật để lọc ra session đang truy cập
	/*
	 * @RequestMapping(value = { "/seach" }, method = RequestMethod.GET) public
	 * String seach(Model model, HttpServletRequest request) { String seachString;
	 * seachString = request.getParameter("seach").trim(); List<TaiKhoan> taiKhoan =
	 * taiKhoanService.search(seachString);
	 * 
	 * String page = "/WEB-INF/jsp/admin/bocauhoi.jsp";
	 * 
	 * model.addAttribute("page", page); model.addAttribute("activebocauhoi",
	 * "active"); if(seachString=="") return "adminMaster"; List<BoCauHoi>
	 * listBoCauHoi = boCauHoiService.seach(seachString);
	 * model.addAttribute("ListBoCauHoi", listBoCauHoi); return "adminMaster"; }
	 */
}
