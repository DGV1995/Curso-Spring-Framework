package com.diegogarciaviana.springboot.app.models.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

public interface IUploadFileService {
	
	public ResponseEntity<Resource> load(String filename);
	
	public Cliente subirFoto(Cliente cliente, MultipartFile foto, RedirectAttributes flash);
	
	public void borrarFoto(String filename, RedirectAttributes flash);
	
	public void deleteAll();
	
	public void init() throws IOException;

}
