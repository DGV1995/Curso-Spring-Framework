package com.diegogarciaviana.app.controllers.models.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("miServicioComplejo")
//@Primary 
// Con este decorador se indica que, si no se especifica que clase de la interfaz es la que se quiere (@Qualifier), se injecte esta clase
public class ComplexService implements ServiceInterface {
	
	@Override
	public String operacion() {
		return "Se ha producido la operación compleja";
	}

}
