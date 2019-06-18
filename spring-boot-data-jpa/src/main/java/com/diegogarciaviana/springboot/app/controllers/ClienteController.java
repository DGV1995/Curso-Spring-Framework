package com.diegogarciaviana.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarciaviana.springboot.app.models.dao.InterfaceClienteDAO;
import com.diegogarciaviana.springboot.app.models.entity.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private InterfaceClienteDAO clienteDao;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", "Listado de clientes");
				
		model.addAttribute("clientes", clienteDao.findAll());
		
		return "listar";
		
	}

}
