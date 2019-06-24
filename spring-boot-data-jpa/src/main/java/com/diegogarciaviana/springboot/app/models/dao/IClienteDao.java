package com.diegogarciaviana.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.diegogarciaviana.springboot.app.models.entity.Cliente;

// No es necesario ponerle ningún decorador, ya que hereda de CrudRepository
public interface IClienteDao extends CrudRepository<Cliente, Long> {
 
}
