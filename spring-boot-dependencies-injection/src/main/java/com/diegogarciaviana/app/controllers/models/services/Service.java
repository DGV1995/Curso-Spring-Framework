package com.diegogarciaviana.app.controllers.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component("miServicioSimple")
public class Service implements ServiceInterface {
		
	@Override
	public String operacion() {
		return "Se ha producido la operación simple";
	}

}
