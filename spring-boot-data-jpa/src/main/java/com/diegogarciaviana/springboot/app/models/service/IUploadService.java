package com.diegogarciaviana.springboot.app.models.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

public interface IUploadService {
	
	public ResponseEntity<Resource> load(String filename);
	public Cliente subirFoto(Cliente cliente, MultipartFile foto, RedirectAttributes flash);
	public void borrarFoto(Cliente cliente, RedirectAttributes flash);

}
