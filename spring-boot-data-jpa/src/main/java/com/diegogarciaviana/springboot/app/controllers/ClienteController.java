package com.diegogarciaviana.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarciaviana.springboot.app.models.dao.InterfaceClienteDAO;
import com.diegogarciaviana.springboot.app.models.entity.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private InterfaceClienteDAO clienteDao;
	
	@Value("${listar.title}")
	private String listar_titulo;
	
	@Value("${formulario.title}")
	private String formulario_titulo;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", listar_titulo);
				
		model.addAttribute("clientes", clienteDao.findAll());
		
		return "listar";
		
	}
	
	@GetMapping("listar/{id}")
	public String findOne(@PathVariable Long id, Model model) {
				
		model.addAttribute("titulo", "Cliente encontrado");
		model.addAttribute("cliente", clienteDao.findById(id));
		
		return "cliente";
		
	}
	
	@GetMapping("/form")
	public String formulario(Model model) {
		
		Cliente cliente = new Cliente();

		model.addAttribute("titulo", formulario_titulo);
		model.addAttribute("cliente", cliente);
		
		return "form";
	}
	
	@PostMapping("/form")
	/** Con el decorador @Valid nos aseguramos de que el objecto Cliente tenga un formato válido (acorda a los requisitos configurados en la clase Cliente) **/
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", formulario_titulo);
			return "form";
		}
		
		clienteDao.save(cliente);
		
		return "redirect:listar";
	}

}
