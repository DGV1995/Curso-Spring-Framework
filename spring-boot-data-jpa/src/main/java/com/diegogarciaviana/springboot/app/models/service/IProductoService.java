package com.diegogarciaviana.springboot.app.models.service;

import com.diegogarciaviana.springboot.app.models.entity.Producto;

public interface IProductoService {
	
	public Producto findOne(Long id);
	public void guardar(Producto producto);

}
