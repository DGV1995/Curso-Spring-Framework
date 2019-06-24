package com.diegogarciaviana.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

@Repository
public class ClienteService implements InterfaceClienteDAO {
	
	// Servicio que gestiona las manipulaciones sobre la base de datos
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		// Retorna los registros que se han insertado en el archivo import.sql
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Override
	@Transactional
	public void save(Cliente cliente) {
		// Si el cliente ya existe (id != null), lo actualiza (merge)
		if (cliente.getId() != null && cliente.getId() > 0)
			em.merge(cliente);
		// Si no existe, crea uno nuevo (persist) ==> El id viene null por defecto
		else if (cliente.getId() > 0)
			em.persist(cliente);
	}
	
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

}
