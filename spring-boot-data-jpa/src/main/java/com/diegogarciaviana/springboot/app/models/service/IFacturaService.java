package com.diegogarciaviana.springboot.app.models.service;

import com.diegogarciaviana.springboot.app.models.entity.Factura;

public interface IFacturaService {
	
	public Factura findOne(Long id);
	
	public void save(Factura factura);
	
	public void delete(Factura factura);
	
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id);

}
