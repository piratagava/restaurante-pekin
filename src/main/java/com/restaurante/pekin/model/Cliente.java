package com.restaurante.pekin.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	private Long idCliente;

	@NotNull(message = "Nombre no debe ir null")
	@Column(name = "username", length = 45, unique = true)
	private String username;

	@NotNull(message = "Apellido Paterno no debe ir null")
	@Column(name = "apellido_paterno")
	private String apellidoPaterno;

	@NotNull(message = "Apellido Materno no debe ir null")
	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Activo no debe ir null")
	@Column(name = "activo")
	private boolean activo;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message = "Password no debe ir null")
	@Column(name = "password")
	private String password;

	// esto es para permisos cuando inicie sesion Relacion con Role
	@ManyToMany
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente") }, inverseJoinColumns = {
					@JoinColumn(name = "name", referencedColumnName = "name") })
	@BatchSize(size = 20)
	private Set<Role> authorities = new HashSet<>();

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Cliente user = (Cliente) o;
		return idCliente.equals(user.idCliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCliente);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", username=" + username + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", activo=" + activo + ", password=" + password
				+ ", authorities=" + authorities + "]";
	}
	
	
}
