package com.diegogarciaviana.app.models.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
//Con este decorador, hacemos que el bean 'Cliente' dure lo que dura una petición http (ya no es Singleton)
//cada usuairo que se conecte va a tener una factura distinta y propia.
//El objeto se va a actualizar cada vez que recarguemos el navegador.
public class Cliente {

	@Value("${cliente.nombre}")
	private String nombre;
	
	@Value("${cliente.apellido}")
	private String apellido;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
