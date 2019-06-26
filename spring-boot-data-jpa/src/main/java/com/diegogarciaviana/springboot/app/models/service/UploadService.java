package com.diegogarciaviana.springboot.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

@Service
public class UploadService implements IUploadService{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Value("${images.path}")
	private String UPLOADS_FOLDER;
	
	@Override
	public ResponseEntity<Resource> load(String filename) {
		
		Path pathFoto = getPath(filename);
		log.info("pathFoto: "+ pathFoto);
		
		Resource recurso = null;
		
		try {
			// Se carga la imagen
			recurso = new UrlResource(pathFoto.toUri());
			if (!recurso.exists() || !recurso.isReadable())
				throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// Se pasa la imagen al cuerpo de la respuesta
		return ResponseEntity.ok()
							 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
							 .body(recurso);
		
	}
	
	@Override
	public Cliente subirFoto(Cliente cliente, MultipartFile foto, RedirectAttributes flash) {
		
		if (!foto.isEmpty()) {
			
			if (cliente.getId() != null && cliente.getId() > 0 && cliente.getFoto() != null && cliente.getFoto().length() > 0) {
				
				Path pathFoto = getPath(cliente.getFoto());
				File archivo = pathFoto.toFile();
				
				if (archivo.exists() && archivo.canRead())
					archivo.delete();
				
			}
			
			// Nombre final de la foto subida
			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			
			// Ruta donde guardaremos la imagen 
			Path rootAbsolutePath = getPath(uniqueFilename); // uploads/nombre.jpg
			
			log.info("Absolute root path: " + rootAbsolutePath);
			
			try {
				
				// Guardar imagen en la ruta especificada
				Files.copy(foto.getInputStream(), rootAbsolutePath);
				// Mensaje flash
				flash.addFlashAttribute("info", "Imagen " + uniqueFilename + " cargada correctamente");
				// Añadimos la foto al cliente en su respectivo atributo
				cliente.setFoto(uniqueFilename);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return cliente;
		
	}
	
	@Override
	public void borrarFoto(Cliente cliente, RedirectAttributes flash) {
		
		// Eliminamos la foto del cliente del directorio 'uploads'
		Path pathFoto = Paths.get(UPLOADS_FOLDER).resolve(cliente.getFoto()).toAbsolutePath();
		File archivo = pathFoto.toFile();
					
		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete())
				flash.addFlashAttribute("info", "Se ha eliminado la imagen ".concat(cliente.getFoto()));
		}
		
	}
	
	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

}
