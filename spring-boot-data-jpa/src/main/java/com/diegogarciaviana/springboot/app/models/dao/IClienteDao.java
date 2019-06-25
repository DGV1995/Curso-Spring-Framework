package com.diegogarciaviana.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.diegogarciaviana.springboot.app.models.entity.Cliente;

// No es necesario ponerle ningún decorador, ya que PaginAndSortingRepository hereda de CrudRepository
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {
 
}
