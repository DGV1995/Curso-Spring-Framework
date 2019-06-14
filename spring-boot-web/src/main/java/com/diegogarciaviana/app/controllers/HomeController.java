package com.diegogarciaviana.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		// Este método redirige la aplicación a la vista indicada después de la palabra clase 'redirect'
		//return "redirect:/app/index";
		return "forward:/app/index"; // Igual, pero sin reiniciar los datos del request
		//return "redirect:https://www.google.es";
	}

}
