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
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems() {
		Producto balon = new Producto("Balón", 15);
		Producto raqueta = new Producto("Raqueta", 50);
		
		ItemFactura item1 = new ItemFactura(balon, 3);
		ItemFactura item2 = new ItemFactura(raqueta, 1);
		
		return Arrays.asList(item1, item2);
	}

}
