package com.diegogarciaviana.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping("/index")
	public String index(Model model) {
		// Con la interfaz model podemos establecer variables en el controlador, que usaremos en la vista (html)
		model.addAttribute("titulo", "Hola Spring Framework");
		// retornamos un String con el nombre de la vista (index.html)
		return "index";
	}
	
	// Otra forma sería:
	/*
	@GetMapping("/index")
	public ModelView index(ModelAndView mv) {
		mv.addObject("titulo", "Hola Spring Framework");
		mv.setViewName("index");
		return mv;
	}
	 */

}
