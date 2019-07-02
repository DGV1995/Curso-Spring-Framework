package com.diegogarciaviana.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;
import com.diegogarciaviana.springboot.app.models.entity.Factura;

// No es necesario ponerle ningún decorador, ya que PaginAndSortingRepository hereda de CrudRepository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {
	
	// Una sola consulta para que el cliente traiga todas sus facturas
	// Este método sustituye al findById(Long id);
	@Query("select c from Cliente c left join fetch c.facturas f where c.id = ?1")
	public Cliente fetchByIdWithFacturas(Long id);
 
}
