package com.diegogarciaviana.springboot.app.models.service;

import com.diegogarciaviana.springboot.app.models.entity.ItemFactura;

public interface IItemFacturaService {
	
	public ItemFactura findOne(Long id);
	public void guardar(ItemFactura itemFactura);

}
