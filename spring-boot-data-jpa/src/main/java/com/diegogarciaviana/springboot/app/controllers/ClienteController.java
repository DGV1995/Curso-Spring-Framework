package com.diegogarciaviana.springboot.app.controllers;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;
import com.diegogarciaviana.springboot.app.models.entity.Factura;
import com.diegogarciaviana.springboot.app.models.service.IClienteService;
import com.diegogarciaviana.springboot.app.models.service.IFacturaService;
import com.diegogarciaviana.springboot.app.models.service.IUploadFileService;
import com.diegogarciaviana.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@Autowired
	private IFacturaService facturaService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${listar.title}")
	private String listar_titulo;
	
	@Value("${formulario.title}")
	private String formulario_titulo;
	
	@Value("${editar.title}")
	private String editar_titulo;
	
	@Value("${ver.title}")
	private String ver_titulo;
	
	@Value("${images.path}")
	private String UPLOADS_FOLDER;
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		//Cliente cliente = clienteService.findOne(id);
		Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", ver_titulo.concat(cliente.getNombre()));
		
		return "ver";
		
	}
	
	@GetMapping("/uploads/{filename:.+}") // Para que Spring no borre la extensión del archivo
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		
		// Muestra la foto del cliente
		return uploadFileService.load(filename);
		
	}
	
	@GetMapping({"/listar", "/"})
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		// Creamos objeto Pegeable ==> implementa la paginación
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo", listar_titulo);
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", formulario_titulo);
			return "form";
		}
		
		// Devolvemos el cliente una vez se le ha añadido la foto
		cliente = uploadFileService.subirFoto(cliente, foto, flash);
		
		// Definir el mensaje flash en función de si se ha creado un nuevo cliente o se ha editado uno ya existente
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con éxito!" : "Cliente creado con éxito!";
		
		clienteService.save(cliente);
		status.setComplete();
		
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if (id > 0)  {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del no existe en la BBDD!");
				return "redirect:/listar";
			}
		}
		
		else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", editar_titulo);
		
		return "form";
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		// Eliminamos cliente de la base de datos
		if (id > 0) {
			
			Cliente cliente = clienteService.findOne(id);
			clienteService.delete(id);
			
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
			
			uploadFileService.borrarFoto(cliente.getFoto(), flash);
			
		}
		
		// Pasamos los clientes que quedan a la vista
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("titulo", listar_titulo);
		
		return "redirect:/listar";
		
	}

}
