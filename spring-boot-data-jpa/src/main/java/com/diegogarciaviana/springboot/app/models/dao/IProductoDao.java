package com.diegogarciaviana.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diegogarciaviana.springboot.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
	
	/*@Query("select p from Producto p where p.nombre like %?1%") // Sustituye este término por el primer parámetro del método (name en este caso)
	public List<Producto> findByName(String name);*/
	
	public List<Producto> findByNombreLikeIgnoreCase(String name);
	
}
