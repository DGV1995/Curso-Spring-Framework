package com.diegogarciaviana.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.diegogarciaviana.springboot.app.models.entity.ItemFactura;

public interface IItemFacturaDao extends CrudRepository<ItemFactura, Long> {

}
