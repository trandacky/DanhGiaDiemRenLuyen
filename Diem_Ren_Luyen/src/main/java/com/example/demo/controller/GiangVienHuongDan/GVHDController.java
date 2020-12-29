package com.example.demo.controller.GiangVienHuongDan;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.BoCauHoi;
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

	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		String page = "/WEB-INF/jsp/GVHD/duyetlan2.jsp";
		
		List<TaiKhoan> listTaiKhoan=taiKhoanService.getTaiKhoanByIDLop((long)1);// lấy lớp của gvhd
		
		List<PhieuRenLuyen> listPhieuRenLuyen=listTaiKhoan.get(0).getPhieuRenLuyens();
		  for(int i=1;i<listTaiKhoan.size();i++) {
		  
		  listPhieuRenLuyen.addAll(listTaiKhoan.get(i).getPhieuRenLuyens());
		  
		  
		  }
		model.addAttribute("listPhieuRenluyen", listPhieuRenLuyen);
		
		model.addAttribute("page", page);
		
		return "GVHDMaster";
	}
	//seach với tình trạng, năm, học kỳ
}
