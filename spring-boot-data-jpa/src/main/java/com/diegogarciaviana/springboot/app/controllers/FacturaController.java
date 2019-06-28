package com.diegogarciaviana.springboot.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;
import com.diegogarciaviana.springboot.app.models.entity.Factura;
import com.diegogarciaviana.springboot.app.models.entity.ItemFactura;
import com.diegogarciaviana.springboot.app.models.entity.Producto;
import com.diegogarciaviana.springboot.app.models.service.IClienteService;
import com.diegogarciaviana.springboot.app.models.service.IFacturaService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	@GetMapping("/form/{clienteId}") 
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
		
		return "factura/form";
		
	}
	
	@GetMapping(value = "/cargar-productos/{name}", produces = {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String name) {
		return clienteService.findByName(name);
	}
	
	@PostMapping("/form")
	public String guardar(Factura factura, 
						 @RequestParam(name="item_id[]", required = false) Long[] itemId,
						 @RequestParam(name="cantidad[]", required = false) Integer[] cantidad, 
						 RedirectAttributes flash, SessionStatus status) {
		
		for (int i = 0; i < itemId.length; i++) {
			
			Producto producto = clienteService.findProductoById(itemId[i]);
			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItem(linea);
			
			log.info("Producto: " + producto.getNombre());
			log.info("Cantidad: " + cantidad[i]);
			
		}
		
		clienteService.saveFactura(factura);
		status.setComplete();
		
		flash.addFlashAttribute("success", "Factura guardada correctamente");
		
		return "redirect:/ver/" + factura.getCliente().getId();
		
	}

}
