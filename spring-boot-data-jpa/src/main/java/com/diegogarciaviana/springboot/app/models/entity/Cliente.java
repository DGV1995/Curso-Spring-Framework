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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes") // Para configurar el nombre de la tabla ==> Clientes
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Indica que este atributo es la llave (key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Reglas de validación **/
	
	@NotEmpty //@NotEmpty se utiliza solo con strings
	// @Size(min = 4, max = 12)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email // Validar que tenga el formato de email
	private String email;

	@Column(name = "create_at") // Para indicar el nombre de la columna
	@Temporal(TemporalType.DATE) // Indica el formato de la fecha
	@DateTimeFormat(pattern="dd/MM/yyyy") // Especificar el formato de la fecha
	@NotNull // Para los atributos que no sean strings
	private Date createAt;
	
	private String foto;
	
	// Un cliente puede tener muchas facturas
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Factura> facturas;
	
	/**
	@PrePersist // Se ejecuta antes de guardarse en la base de datos
	public void prePersist() {
		this.createAt = new Date();
	}
	**/
	
	public Cliente() {
		facturas = new ArrayList<Factura>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void addFactura(Factura factura) {
		facturas.add(factura);
	}

}
