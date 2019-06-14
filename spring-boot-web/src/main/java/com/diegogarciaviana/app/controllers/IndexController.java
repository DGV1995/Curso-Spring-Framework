package com.diegogarciaviana.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diegogarciaviana.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${textoIndex}") // Variable definida en el application.properties
	private String textoIndex;
	
	@Value("${textoPerfil}")
	private String textoPerfil;
	
	@Value("${textoListar}")
	private String textoListar;
	
	@GetMapping("/index")
	public String index(Model model) {
		// Con la interfaz model podemos establecer variables en el controlador, que usaremos en la vista (html)
		model.addAttribute("titulo", textoIndex);
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
	
	@GetMapping("/perfil")
	public String getUsuario(Model model) {
		
		Usuario usuario = new Usuario();
		
		usuario.setNombre("Diego");
		usuario.setApellido("García-Viana");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textoPerfil.concat(usuario.getNombre()));
		model.addAttribute("correo", "diego@correo.com");
		
		return "perfil";
		
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", textoListar);
		
		return "listar";
		
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> getUsuarios() {
		
		// Crearemos y retornaremos una lista de usuarios, que utilizaremos como atributo en nuestra vista
		
		List<Usuario> usuarios = new ArrayList<>();
		
		usuarios.add(new Usuario("Diego","García-Viana","diego@correo.com"));
		usuarios.add(new Usuario("Pepe", "Martínez", "pepe@correo.com"));
		usuarios.add(new Usuario("María", "Rodríguez", "maria@correo.com"));
		
		return usuarios;
		
	}

}
