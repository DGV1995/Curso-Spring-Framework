package com.diegogarciaviana.springboot.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.diegogarciaviana.springboot.app.models.dao.IItemFacturaDao;
import com.diegogarciaviana.springboot.app.models.entity.ItemFactura;

public class ItemFacturaServiceImpl implements IItemFacturaService {
	
	@Autowired 
	private IItemFacturaDao itemFacturaDao;

	@Override
	public ItemFactura findOne(Long id) {
		return itemFacturaDao.findById(id).orElse(null);
	}

	@Override
	public void guardar(ItemFactura itemFactura) {
		itemFacturaDao.save(itemFactura);
	}

}
