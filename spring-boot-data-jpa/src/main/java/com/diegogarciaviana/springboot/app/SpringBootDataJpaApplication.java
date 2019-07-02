package com.diegogarciaviana.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diegogarciaviana.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {
	
	@Autowired
	private IUploadFileService uploadFileService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	// De esta forma nos aseguramos de que se borren las imágenes cada vez que se inicia la aplicación
	@Override
	public void run(String... args) throws Exception {
		uploadFileService.deleteAll();
		uploadFileService.init();
	}

}
