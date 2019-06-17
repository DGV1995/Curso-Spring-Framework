package com.diegogarciaviana.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarciaviana.app.models.entity.Factura;
import com.diegogarciaviana.app.models.entity.ItemFactura;

@Controller
@RequestMapping("/factura")
public class FacturaController {
	
	@Autowired
	private Factura factura;
	
	@GetMapping("/ver")
	public String factura(Model model) {
		
		model.addAttribute("titulo", "Ejemplo factura con inyección de dependencia");
		model.addAttribute("factura", factura);
		
		return "factura/ver";
		
	}

}
