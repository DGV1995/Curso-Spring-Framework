package com.diegogarciaviana.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		// Este método redirige la aplicación a la vista indicada después de la palabra clase 'redirect'
		//return "redirect:/app/index"; // Cambia la ruta url
		return "forward:/app/index"; // Igual, pero sin reiniciar los datos del request (la ruta url se mantiene en la que estaba) ==> no se puede hacer un forward a una página externa de la aplicación (como google.es)
		//return "redirect:https://www.google.es";
	}

}
