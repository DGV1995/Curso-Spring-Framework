package com.diegogarciaviana.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.diegogarciaviana.springboot.app.models.dao.IClienteDao;
import com.diegogarciaviana.springboot.app.models.entity.Cliente;
import com.diegogarciaviana.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Value("${listar.title}")
	private String listar_titulo;
	
	@Value("${formulario.title}")
	private String formulario_titulo;
	
	@Value("${editar.title}")
	private String editar_titulo;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", listar_titulo);
		model.addAttribute("clientes", clienteService.findAll());
		
		return "listar";
		
	}
	
	@GetMapping("/form")
	public String formulario(Model model) {
		
		Cliente cliente = new Cliente();

		model.addAttribute("titulo", formulario_titulo);
		model.addAttribute("cliente", cliente);
		
		return "form";
	}
	
	@PostMapping("/form")
	/** Con el decorador @Valid nos aseguramos de que el objecto Cliente tenga un formato válido (acorde a los requisitos configurados en la clase Cliente) **/
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", formulario_titulo);
			return "form";
		}
		
		clienteService.save(cliente);
		status.setComplete();
		
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		
		Cliente cliente = null;
		
		if (id > 0) 
			cliente = clienteService.findOne(id);
		
		else
			return "redirect:listar";
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", editar_titulo);
		
		return "form";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, Model model) {
		
		// Eliminamos cliente de la base de datos
		if (id > 0)
			clienteService.delete(id);
		
		// Pasamos los clientes que quedan a la vista
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("titulo", listar_titulo);
		
		return "redirect:/clientes/listar";
		
	}

}
