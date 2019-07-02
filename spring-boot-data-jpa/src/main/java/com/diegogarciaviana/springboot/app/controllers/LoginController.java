package com.diegogarciaviana.springboot.app.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error, Model model, Principal principal, RedirectAttributes flash) {
		
		// Si ya se ha iniciado sesión anteriormente
		if (principal != null) {
			flash.addFlashAttribute("info", "Ya hay una sesión iniciada!");
			return "redirect:/";
		}
		
		if (error != null) 
			model.addAttribute("error", "Error en el login: usuario o contraseña incorrecta");
		
		return "login";
		
	}
	
	@GetMapping("/logout")
	public String logout(Model model, RedirectAttributes flash) {
		
		flash.addFlashAttribute("success", "Sesión cerrada con éxito");
		
		return "redirect:/";
		
	}

}
