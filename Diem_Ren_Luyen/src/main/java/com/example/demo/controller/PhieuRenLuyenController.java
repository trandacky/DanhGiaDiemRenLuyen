package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.PhieuRenLuyen;
import com.example.demo.service.PhieuRenLuyenService;
@Controller
public class PhieuRenLuyenController {

	public final PhieuRenLuyenService phieurenluyenService;
	public PhieuRenLuyenController(PhieuRenLuyenService phieurenluyenService) {
		super();
		this.phieurenluyenService = phieurenluyenService;
	}
	
	@RequestMapping(value = { "/quanlyphieurenluyen" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/danhgialan2.jsp";
		List<PhieuRenLuyen> listPhieuRenLuyen = phieurenluyenService.getAll();
		model.addAttribute("ListPhieuRenLuyen", listPhieuRenLuyen);
		model.addAttribute("page",page);
		return "canBoLop";
	}
	
}
