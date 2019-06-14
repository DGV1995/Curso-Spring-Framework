package com.diegogarciaviana.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarciaviana.app.controllers.models.services.ServiceInterface;

@Controller
public class IndexController {
	
	@Value("${textIndex}")
	private String textIndex;
	
	// Indicamos cuál es el @Component que queremos inyectar
	@Qualifier("miServicioComplejo")
	@Autowired
	private ServiceInterface myService;
	
	@GetMapping("/")
	public String index(Model model) {
		
		model.addAttribute("titulo", textIndex);
		model.addAttribute("servicio", myService);
		
		return "index";
		
	}

}
