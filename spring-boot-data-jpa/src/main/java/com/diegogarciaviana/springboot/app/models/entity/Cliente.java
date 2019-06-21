package com.diegogarciaviana.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Table(name = "clientes") // Para configurar el nombre de la tabla
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id // Indica que este atributo es la llave (key)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String apellido;
	private String email;

	@Column(name = "create_at") // Para indicar el nombre de la columna
	@Temporal(TemporalType.DATE) // Indica el formato de la fecha
	private Date createAt;
	
	/**
	@PrePersist // Se ejecuta antes de guardarse en la base de datos
	public void prePersist() {
		this.createAt = new Date();
	}
	**/

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
