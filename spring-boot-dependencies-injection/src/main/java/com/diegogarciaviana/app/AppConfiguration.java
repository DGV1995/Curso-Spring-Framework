package com.diegogarciaviana.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.diegogarciaviana.app.controllers.models.services.ComplexService;
import com.diegogarciaviana.app.controllers.models.services.Service;
import com.diegogarciaviana.app.controllers.models.services.ServiceInterface;

// Esta clase simple como una definición para crear componentes (beans) mediante métodos
// Así evitamos tener que pones @Component en las clases que queramos inyectar
@Configuration
public class AppConfiguration {
	
	@Bean("miServicioSimple")
	@Primary
	public ServiceInterface registrarMiServicioSimple() {
		return new Service();
	}
	
	@Bean("miServicioComplejo")
	public ServiceInterface registrarMiServicioComplejo() {
		return new ComplexService();
	}

}
