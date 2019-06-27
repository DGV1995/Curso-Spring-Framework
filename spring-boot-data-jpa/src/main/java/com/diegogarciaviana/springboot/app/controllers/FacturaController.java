package com.diegogarciaviana.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;
import com.diegogarciaviana.springboot.app.models.entity.Factura;
import com.diegogarciaviana.springboot.app.models.service.IClienteService;
import com.diegogarciaviana.springboot.app.models.service.IFacturaService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired 
	private IClienteService clienteService;
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model) {
		
		Factura factura = facturaService.findOne(id);
		
		model.addAttribute("titulo", "Factura con ID: " + factura.getId());
		model.addAttribute("factura", factura);
		
		return "factura/factura";
		
	}
	
	@GetMapping("/crear/{clienteId}") 
	public String formulario(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(clienteId);
		
		// Validamos que exista el cliente en nuestra base de datos
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Factura factura = new Factura();
		factura.setCliente(cliente);
		
		model.addAttribute("titulo", "Crear nueva factura");
		model.addAttribute("factura", factura);
		
		return "factura/crear_factura";
		
	}
	
	@PostMapping("/crear")
	public String crear(@Valid Factura factura, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear nueva factura");
			return "crear_factura";
		}
		
		facturaService.save(factura);
		
		status.setComplete();
		
		flash.addFlashAttribute("success", "Factura guardado correctamente");
		
		return "redirect:/listar";
		
	}

}
