package com.diegogarciaviana.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegogarciaviana.springboot.app.models.dao.IProductoDao;
import com.diegogarciaviana.springboot.app.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	public Producto findOne(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	public void guardar(Producto producto) {
		productoDao.save(producto);
	}

}
