package com.diegogarciaviana.springboot.app.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegogarciaviana.springboot.app.models.dao.IClienteDao;
import com.diegogarciaviana.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	// Capa de servicio
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

}