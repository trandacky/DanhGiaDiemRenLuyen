package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.ChiTietPhieuRenLuyen;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.ChiTietPhieuRenLuyenService;
import com.example.demo.service.TaiKhoanService;

@Controller
public class ChiTietDanhGiaLan3Controller {
	public final ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService;
	public ChiTietDanhGiaLan3Controller(ChiTietPhieuRenLuyenService chiTietPhieuRenLuyenService)
	
	{
		super();
		this.chiTietPhieuRenLuyenService = chiTietPhieuRenLuyenService;
	}
	@RequestMapping(value = { "/quanly/chitietxetduyetlan3/{id}" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/quanlydanhgialan3.jsp";
		List<ChiTietPhieuRenLuyen> listChiTietPhieuRenLuyen = chiTietPhieuRenLuyenService.getAll();
		model.addAttribute("ListChiTietPhieuRenLuyen", listChiTietPhieuRenLuyen);
		model.addAttribute("page",page);
		model.addAttribute("activequanlydanhgialan3", "active");
		return "adminMaster";
						}
}
