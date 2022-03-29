package com.restaurante.pekin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "user_authority")
@IdClass(ClienteRolePK.class)
public class ClienteRole {
	// LLaves compuestas
	// @EmbeddedId solo para referencia
	// private ClienteRolePK clienteRolePK;
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue
	private int idCliente;

	@Id
	@Column(name = "name")
	private String name;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ClienteRole [idCliente=" + idCliente + ", name=" + name + "]";
	}

}