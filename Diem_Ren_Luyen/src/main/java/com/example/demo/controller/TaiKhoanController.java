package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.service.TaiKhoanService;
@Controller
public class TaiKhoanController {
	public final TaiKhoanService taiKhoanService;
	public TaiKhoanController(TaiKhoanService taiKhoanService)
	{
		super();
		this.taiKhoanService = taiKhoanService;
	}
	@RequestMapping(value = { "/quanly/taikhoan" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/taikhoan.jsp";
		List<TaiKhoan> listTaiKhoan = taiKhoanService.getAll();
		model.addAttribute("ListTaiKhoan", listTaiKhoan);
		model.addAttribute("page",page);
		model.addAttribute("activequanlytaikhoan", "active");
		return "adminMaster";
						}
}
