package com.example.demo.controller;

import java.time.Year;
import java.util.ArrayList;
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
@RequestMapping(value = "/quanly/duyetlan3")
public class ChiTietDanhGiaLan3Controller {
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

	@RequestMapping(value = { "", "/" })
	public String index(Model model, HttpServletRequest request) {
		String page = "/WEB-INF/jsp/admin/xetduyetlan3.jsp";

		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanSinhVien();

		List<PhieuRenLuyen> listPhieuRenLuyen = listTaiKhoan.get(0).getPhieuRenLuyens();
		for (int i = 1; i < listTaiKhoan.size(); i++) {

			listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());

		}
		model.addAttribute("lopselect", listTaiKhoan.get(0).getIdLop());
		model.addAttribute("selecthocky", 1);
		model.addAttribute("namhocselect", Year.now().getValue());
		model.addAttribute("daduyetlan3select", true);
		request.getSession().setAttribute("listPhieuRenLuyen", listPhieuRenLuyen);
		model.addAttribute("page", page);
		model.addAttribute("activeduyetlan3", "active");

		return "adminMaster";
	}

	@RequestMapping(value = "/loc", method = RequestMethod.POST)
	public String index2(Model model, HttpServletRequest request) {
		long idlop = Long.parseLong(request.getParameter("idlop"));
		int namhoc = Integer.parseInt(request.getParameter("namhoc"));
		int hocky = Integer.parseInt(request.getParameter("hocky"));
		boolean daduyetlan3 = Boolean.parseBoolean(request.getParameter("daduyetlan3"));
		
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getTaiKhoanSinhVien();
		List<PhieuRenLuyen> listPhieuRenLuyen = listTaiKhoan.get(0).getPhieuRenLuyens();
		
		for (int i = 1; i < listTaiKhoan.size(); i++) {
			listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());
		}
		for (int j = 0; j < listPhieuRenLuyen.size(); j++) {
			if (listPhieuRenLuyen.get(j).getMaSinhVien().getIdLop().getIdLop() != idlop ||listPhieuRenLuyen.get(j).getHocKy() != hocky || listPhieuRenLuyen.get(j).getNamHoc() != namhoc || listPhieuRenLuyen.get(j).getDaDuyetLan3() != daduyetlan3) {
				listPhieuRenLuyen.remove(j);
				j--;
			}
		}
		model.addAttribute("listPhieuRenLuyen", listPhieuRenLuyen);
		model.addAttribute("lopselect", lopService.getByID(idlop).get());
		model.addAttribute("namhocselect", namhoc);
		model.addAttribute("selecthocky", hocky);
		model.addAttribute("daduyetlan3select", daduyetlan3);
		String page = "/WEB-INF/jsp/admin/xetduyetlan3.jsp";
		model.addAttribute("page", page);
		model.addAttribute("activeduyetlan3", "active");
		return "adminMaster";
	}
	@RequestMapping(value = { "/duyetmot" }, method = RequestMethod.POST)
	public String index6(Model model, HttpServletRequest request) {
		Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
		phieuRenLuyenService.updateDuyetLan3(idPhieu);
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}

	@RequestMapping(value = { "/duyettatca" }, method = RequestMethod.POST)
	public String index3(Model model, HttpServletRequest request) {
		
		@SuppressWarnings("unchecked")
		List<PhieuRenLuyen> listPhieu=  (List<PhieuRenLuyen>) request.getSession().getAttribute("listPhieuRenLuyen");
		for(int i=0;i<listPhieu.size();i++)
		{
			PhieuRenLuyen phieuRenLuyen = listPhieu.get(i);
			phieuRenLuyen.setDaDuyetLan3(true);
			phieuRenLuyenService.update(phieuRenLuyen);
		}
		
		String back = request.getHeader("Referer");
		return "redirect:"+back;
	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request, @PathVariable Long id) {
		String page = "/WEB-INF/jsp/admin/chitietphieurenluyen.jsp";
		  Optional<PhieuRenLuyen> phieuRenLuyen = phieuRenLuyenService.getByID(id);
		  TaiKhoan taiKhoan= phieuRenLuyen.get().getMaSinhVien();
		  model.addAttribute("taikhoan", taiKhoan);
		  
		  List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen= phieuRenLuyen.get().getChiTietPhieuRenLuyens();
		  request.getSession().setAttribute("listChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		  model.addAttribute("page", page);
		  model.addAttribute("activeduyetlan3", "active");
		return "adminMaster";
	}
	
	@RequestMapping(value = { "/tinhtongvaduyetphieu" }, method = RequestMethod.POST)
	public String index22(Model model, HttpServletRequest request) {
		if (request.getParameter("tinhtong") != null) {
			int tong=0;
			for(int i=1; i<Integer.parseInt(request.getParameter("sttcauhoi").toString()); i++) {
				tong=tong+Integer.parseInt(request.getParameter("diemlan3"+i).toString());
				chiTietPhieuRenLuyenService.updateDiemLan3(Integer.parseInt(request.getParameter("diemlan3"+i).toString()), (CauHoi)request.getAttribute("idcauhoi"+i));
			}
			phieuRenLuyenService.updateTongDiemLan3(tong, Long.parseLong(request.getParameter("idphieu")));
			String back = request.getHeader("Referer");
			return "redirect:"+back;
	    } else if (request.getParameter("duyet") != null) {
	    	Long idPhieu=Long.parseLong(request.getParameter("idphieu"));
			phieuRenLuyenService.updateDuyetLan3True(idPhieu);
			return "redirect:/quanly/duyetlan3";
	    }
		
		return null;
	}
	
	
	
	
}
