package com.diegogarciaviana.app.models.entity;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope 
// Con este decorador, hacemos que el bean 'Factura' dure lo que dura una petición http (ya no es Singleton)
// cada usuairo que se conecte va a tener una factura distinta y propia.
// El objeto se va a actualizar cada vez que recarguemos el navegador.
// @SessionScope ==> para trabajar con sesiones --> El objeto dura lo que dura una sesión (hasta que cerremos el navegador, se invalide la sesión u ocurra un tiemout)
public class Factura {

	@Value("${factura.descripcion}")
	private String descripcion;

	// Una factura tiene un cliente y varios ItemFactura (uno por cada producto comprado)
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("itemsFacturaOficina") // Inyectamos el @Bean("itemsFacturaOficina") definido en la clase AppConfiguration
	private List<ItemFactura> items; 

	@PostConstruct // Se ejecuta justo después de crear el objeto y después de que se hayan inyectado las dependencias
	public void inicializar() {
		cliente.setNombre(cliente.getNombre().concat(" ").concat("José"));
		descripcion = descripcion.concat(" del cliente: ").concat(cliente.getNombre());
	}
	
	@PreDestroy // Ejecuta al terminar el programa y destruirse el objeto Factura
	public void destruir() {
		System.out.println("Factura destruida: ".concat(descripcion));
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

}
