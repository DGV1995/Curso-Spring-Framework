package com.diegogarciaviana.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diegogarciaviana.springboot.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long> {
	
	// Query para hacer inner joins entre las tablas (Una factura tiene un cliente y una lista de items, y esta lista de items tiene productos
	// Este método sustituye al findById(Long id);
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id = ?1")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
	
	
}
