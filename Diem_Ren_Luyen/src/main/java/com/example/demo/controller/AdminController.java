package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:index";
	}
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET)
	public String index2(Model model) {
		return "index";
	}
}
