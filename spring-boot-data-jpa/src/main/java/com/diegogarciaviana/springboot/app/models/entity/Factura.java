package com.diegogarciaviana.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String descripcion;

	private String observacion;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;

	// Muchas facturas para un cliente
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cliente cliente;

	/**
	 * Relación unidireccional ==> La factura necesita los items, pero los ítems no
	 * necesitan a la factura
	 **/
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "factura_id") // Importante para generar la llave foránea 'factura_id' en la tabla
										// FACTURA_ITEMS
	private List<ItemFactura> items;

	// Se ejecuta antes de guardarse en la base de datos
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	public Factura() {
		this.items = new ArrayList<ItemFactura>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void addItem(ItemFactura item) {
		items.add(item);
	}

	public Double getTotal() {
		Double total = 0.0;
		for (ItemFactura item : items) {
			total += item.calcularImporte();
		}
		return total;
	}

}
