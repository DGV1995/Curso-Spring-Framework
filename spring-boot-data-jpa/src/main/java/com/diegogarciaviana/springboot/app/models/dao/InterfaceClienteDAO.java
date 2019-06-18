package com.diegogarciaviana.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

public interface InterfaceClienteDAO {
	
	public List<Cliente> findAll();
	public Cliente findById(Long id);
 
}
