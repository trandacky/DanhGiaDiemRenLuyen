package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/quanly/xetduyetlan3" )
public class XetDuyetLan3Controller {
	@RequestMapping(value = { "/","" }, method = RequestMethod.GET)
	public String index(Model model) {
		String page="/WEB-INF/jsp/admin/xetduyetlan3.jsp";
		model.addAttribute("page",page);
		model.addAttribute("activexetduyetlan3","active");
		return "adminMaster";
	}
}
