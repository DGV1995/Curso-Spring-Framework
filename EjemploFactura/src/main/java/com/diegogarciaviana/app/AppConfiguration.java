package com.diegogarciaviana.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diegogarciaviana.app.models.entity.ItemFactura;
import com.diegogarciaviana.app.models.entity.Producto;

@Configuration
public class AppConfiguration {
	
	// Definimos beans que usaremos en nuestra aplicación
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems() {
		Producto producto1 = new Producto("Balón", 15);
		Producto producto2 = new Producto("Raqueta", 50);
		
		ItemFactura item1 = new ItemFactura(producto1, 3);
		ItemFactura item2 = new ItemFactura(producto2, 1);
		
		return Arrays.asList(item1, item2);
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina() {
		Producto producto1 = new Producto("Monitor Samsung 32", 400);
		Producto producto2 = new Producto("Impresora HP", 250);
		Producto producto3 = new Producto("Escritorio", 300);
		
		ItemFactura item1 = new ItemFactura(producto1, 10);
		ItemFactura item2 = new ItemFactura(producto2, 2);
		ItemFactura item3 = new ItemFactura(producto3, 1);
		
		return Arrays.asList(item1, item2, item3);
		
	}

}
