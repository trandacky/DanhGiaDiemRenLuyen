package com.example.demo.controller.CanBoLop;

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
		  model.addAttribute("listPhieuRenLuyen", listPhieuRenLuyen);
		  request.getSession().setAttribute("tensinhvien",getTaiKhoanDangNhap().getTen());
		  model.addAttribute("page", page);
		  model.addAttribute("activedanhsachlop", "active");
		return "canBoLop";
	}

	@RequestMapping(value = { "/duyetmot" }, method = RequestMethod.POST)
	public String index6(Model model, HttpServletRequest request) {
		Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
		phieuRenLuyenService.updateDuyetLan2(idPhieu);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request, @PathVariable Long id) {
		String page = "/WEB-INF/jsp/CBL/danhgialan2.jsp";
		  Optional<PhieuRenLuyen> phieuRenLuyen = phieuRenLuyenService.getByID(id);
		  TaiKhoan taiKhoan= phieuRenLuyen.get().getMaSinhVien();
		  model.addAttribute("taikhoan", taiKhoan);
		  
		  List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen= phieuRenLuyen.get().getChiTietPhieuRenLuyens();
		  request.getSession().setAttribute("listChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		  model.addAttribute("page", page);
		  model.addAttribute("activedanhsachlop", "active");
		return "canBoLop";
	}
	@RequestMapping(value = { "/tinhtongvaduyetphieu" }, method = RequestMethod.POST)
	public String index22(Model model, HttpServletRequest request) {
		if (request.getParameter("tinhtong") != null) {
			int tong=0;
			for(int i=1; i<Integer.parseInt(request.getParameter("sttcauhoi").toString()); i++) {
				tong=tong+Integer.parseInt(request.getParameter("diemlan2"+i).toString());
				chiTietPhieuRenLuyenService.updateDiemLan2(Integer.parseInt(request.getParameter("diemlan2"+i).toString()), Long.parseLong(request.getParameter("idchitietphieurenluyen"+i).toString()));
			}
			phieuRenLuyenService.updateTongDiemLan1(tong, Long.parseLong(request.getParameter("idphieu")));
			String back = request.getHeader("Referer");
			return "redirect:"+back;
	    } else if (request.getParameter("duyet") != null) {
	    	Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
			phieuRenLuyenService.updateDuyetLan2True(idPhieu);
			return "redirect:/cbl/danhsachsinhvien";
	    }
		
		return null;
	}

}
