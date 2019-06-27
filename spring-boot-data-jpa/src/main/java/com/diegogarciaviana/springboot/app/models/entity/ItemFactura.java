package com.diegogarciaviana.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Relación unidireccional ==> Un ítem necesita un producto, pero el producto no necesita un ítem
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id") // Creará una llave 'producto_id' en la tabla FACTURA_ITEMS
	private Producto producto;

	private Integer cantidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double calcularImporte() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
