package com.diegogarciaviana.springboot.app.models.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diegogarciaviana.springboot.app.models.dao.IFacturaDao;
import com.diegogarciaviana.springboot.app.models.entity.Factura;

@Service
public class FacturaServiceImpl implements IFacturaService {
	
	@Autowired
	private IFacturaDao facturaDao;

	@Override
	@Transactional(readOnly = true)
	public Factura findOne(Long id) {
		return facturaDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public void save(Factura factura) {
		facturaDao.save(factura);
	}
	
	@Override
	@Transactional
	public void delete(Factura factura) {
		facturaDao.delete(factura);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}

}
