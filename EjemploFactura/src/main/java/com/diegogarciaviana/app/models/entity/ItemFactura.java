package com.diegogarciaviana.app.models.entity;

public class ItemFactura {
	
	// Un item factura tiene un producto y la cantidad de dicho producto comprada
	private Producto producto;
	private Integer cantidad;
	
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
	
	public ItemFactura(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}	
	
	public Integer calcularImporte() {
		return producto.getPrecio() * cantidad;
	}

}
