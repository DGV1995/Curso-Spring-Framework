package com.diegogarciaviana.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@Value("${application.title}")
	private String titulo;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("titulo", titulo);
		
		return "index";
		
	}

}
